package game;

import biuoop.DrawSurface;
import biuoop.GUI;

import collidables.Block;
import collidables.Collidable;
import collidables.Paddle;

import geometry.Point;
import geometry.Rectangle;

import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.Counter;
import listeners.ScoreTrackingListener;

import sprites.Ball;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.Velocity;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * a Game class.
 *
 * @author Bar Tawil
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;

    /**
     * construct the Game class.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", 800, 600);
        this.blocksCounter = new Counter(0);
        this.ballsCounter = new Counter(0);
        this.scoreCounter = new Counter(0);
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
        // create balls
        List<Ball> balls = new LinkedList<Ball>();
        ballList(balls);
        // create limits
        List<Block> limits = new LinkedList<Block>();
        boardLimits(limits);
        // death block
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        limits.get(3).addHitListener(ballRemover);
        // create blocks
        List<Block> blocks = new LinkedList<Block>();
        allBlocks(blocks);
        BlockRemover blockRemover = new BlockRemover(this, blocksCounter);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(scoreCounter);
        for (Block block : blocks) {
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
        Ball ball1 = new Ball(new Point(100, 100), 5, Color.RED);
        balls.add(ball1);
        Ball ball2 = new Ball(new Point(200, 200), 5, Color.RED);
        balls.add(ball2);
        Ball ball3 = new Ball(new Point(100, 500), 5, Color.RED);
        balls.add(ball3);
        for (Ball ball: balls) {
            double angle = (Math.random() * (70) + 10);
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, 5));
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            this.ballsCounter.increase(1);
        }
        return  balls;
    }

    /**
     * @param limits - blocks at the limits of the screen
     * @return - list of blacks
     */
    private List<Block> boardLimits(List<Block> limits) {
        Rectangle rectangle1 = new Rectangle(new Point(15, 15), 800, 20);
        Block block1 = new Block(rectangle1, new Color(187, 187, 119));
        limits.add(block1);
        Rectangle rectangle2 = new Rectangle(new Point(0, 15), 20, 600);
        Block block2 = new Block(rectangle2, new Color(187, 187, 119));
        limits.add(block2);
        Rectangle rectangle3 = new Rectangle(new Point(780, 15), 20, 600);
        Block block3 = new Block(rectangle3, new Color(187, 187, 119));
        limits.add(block3);
        Rectangle rectangle4 = new Rectangle(new Point(20, 600), 760, 20);
        Block block4 = new Block(rectangle4, new Color(242, 255, 204));
        limits.add(block4);
        for (Block block: limits) {
            block.addToGame(this);
        }
        return limits;
    }

    /**
     * @param blockList - list of all blocks in the middle of the screen
     * @return - update list of blocks
     */
    private List<Block> allBlocks(List<Block> blockList) {
        Color[] colors = new Color[6];
        colors[0] = new Color(153, 0, 61);
        colors[1] = new Color(255, 51, 51);
        colors[2] = new Color(255, 255, 77);
        colors[3] = new Color(26, 255, 26);
        colors[4] = new Color(6, 132, 122);
        colors[5] = new Color(0, 0, 153);
        // sets all blocks in the right place
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j < 13 - i; j++) {
                // small blocks width
                int width = 50;
                // small blocks height
                int height = 20;
                Rectangle rectangle1 = new Rectangle(new Point(780 - width * j, 150 + height * i), width, height);
                Block block1 = new Block(rectangle1, colors[i]);
                blockList.add(block1);
            }
        }
        return blockList;
    }

    /**
     *
     * @param balls - list of balls
     */
    private void peddleMove(List<Ball> balls) {
        Rectangle pd = new Rectangle(new Point(370, 585), 100, 15);
        Paddle paddle = new Paddle(pd, gui.getKeyboardSensor(), Color.black);
        paddle.addToGame(this);
        for (Ball ball: balls) {
            ball.getGameEnvironment().addCollidable(paddle);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.blocksCounter.getValue() != 0 && this.ballsCounter.getValue() != 0) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(new Color(242, 255, 204));
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.blocksCounter.getValue() == 0) {
                this.scoreCounter.increase(100);
            }
        }
        this.gui.close();
    }

    /**
     *
     * @param c - collidable we need to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     *
     * @param s - sprite we need to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     *
     * @return - the scour of the player
     */
    public Counter getScoreCounter() {
        return  this.scoreCounter;
    }

    /**
     * @param args - none
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}