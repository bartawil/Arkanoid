package game;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import collidables.Block;
import collidables.Collidable;
import collidables.Paddle;

import geometry.Point;
import geometry.Rectangle;

import levels.LevelInformation;

import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.Counter;
import listeners.ScoreTrackingListener;

import sprites.Ball;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * a Game class implements Animation.
 *
 * @author Bar Tawil
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private ScoreTrackingListener scoreCounter;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private static final double LIMITS_WIDE = 25;

    /**
     * construct the Game class.
     *
     * @param level                 - current animation
     * @param runner                - the runner
     * @param keyboard              - the keyboard
     * @param scoreTrackingListener - the score listener
     */
    public GameLevel(LevelInformation level, AnimationRunner runner, biuoop.KeyboardSensor keyboard,
                     ScoreTrackingListener scoreTrackingListener) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksCounter = new Counter(0);
        this.ballsCounter = new Counter(0);
        this.scoreCounter = scoreTrackingListener;
        this.runner = runner;
        this.keyboard = keyboard;
        this.level = level;
    }

    /**
     * @param c - Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * @param s - sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        this.sprites.addSprite(this.level.getBackground());
        // create balls
        List<Ball> balls = new LinkedList<>();
        ballList(balls);
        // create limits
        List<Block> limits = new LinkedList<>();
        boardLimits(limits);
        // death block
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        limits.get(3).addHitListener(ballRemover);
        // create blocks
        BlockRemover blockRemover = new BlockRemover(this, blocksCounter);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(scoreCounter.getCurrentScore());
        for (Block block : level.blocks()) {
            block.addToGame(this);
            this.blocksCounter.increase(1);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracking);
        }
        // create paddle
        peddleMove(balls);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this);
        scoreIndicator.addToGame();
    }

    /**
     * @param balls - list of balls in the game
     * @return - list of balls
     */
    private List<Ball> ballList(List<Ball> balls) {
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point(400, 578), 5, Color.WHITE);
            ball.setVelocity(level.initialBallVelocities().get(i));
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            this.ballsCounter.increase(1);
            balls.add(ball);
        }
        return balls;
    }

    /**
     * @param limits - blocks at the limits of the screen
     * @return - list of blacks
     */
    private List<Block> boardLimits(List<Block> limits) {
        Rectangle rectangle1 = new Rectangle(new Point(15, 20), 800, this.LIMITS_WIDE);
        Block block1 = new Block(rectangle1, new Color(187, 202, 182));
        limits.add(block1);
        Rectangle rectangle2 = new Rectangle(new Point(0, 15), this.LIMITS_WIDE, 600);
        Block block2 = new Block(rectangle2, new Color(187, 202, 182));
        limits.add(block2);
        Rectangle rectangle3 = new Rectangle(new Point(775, 15), this.LIMITS_WIDE, 600);
        Block block3 = new Block(rectangle3, new Color(187, 202, 182));
        limits.add(block3);
        Rectangle rectangle4 = new Rectangle(new Point(this.LIMITS_WIDE, 600), 760, this.LIMITS_WIDE);
        Block block4 = new Block(rectangle4, new Color(187, 202, 182));
        limits.add(block4);
        for (Block block : limits) {
            block.addToGame(this);
        }
        return limits;
    }

    /**
     * @param balls - list of balls
     */
    private void peddleMove(List<Ball> balls) {
        Rectangle pd = new Rectangle(new Point(400 - level.paddleWidth() / 2, 585), level.paddleWidth(), 20);
        Paddle paddle = new Paddle(pd, this.keyboard, new Color(64, 64, 32));
        paddle.addToGame(this);
        for (Ball ball : balls) {
            ball.getGameEnvironment().addCollidable(paddle);
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.level.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blocksCounter.getValue() == 0) {
            this.scoreCounter.getCurrentScore().increase(100);
            this.running = false;
        }
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, new PauseScreen()));
        }
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * @param c - collidable we need to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * @param s - sprite we need to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * @return - the scour of the player
     */
    public Counter getScoreCounter() {
        return this.scoreCounter.getCurrentScore();
    }

    /**
     * @return - level name.
     */
    public String getLevelName() {
        return this.level.levelName();
    }

    /**
     * @return - number of balls.
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * @return - number of blocks.
     */
    public Counter getBlocksCounterCounter() {
        return this.blocksCounter;
    }
}