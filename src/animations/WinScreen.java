package animations;

import biuoop.DrawSurface;
import listeners.Counter;

import java.awt.Color;

/**
 * a WinScreen class implements Animation.
 *
 * @author Bar Tawil
 */
public class WinScreen implements Animation {
    private Counter score;
    private boolean stop;

    /**
     * Construct a new EndScreen by keyboardSensor.
     *
     * @param score - player score
     */
    public WinScreen(Counter score) {
        this.score = score;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(224, 163, 102));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
