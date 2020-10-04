package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * a KeyPressStoppableAnimation class.
 *
 * @author Bar Tawil
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Construct a KeyPressStoppableAnimation.
     *
     * @param sensor    - keyboard.
     * @param key       - the pressed key.
     * @param animation - our current animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(key) && !this.isAlreadyPressed) {
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
