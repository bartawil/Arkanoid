package listeners;

import collidables.Block;
import game.Game;
import sprites.Ball;

/**
 * a BlockRemover class implements HitListener.
 * in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Bar Tawil
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Construct a new BlockRemover by game and remainingBlocks.
     *
     * @param game          - this game
     * @param removedBlocks - blocks removed from game
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decrease(1);
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
    }
}