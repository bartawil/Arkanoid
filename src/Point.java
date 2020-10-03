/**
 * A Point class.
 *
 * @author Bar Tawil
 */
public class Point {
    private double x;
    private double y;

    /**
     * Construct a point by given x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other - point we measure the distance to.
     * @return - the distance between those points.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * @param other - the point we compare to.
     * @return return - true if the points are equal, false otherwise.
     */

    public boolean equals(Point other) {
        return (this.x == other.getX() && this.y == other.getY());
    }

    /**
     * @return the x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate.
     */
    public double getY() {
        return this.y;
    }
}

