package animations;

import biuoop.DrawSurface;
import listeners.Counter;

import java.awt.Color;

/**
 * a EndScreen class implements Animation.
 *
 * @author Bar Tawil
 */
public class EndScreen implements Animation {
    private Counter score;
    private boolean stop;

    /**
     * Construct a new EndScreen by keyboardSensor.
     *
     * @param score - player score
     */
    public EndScreen(Counter score) {
        this.score = score;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
