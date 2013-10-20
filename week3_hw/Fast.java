
public class Fast {
    public static void main(String[] args) {
        int pointCount;
        Point[] points;
        boolean[] drawnPoints;
        boolean[][] drawnSegments;

        if(args.length > 0 && args[0] != null) {
            In in = new In(args[0]);
            if (!in.exists()) {
                throw new IllegalArgumentException("Input file does not exist: " + args[0]);
            }

            pointCount = Integer.parseInt(in.readLine());
            points = new Point[pointCount];

            System.out.write(pointCount);
            int i = 0;
            while(i != pointCount) {
                String[] lineSplited = in.readLine().trim().split("\\s+");

                for(int j = 0; j < lineSplited.length; j += 2) {
                    points[i++] = new Point(Integer.parseInt(lineSplited[j]), Integer.parseInt(lineSplited[j + 1]));
                }
            }

        } else {
            pointCount = StdIn.readInt();
            points = new Point[pointCount];
            for(int i = 0; i < pointCount; i++) {
                int x = StdIn.readInt(), y = StdIn.readInt();
                points[i] = new Point(x, y);
            }
        }
        Sort(points);


        drawnPoints = new boolean[pointCount];;
        drawnSegments = new boolean[pointCount][pointCount];
        for(int i = 0; i < pointCount; i++) {
            drawnPoints[i] = false;
            for(int j = 0; j < pointCount; j++) {
                drawnSegments[i][j] = false;
            }
        }

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for(int i = 0; i < pointCount; i++) {
            if(!drawnPoints[i]) {
                drawnPoints[i] = true;
                points[i].draw();
            }
            Slope[] slopes = new Slope[pointCount];
            //TODO: rewriter with more efficient alg
            for(int j = 0; j < pointCount; j++) {
                slopes[j] = new Slope(points[i].slopeTo(points[j]), points[j]);
            }
            Sort(slopes);

            Slope prevSlope = slopes[0];
            int count = 1;
            String str = slopes[0].point.toString();
            for(int j = 1; j < pointCount; j++) {
                if(equal(prevSlope.val, slopes[j].val)) {
                    count++;
                    str += " -> " + slopes[j].point.toString();
                } else {
                    if (count >= 3 && points[i].compareTo(prevSlope.point) < 0) {
                        StdOut.println(points[i].toString() + " -> " + str);

                        if(!drawnSegments[i][j]) {
                            drawnSegments[i][j] = true;
                            points[i].drawTo(points[j]);
                        }
                    }

                    prevSlope = slopes[j];
                    str = prevSlope.point.toString();
                    count = 1;
                }
            }
        }
    }

    private static boolean equal(double d1, double d2) {
        return d1 == d2 ||
               d1 == Double.POSITIVE_INFINITY && d2 == Double.POSITIVE_INFINITY ||
               d1 == Double.NEGATIVE_INFINITY && d2 == Double.NEGATIVE_INFINITY;
    }

    private static class Slope implements Comparable<Slope> {

        public double val;
        public Point point;

        public Slope(double val, Point point) {
            this.val = val;
            this.point  = point;
        }

        @Override
        public int compareTo(Slope o) {
            if(o.val == this.val) {
                return 0;
            } else if(o.val > this.val) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private static void Sort(Comparable[] items) {
        if(items.length > 1) {
            for(int i = 1; i < items.length; i++) {
                int j = i;
                while(j > 0 && items[j].compareTo(items[j - 1]) < 0) {
                    Comparable temp = items[j];
                    items[j] = items[j - 1];
                    items[j - 1] = temp;
                    j--;
                }
            }
        }
    }
}