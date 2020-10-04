


import biuoop.DrawSurface;
import biuoop.GUI;

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

    /**
     * construct the Game class.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Game", 800, 600);
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
        // first ball
        Ball ball1 = new Ball(new Point(100, 100), 5, Color.RED);
        Velocity v = Velocity.fromAngleAndSpeed(36, 5);
        ball1.setVelocity(v);
        ball1.setGameEnvironment();
        ball1.addToGame(this);
        // second ball
        Ball ball2 = new Ball(new Point(200, 200), 5, Color.RED);
        v = Velocity.fromAngleAndSpeed(60, 5);
        ball2.setVelocity(v);
        ball2.setGameEnvironment();
        ball2.addToGame(this);
        // blocks list
        List<Block> blocks = new LinkedList<>();
        // limits blocks
        boardLimits(blocks);
        // other blocks
        allBlocks(blocks);
        for (Block block : blocks) {
            block.addToGame(this);
            ball1.getGameEnvironment().addCollidable(block);
            ball2.getGameEnvironment().addCollidable(block);
        }
        // paddle
        peddleMove(ball1, ball2);
    }

    /**
     * @param limits - blocks at the limits of the screen
     * @return - list of blacks
     */
    private List<Block> boardLimits(List<Block> limits) {
        Rectangle rectangle1 = new Rectangle(new Point(0, 0), 800, 20);
        Block block1 = new Block(rectangle1, new Color(187, 187, 119));
        Rectangle rectangle2 = new Rectangle(new Point(0, 20), 20, 600);
        Block block2 = new Block(rectangle2, new Color(187, 187, 119));
        Rectangle rectangle3 = new Rectangle(new Point(780, 20), 20, 600);
        Block block3 = new Block(rectangle3, new Color(187, 187, 119));
        Rectangle rectangle4 = new Rectangle(new Point(20, 580), 760, 20);
        Block block4 = new Block(rectangle4, new Color(187, 187, 119));
        limits.add(block1);
        limits.add(block2);
        limits.add(block3);
        limits.add(block4);
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
     * @param ball1 - first ball
     * @param ball2 - second ball
     */
    private void peddleMove(Ball ball1, Ball ball2) {
        Rectangle pd = new Rectangle(new Point(370, 565), 100, 15);
        Paddle paddle = new Paddle(pd, gui.getKeyboardSensor(), Color.black);
        paddle.addToGame(this);
        ball1.getGameEnvironment().addCollidable(paddle);
        ball2.getGameEnvironment().addCollidable(paddle);
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
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
        }
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