package animations;

import biuoop.DrawSurface;

/**
 * a Animation interface.
 *
 * @author Bar Tawil
 */
public interface Animation {
    /**
     * draw objects from the animation we are running.
     *
     * @param d - the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * stopping conditions.
     *
     * @return true/false.
     */
    boolean shouldStop();
}
