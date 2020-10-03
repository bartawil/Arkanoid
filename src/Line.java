/**
 * A Line class.
 *
 * @author Bar Tawil
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Construct a line by given start and end points.
     *
     * @param start - the start of the line point
     * @param end   - the end of the line point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Construct the start and end Points by coordinates.
     *
     * @param x1 - the x coordinate for the start point
     * @param y1 - the y coordinate for the start point
     * @param x2 - the x coordinate for the end point
     * @param y2 - the y coordinate for the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        double mx = ((this.start.getX() + this.end.getX())) / 2;
        double my = ((this.start.getY() + this.end.getY())) / 2;
        return new Point(mx, my);
    }

    /**
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * gets two lines and find if their slopes equal.
     *
     * @param other - other line.
     * @return if equals - true, else - false.
     */
    public boolean equalSlope(Line other) {
        // finds our line slope
        double slopeLine = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        // finds other line slope
        double slopeOther = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        // checks is both slopes are equal
        if (slopeLine == slopeOther) {
            return true;
        }
        return false;
    }

    /**
     * find if lines don't have slopes - vertical lines.
     *
     * @param other - other line.
     * @return no slope - true, else - false.
     */
    public boolean haveNolSlope(Line other) {
        if ((this.start.getX() == this.end.getX()) && (other.start.getX() == other.end.getX())) {
            return true;
        }
        return false;
    }

    /**
     * the func founds the intersection point of two equations.
     *
     * @param other - other line.
     * @return Point val - the intersection point.
     */
    public Point equationsIntersection(Line other) {
        // find equations like: y = mx +b
        double slopeLine = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double slopeOther = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double bLine = this.start.getY() - slopeLine * start.getX();
        double bOther = other.start.getY() - slopeOther * other.start.getX();
        double xVal = 1;
        // after compering the equations we will get:
        xVal = (bOther - bLine) / (slopeLine - slopeOther);
        // case slopes equals
        if (slopeLine == slopeOther) {
            if (Math.max(this.start.getX(), this.end.getX()) == Math.min(other.start.getX(), other.end.getX())) {
                xVal = Math.max(this.start.getX(), this.end.getX());
            } else if (Math.min(this.start.getX(), this.end.getX()) == Math.max(other.start.getX(), other.end.getX())) {
                xVal = Math.min(this.start.getX(), this.end.getX());
            }
        }
        // putting xVal in one of owr equations
        double yVal = slopeLine * xVal + bLine;
        // case other line parallel to Y axis
        if ((this.start.getX() != this.end.getX()) && (other.start.getX() == other.end.getX())) {
            xVal = other.start.getX();
            yVal = slopeLine * xVal + bLine;
        }
        // case this line parallel to Y axis
        if ((this.start.getX() == this.end.getX()) && (other.start.getX() != other.end.getX())) {
            xVal = this.start.getX();
            yVal = slopeOther * xVal + bOther;
        }
        // case both lines parallel to Y axis
        if (haveNolSlope(other)) {
            xVal = this.start.getX();
            if (Math.max(this.start.getY(), this.end.getY()) == Math.min(other.start.getY(), other.end.getY())) {
                yVal = Math.max(this.start.getY(), this.end.getY());
            } else if (Math.min(this.start.getY(), this.end.getY()) == Math.max(other.start.getY(), other.end.getY())) {
                yVal = Math.min(this.start.getY(), this.end.getY());
            }
        }
        // the intersection point
        Point val = new Point(xVal, yVal);
        return val;
    }

    /**
     * checks if the intersection point is in the range of owr lines.
     * checks if any x or y from the intersection point is between each line.
     *
     * @param other - other line.
     * @return true if the point is in the correct range, false otherwise.
     */
    public boolean inRange(Line other) {
        if (Math.min(this.start.getX(), this.end.getX()) <= equationsIntersection(other).getX()
                && equationsIntersection(other).getX() <= Math.max(this.start.getX(), this.end.getX())
                && Math.min(other.start.getX(), other.end.getX()) <= equationsIntersection(other).getX()
                && equationsIntersection(other).getX() <= Math.max(other.start.getX(), other.end.getX())
                && Math.min(this.start.getY(), this.end.getY()) <= equationsIntersection(other).getY()
                && equationsIntersection(other).getY() <= Math.max(this.start.getY(), this.end.getY())
                && Math.min(other.start.getY(), other.end.getY()) <= equationsIntersection(other).getY()
                && equationsIntersection(other).getY() <= Math.max(other.start.getY(), other.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * checks if the lines can potentially intersect.
     *
     * @param other - other line.
     * @return true - if the lines intersect, false - otherwise.
     */
    public boolean isIntersecting(Line other) {
        // case both lines parallel to Y axis
        if (haveNolSlope(other)) {
            // checks if lines have one and only one intersection point
            if (this.start.equals(other.start) ^ this.start.equals(other.end) ^ this.end.equals(other.start)
                    ^ this.end.equals(other.end)) {
                // checks if lines not merging
                if (Math.max(this.start.getY(), this.end.getY()) == Math.min(other.start.getY(), other.end.getY())
                        || Math.min(this.start.getY(), this.end.getY())
                        == Math.max(other.start.getY(), other.end.getY())) {
                    return true;
                }
                return false;
            }
            return false;
        }
        // case both lines have the same slope
        if (equalSlope(other)) {
            // checks if lines have one and only one intersection point
            if (this.start.equals(other.start) ^ this.start.equals(other.end) ^ this.end.equals(other.start)
                    ^ this.end.equals(other.end)) {
                // checks if lines not merging
                if (Math.max(this.start.getX(), this.end.getX()) == Math.min(other.start.getX(), other.end.getX())
                        || Math.min(this.start.getX(), this.end.getX())
                        == Math.max(other.start.getX(), other.end.getX())) {
                    return true;
                }
                return false;
            }
        } else if (inRange(other)) {
            return true;
        }
        return false;
    }

    /**
     * @param other - the other line we intersect.
     * @return - the intersection point if the lines actually intersect.
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            return equationsIntersection(other);
        } else {
            return null;
        }
    }

    /**
     * @param other - the other line we compare.
     * @return true - if the lines are equal, false - otherwise.
     */
    public boolean equals(Line other) {
        return (this.start == other.start && this.end == other.end);
    }

    /**
     * return the closest intersection point to the start of the line.
     *
     * @param rect - rectangle
     * @return null/closest intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line l = new Line(this.start, this.end);
        if (!rect.intersectionPoints(l).isEmpty()) {
            int listSize = rect.intersectionPoints(l).size();
            // min line index of the list
            int min = 0;
            for (int i = 1; i < listSize; i++) {
                if (this.start.distance(rect.intersectionPoints(l).get(min))
                        > this.start.distance(rect.intersectionPoints(l).get(i))) {
                    min++;
                }
            }
            return rect.intersectionPoints(l).get(min);
        }
        return null;
    }
}