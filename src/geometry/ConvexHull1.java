/*

Given a set of points in the plane. the convex hull of the set is the smallest convex polygon that contains all the points of it.

 */
package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 16/01/17.
 */
public class ConvexHull1 {

    public void printHull(Point[] points) {

        int min = Integer.MAX_VALUE;

        int n = points.length;
        int startIndex = 0;
        for (int i=0;i<n;i++) {
            if(min<points[i].x) {
                min = points[i].x;
                startIndex = i;
            }
        }

        List<Point> pointList = new ArrayList<>();
        int p = startIndex,q;

        do {
            pointList.add(points[p]);
            q = (p+1)%n;
            for (int i=0;i<n;i++) {
                if(Orientation.findOrientation(points[p],points[i],points[q])<0) {
                    q = i;
                }
            }
            p=q;

        } while (p!=startIndex);

        pointList.forEach(System.out::println);

    }

    public static void main(String[] args) {
        Point points[] = {new Point(0,3), new Point(2,2),
                new Point(6,5), new Point(8,4), new Point(3,0)};

        new ConvexHull1().printHull(points);
    }

}


/*

The idea of Jarvis’s Algorithm is simple, we start from the leftmost point (or point with minimum x coordinate value)
and we keep wrapping points in counterclockwise direction. The big question is, given a point p as current point,
how to find the next point in output? The idea is to use orientation() here. Next point is selected as the point
that beats all other points at counterclockwise orientation, i.e., next point is q if for any other point r,
we have “orientation(p, r, q) = counterclockwise”. Following is the detailed algorithm.

1) Initialize p as leftmost point.
2) Do following while we don’t come back to the first (or leftmost) point.
…..a) The next point q is the point such that the triplet (p, q, r) is counterclockwise for any other point r.
…..b) next[p] = q (Store q as next of p in the output convex hull).
…..c) p = q (Set p as q for next iteration).

For every point on the hull we examine all the other points to determine the next point.
Time complexity is ?(m * n) where n is number of input points and m is number of output or hull points (m <= n).
In worst case, time complexity is O(n^2). The worst case occurs when all the points are on the hull (m = n)

 */