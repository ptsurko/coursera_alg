import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            if(o1 == null || o2 == null) {
                throw new NullPointerException();
            }

            double s1  = slopeTo(o1), s2 = slopeTo(o2);
            if(s1 == s2 ||
               s1 == Double.POSITIVE_INFINITY && s2 == Double.POSITIVE_INFINITY ||
               s1 == Double.NEGATIVE_INFINITY && s2 == Double.NEGATIVE_INFINITY) {
                return 0;
            } else if(slopeTo(o2) > slopeTo(o1)) {
                return 1;
            } else {
                return -1;
            }
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if(that == null) {
            throw new NullPointerException();
        }

        if(that.x == x) {
            if(that.y == y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        } else if(that.y == y) {
            return +0;
        }
        return ((double)(that.y - y) / (that.x - x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if(that == null) {
            throw new NullPointerException();
        }

        if(that.x == x && that.y == y) {
            return 0;
        } else if((that.y > y) || ((that.y == y) && (that.x > x))) {
            return -1;
        } else {
            return 1;
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}