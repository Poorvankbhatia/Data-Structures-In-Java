/*

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Sample Input :

(1, 1)
(2, 2)
Sample Output :

2
You will be give 2 arrays X and Y. Each point is represented by (X[i], Y[i])

 */
package geometry;

import java.util.*;

/**
 * Created by poorvank on 08/01/17.
 */
public class MaxPointsInALine {

    public int maxPoints(Point[] points) {
        Double d = Double.MAX_VALUE;

        if (points.length <= 1) {
            return points.length;
        }

        List<Map<Double, Set<Point>>> mapList = new ArrayList<>(points.length);

        for (int i = 0; i < points.length; i++) {
            Map<Double, Set<Point>> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                Double slope;
                if (points[j].x == points[i].x) {
                    slope = d;
                } else {
                    slope = (double) (points[j].y - points[i].y) / (double) (points[j].x - points[i].x);
                }
                if (map.get(slope) == null) {
                    Set<Point> set = new HashSet<>();
                    set.add(points[i]);
                    map.put(slope, set);
                }
                map.get(slope).add(points[j]);
            }
            mapList.add(i, map);
        }

        int max = 0;
        for (Map<Double, Set<Point>> map : mapList) {
            for (Set<Point> set : map.values()) {
                int count = set.size();
                if (count > max) {
                    max = count;
                }
            }
        }
        return max;

    }


    public static void main(String[] args) {

        Point p1 = new Point(0, 2);
        Point p2 = new Point(0, 3);

        Point p3 = new Point(2, 9);
        Point p4 = new Point(2, 4);

        Point[] arr = new Point[]{p1, p2, p3, p4};

        System.out.print(new MaxPointsInALine().maxPoints(arr));


    }

}
