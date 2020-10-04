package geometry;

import java.util.LinkedList;
import java.util.List;

/**
 * A Rectangle class.
 *
 * @author Bar Tawil
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;


    /**
     * Create a new rectangle by point location and width/height length.
     *
     * @param upperLeft - start point
     * @param width     - width of the rectangle
     * @param height    - height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line - line intersect with the rectangle
     * @return - List of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // finds all the rectangle coordinates
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        // array with all the rectangle edges
        Line[] edges = new Line[4];
        edges[0] = new Line(this.upperLeft, upperRight);
        edges[1] = new Line(upperRight, downRight);
        edges[2] = new Line(downLeft, downRight);
        edges[3] = new Line(this.upperLeft, downLeft);
        List<Point> intersectionList = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            if (edges[i].isIntersecting(line)) {
                intersectionList.add(edges[i].intersectionWith(line));
            }
        }
        return intersectionList;
    }

    /**
     * @return - the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return - the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return - the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}

