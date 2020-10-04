package listeners;

import collidables.Block;
import game.Game;
import sprites.Ball;

/**
 * a BallRemover class implements HitListener.
 *
 * @author Bar Tawil
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Construct a new BallRemover by game and removedBalls.
     *
     * @param game         - this game
     * @param removedBalls - balls removed from game
     */
    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
