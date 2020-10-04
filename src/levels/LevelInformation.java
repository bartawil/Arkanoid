package levels;

import collidables.Block;

import sprites.Sprite;
import sprites.Velocity;

import java.util.List;

/**
 * a LevelInformation interface.
 *
 * @author Bar Tawil
 */
public interface LevelInformation {
    /**
     * @return - the number of balls in each level.
     */
    int numberOfBalls();

    /**
     * @return - the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return - the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return - the paddle width.
     */
    int paddleWidth();

    /**
     * @return - displayed the level name at the top of the screen.
     */
    String levelName();

    /**
     * @return - a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return - a list of blocks.
     */
    List<Block> blocks();

    /**
     * @return - number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
}