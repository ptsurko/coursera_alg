
public class Brute {
    public static void main(String[] args) {
        int pointCount;
        boolean[] drawnPoints;
        boolean[][] drawnSegments;
        Point[] points;

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
//            pointCount = 6;
//            points = new Point[pointCount];
//            points[0] = new Point(19000, 10000);
//            points[1] = new Point(18000, 10000);
//            points[2] = new Point(32000, 10000);
//            points[3] = new Point(21000, 10000);
//            points[4] = new Point(1234, 5678);
//            points[5] = new Point(14000, 10000);

            pointCount = StdIn.readInt();
            points = new Point[pointCount];
            for(int i = 0; i < pointCount; i++) {
                int x = StdIn.readInt(), y = StdIn.readInt();
                points[i] = new Point(x, y);
            }
        }

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

        Sort(points);
        for(int i = 0; i < pointCount; i++) {
            if(!drawnPoints[i]) {
                drawnPoints[i] = true;
                points[i].draw();
            }

            for(int j = i + 1; j < pointCount - 2; j++) {
                for(int k = j + 1; k < pointCount - 1; k++) {
                    if(points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])) {
                        for(int m = k + 1; m < pointCount; m++) {
                            if(points[i].slopeTo(points[j]) == points[k].slopeTo(points[m])) {
                                StdOut.printf("%s -> %s -> %s -> %s", points[i], points[j], points[k], points[m]);
                                StdOut.println();

                                if(!drawnSegments[i][m]) {
                                    drawnSegments[i][m] = true;
                                    points[i].drawTo(points[m]);
                                }
                            }
                        }
                    }
                }
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
