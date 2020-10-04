package sprites;

import biuoop.DrawSurface;
import game.Game;

import java.awt.Color;

/**
 * a ScoreIndicator class implements sprite.
 *
 * @author Bar Tawil
 */
public class ScoreIndicator implements Sprite {
    private Game game;

    /**
     * Construct a ScoreIndicator.
     *
     * @param game - the game
     */
    public ScoreIndicator(Game game) {
        this.game = game;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(360, 15, "Score: " + this.game.getScoreCounter().getValue(), 15);
    }

    /**
     * This method adds the sprite into the game.
     */
    public void addToGame() {
        this.game.addSprite(this);
    }

    @Override
    public void timePassed() {

    }
}
