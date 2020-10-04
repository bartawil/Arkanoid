package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * CountdownAnimation class.
 *
 * @author Bar Tawil
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;
    private int startCount;

    /**
     * Construct the CountdownAnimation.
     *
     * @param numOfSeconds - sum of seconds to count.
     * @param countFrom    - revers counting.
     * @param gameScreen   - the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.sleeper = new Sleeper();
        this.startCount = this.countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int millisecondsPerFrame = 1000 * (int) this.numOfSeconds / this.startCount;
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(d.getWidth() / 2 - 60, d.getHeight() / 2 + 65, this.countFrom + "..."
                , 200);
        if (this.countFrom >= 0 && this.countFrom < 3) {
            sleeper.sleepFor(millisecondsPerFrame);
        }
        this.countFrom--;
        if (this.countFrom < 0) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
