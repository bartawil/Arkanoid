package animations;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * a PauseScreen class implements Animation.
 *
 * @author Bar Tawil
 */
public class PauseScreen implements Animation {

    /**
     * Construct a new PauseScreen by keyboardSensor.
     *
     * @param d - surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(179, 240, 179));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}