package listeners;

import collidables.Block;
import sprites.Ball;

/**
 * a HitListener interface.
 *
 * @author Bar Tawil
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit - object that hit
     * @param hitter   - the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
