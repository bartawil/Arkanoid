

package geometry;

/**
 * A Point class.
 *
 * @author Bar Tawil
 */
public class Point {
    private double x;
    private double y;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other a point to measure the distance between two points
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * @param other the other point we compare to
     * @return return true if the points are equal, false otherwise
     */

    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param dx - set the new x coordinate of the point
     */
    public void setDx(double dx) {
        this.x = dx;
    }

    /**
     * @param dy - set the new y coordinate of the point
     */
    public void setDy(double dy) {
        this.y = dy;
    }
}

