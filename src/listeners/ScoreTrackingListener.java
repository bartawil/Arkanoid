package listeners;

import collidables.Block;
import sprites.Ball;

/**
 * a ScoreTrackingListener class implements HitListener.
 *
 * @author Bar Tawil
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Construct ScoreTrackingListener.
     *
     * @param scoreCounter - the player score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @param beingHit - object that hit
     * @param hitter   - the Ball that's doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }


    /**
     *
     * @return - current game score.
     */
    public Counter getCurrentScore() {
        return  this.currentScore;
    }
}