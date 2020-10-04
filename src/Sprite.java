package sprites;

import biuoop.DrawSurface;

/**
 * This interface will be used by things that should be draw on.
 *
 * @author Bar Tawil
 */
public interface Sprite {

    /**
     * @param d - draw the sprite to the screen.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has.
     */
    void timePassed();
}