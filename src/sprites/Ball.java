package sprites;

import biuoop.DrawSurface;
import collidables.CollisionInfo;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * A Ball class.
 *
 * @author Bar Tawil
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Construct a ball given center point, radius and color.
     *
     * @param center - ball center
     * @param r      - ball radius
     * @param color  - color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Construct a ball given x and y coordinates, radius and color.
     *
     * @param x     - the x coordinate
     * @param y     - the y coordinate
     * @param r     - ball radius
     * @param color - color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * @return - x coordinate of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return - y coordinate of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return - r radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return - color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * @param v - velocity of ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx - velocity of x
     * @param dy - velocity of x
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return - ball velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set a new game environment.
     * @param ge - the game environment
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }

    /**
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * moving the ball.
     */
    public void moveOneStep() {
        Line line = new Line(this.center, getVelocity().applyToPoint(this.center));
        CollisionInfo info = this.gameEnvironment.getClosestCollision(line);
        if (this.gameEnvironment.getClosestCollision(line) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Velocity prev = new Velocity(this.velocity.getDx(), this.velocity.getDy());
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity());
            if (prev.getDx() < velocity.getDx()) {
                this.center = new Point(info.collisionPoint().getX() + 0.5 * r, info.collisionPoint().getY());
            }
            if (prev.getDx() > velocity.getDx()) {
                this.center = new Point(info.collisionPoint().getX() - 0.5 * r, info.collisionPoint().getY());
            }
            if (prev.getDy() < velocity.getDy()) {
                this.center = new Point(info.collisionPoint().getX(), info.collisionPoint().getY() + 0.5 * r);
            }
            if (prev.getDy() > velocity.getDy()) {
                this.center = new Point(info.collisionPoint().getX(), info.collisionPoint().getY() - 0.5 * r);
            }
        }
    }


    /**
     * replacing the center point if its too close to the border.
     *
     * @param l1 - end window coordinates
     * @param l2 - start window coordinates
     */
    public void startPoint(int l1, int l2) {
        if (center.getX() + this.r >= l1) {
            this.center = new Point(l1 - this.r, getY());
        }
        if (center.getX() - this.r <= l2) {
            this.center = new Point(l2 + this.r, getY());
        }
        if (center.getY() + this.r >= l1) {
            this.center = new Point(getX(), l1 - this.r);
        }
        if (center.getY() - this.r <= l2) {
            this.center = new Point(getX(), l2 + this.r);
        }
    }

    /**
     * moving the ball.
     *
     * @param l1 - end window coordinates
     * @param l2 - start window coordinates
     */
    public void moveOneStep(int l1, int l2) {
        startPoint(l1, l2);
        if (this.center.getX() + getVelocity().getDx() + this.r >= l1) {
            this.getVelocity().setDx(-getVelocity().getDx());
        }
        if (this.center.getX() + getVelocity().getDx() - this.r <= l2) {
            this.getVelocity().setDx(-getVelocity().getDx());
        }
        if (this.center.getY() + getVelocity().getDy() + this.r >= l1) {
            this.getVelocity().setDy(-getVelocity().getDy());
        }
        if (this.center.getY() + getVelocity().getDy() - this.r <= l2) {
            this.getVelocity().setDy(-getVelocity().getDy());
        }
        this.center = getVelocity().applyToPoint(this.center);
    }

    /**
     * @param surface - draw the ball on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(getX(), getY(), getSize());
        surface.setColor(this.color);
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * @param g - add the ball sprite to the game class
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removes the ball from the game.
     * @param game - this game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}