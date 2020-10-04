

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * A Paddle class implementing the collidable and sprite interface.
 *
 * @author Bar Tawil
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle peddle;
    private Color color;
    private static final double SURFACE_WIDTH = 800;
    private static final double PADDLE_MOVES = 10;
    private static final double BORDER_WIDTH = 20;

    /**
     * Construct a paddle given rectangle, Keyboard Sensor and color.
     *
     * @param rect  - rectangle size
     * @param k     - keyboard
     * @param color - paddle color
     */
    public Paddle(Rectangle rect, KeyboardSensor k, Color color) {
        this.peddle = rect;
        this.color = color;
        this.keyboard = k;
    }

    /**
     * move the paddle to the left.
     */
    public void moveLeft() {
        if (this.peddle.getUpperLeft().getX() - this.PADDLE_MOVES > this.BORDER_WIDTH) {
            this.peddle.getUpperLeft().setDx(this.peddle.getUpperLeft().getX() - this.PADDLE_MOVES);
        } else {
            this.peddle.getUpperLeft().setDx(this.BORDER_WIDTH);
        }
    }

    /**
     * move the paddle to the right.
     */
    public void moveRight() {
        if (this.peddle.getUpperLeft().getX() + this.peddle.getWidth() + this.PADDLE_MOVES
                < this.SURFACE_WIDTH - this.BORDER_WIDTH) {
            this.peddle.getUpperLeft().setDx(this.peddle.getUpperLeft().getX() + this.PADDLE_MOVES);
        } else {
            this.peddle.getUpperLeft().setDx(this.SURFACE_WIDTH - this.BORDER_WIDTH - this.peddle.getWidth());
        }
    }

    /**
     * notify the sprite that time has.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * @param d - draw the paddle on the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.peddle.getUpperLeft().getX(), (int) this.peddle.getUpperLeft().getY(),
                (int) this.peddle.getWidth(), (int) this.peddle.getHeight());
        d.setColor(Color.white);
        d.drawRectangle((int) this.peddle.getUpperLeft().getX(), (int) this.peddle.getUpperLeft().getY(),
                (int) this.peddle.getWidth(), (int) this.peddle.getHeight());
    }

    /**
     * @return - peddle as collidable object.
     */
    public Rectangle getCollisionRectangle() {
        return this.peddle;
    }

    /**
     * @param collisionPoint  -the collision Point.
     * @param currentVelocity - a given velocity.
     * @return - new velocity.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity v = currentVelocity;
        double speed = Math.sqrt(v.getDx() * v.getDx() + v.getDy() * v.getDy());
        double secWidth = (this.peddle.getWidth() / 5);
        double leftX = this.peddle.getUpperLeft().getX();
        if (leftX < collisionPoint.getX() && collisionPoint.getX() < leftX + secWidth) {
            v = currentVelocity.fromAngleAndSpeed(-60, speed);
        }
        if (leftX + secWidth <= collisionPoint.getX() && collisionPoint.getX() <= leftX + 2 * secWidth) {
            v = currentVelocity.fromAngleAndSpeed(-30, speed);
        }
        if (leftX + 2 * secWidth <= collisionPoint.getX() && collisionPoint.getX() <= leftX + 3 * secWidth) {
            v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (leftX + 3 * secWidth <= collisionPoint.getX() && collisionPoint.getX() <= leftX + 4 * secWidth) {
            v = currentVelocity.fromAngleAndSpeed(30, speed);
        }
        if (leftX + 4 * secWidth <= collisionPoint.getX() && collisionPoint.getX() <= leftX + 5 * secWidth) {
            v = currentVelocity.fromAngleAndSpeed(60, speed);
        }
        if (collisionPoint.getY() == this.peddle.getUpperLeft().getY()
                || collisionPoint.getY() == this.peddle.getUpperLeft().getY() + this.peddle.getHeight()) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        return v;
    }

    /**
     * @param g - Add this paddle to the game.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}