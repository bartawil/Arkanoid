package sprites;

import geometry.Point;

/**
 * Velocity class - specifies the change in position on the `x` and the `y` axis's.
 *
 * @author Bar Tawil
 */
public class Velocity {
    private double x;
    private double y;

    /**
     * Construct an x and y values for the next center point.
     *
     * @param dx the x coordinate
     * @param dy the y coordinate
     */
    public Velocity(double dx, double dy) {
        this.x = dx;
        this.y = dy;
    }

    /**
     *
     * Construct an x and y values for the next center point by angle and speed.
     *
     * @param angle - move angle
     * @param speed - move speed
     * @return - velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);

    }

    /**
     * @return - how much we move on x axis
     */
    public double getDx() {
        return this.x;
    }

    /**
     * @return - how much we move on y axis
     */
    public double getDy() {
        return this.y;
    }

    /**
     * @param dx - set the new x coordinate of the center point
     */
    public void setDx(double dx) {
        this.x = dx;
    }

    /**
     * @param dy - set the new y coordinate of the center point
     */
    public void setDy(double dy) {
        this.y = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     *
     * @param p - random point
     * @return with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.x, p.getY() + this.y);
    }
}


