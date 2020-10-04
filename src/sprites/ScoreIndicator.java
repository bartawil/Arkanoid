package sprites;

import biuoop.DrawSurface;
import game.GameLevel;

import java.awt.Color;

/**
 * a ScoreIndicator class implements sprite.
 *
 * @author Bar Tawil
 */
public class ScoreIndicator implements Sprite {
    private GameLevel game;

    /**
     * Construct a ScoreIndicator.
     *
     * @param game - the game
     */
    public ScoreIndicator(GameLevel game) {
        this.game = game;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(234, 253, 227));
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(new Color(0, 128, 128));
        d.drawText(360, 15, "Score: " + this.game.getScoreCounter().getValue(), 15);
        d.drawText(620, 15, "Level Name:  " + this.game.getLevelName(), 15);
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
