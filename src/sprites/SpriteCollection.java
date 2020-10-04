package sprites;

import java.util.LinkedList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * SpriteCollection class.
 *
 * @author Bar Tawil
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Construct a sprite collection by list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    /**
     * add the given sprite to the list.
     *
     * @param s - new sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesList = new LinkedList<>(this.sprites);
        for (Sprite s : spritesList) {
            s.timePassed();
        }
    }

    /**
     * @param d - call drawOn(d) on all sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }


    /**
     * @return - the list of sprites.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}