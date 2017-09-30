/*
Given a set of points in the plane. the convex hull of the set is the smallest convex polygon that contains all the points of it.
 */
package geometry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * Created by poorvank.b on 30/09/17.
 */
public class GrahamScan {

    Point p0;
    public void convexHull(Point[] points) {

        int n = points.length;
        int minY = points[0].y;
        int min=0;

        /**
         * Finding the bottom-most point by comparing y coordinate of all points.
         * If there are two points with same y value, then the point with smaller x coordinate value is considered.
         * Let the bottom-most point be P0.
         */
        for (int i=1;i<n;i++) {
            int y = points[i].y;
            if(y<minY || (y==minY && points[min].x>points[i].x)) {
                minY=y;
                min=i;
            }
        }

        /**
         * Put P0 at first position in output hull.
         */
        swap(min,0,points);

        p0=points[0];
        /**
         * Sort n-1 points with respect to the first point.
         * A point p1 comes before p2 in sorted output if p2
         * has larger polar angle (in counterclockwise
         * direction) than p1
         */
        Arrays.sort(points,1,n,new PointComparator());

        int m=1; // Initialize size of modified array

        /* If two or more points make same angle with p0,
        *Remove all but the one that is farthest from p0
        *Remember that, in above sorting, our criteria was
        *to keep the farthest point at the end when more than
        one points have same angle.*/
        for (int i=1;i<n;i++) {

            while (i<n-1 && Orientation.findOrientation(p0,points[i],points[i+1])==0) {
                i++;
            }

            points[m]=points[i];
            m++;

        }

        if (m < 3) return;

        /**
         * The first two points in sorted array are always part of Convex Hull. For remaining points,
         * we keep track of recent three points, and find the angle formed by them. Let the three points be prev(p),
         * curr(c) and next(n). If orientation of these points (considering them in same order) is not counterclockwise,
         * we discard c, otherwise we keep it.
         *
         */

        Stack<Point> s = new Stack<>();
        s.add(points[0]);
        s.add(points[1]);
        s.add(points[2]);


        for (int i = 3; i < m; i++)
        {
            // Keep removing top while the angle formed by
            // points next-to-top, top, and points[i] makes
            // a non-left turn
            while (s.size()>=2 && Orientation.findOrientation(nextToTop(s), s.peek(), points[i])>=0)
                s.pop();
            s.push(points[i]);
        }

        // Now stack has the output points, print contents of stack
        while (!s.empty())
        {
            Point p = s.peek();
            System.out.println("(" + p.x + ", " + p.y + ")" );
            s.pop();
        }

    }

    private void swap(int a,int b,Point[] points) {
        Point t = points[a];
        points[a]=points[b];
        points[b]=t;
    }

    private class PointComparator implements Comparator<Point> {

        @Override
        public int compare(Point p1,Point p2) {
            int o = Orientation.findOrientation(p0,p1,p2);
            if(o==0) {
                return squareDistance(p0,p2)>=squareDistance(p0,p1)?-1:1;
            }

            return o<0?-1:1;
        }
    }

    private int squareDistance(Point p1,Point p2) {
        return (p1.x - p2.x)*(p1.x - p2.x) +
                (p1.y - p2.y)*(p1.y - p2.y);
    }


    private Point nextToTop(Stack<Point> S) {
        Point p = S.peek();
        S.pop();
        Point res = S.peek();
        S.push(p);
        return res;
    }

    public static void main(String[] args) {
        Point points[] = {new Point(0,3), new Point(1, 1), new Point(2, 2), new Point(4, 4),
                new Point(0, 0), new Point(1, 2), new Point(3, 1), new Point(3, 3)};
        new GrahamScan().convexHull(points);
    }

}

/*
Worst case time complexity of Jarvis’s Algorithm is O(n^2). Using Graham’s scan algorithm, we can find Convex Hull in O(nLogn) time.
Following is Graham’s algorithm

Let points[0..n-1] be the input array.

1) Find the bottom-most point by comparing y coordinate of all points. If there are two points with same y value,
then the point with smaller x coordinate value is considered. Let the bottom-most point be P0. Put P0 at first position in output hull.

2) Consider the remaining n-1 points and sort them by polor angle in counterclockwise order around points[0].
If polor angle of two points is same, then put the nearest point first.

3 After sorting, check if two or more points have same angle. If two more points have same angle, then remove
 all same angle points except the point farthest from P0. Let the size of new array be m.

4) If m is less than 3, return (Convex Hull not possible)

5) Create an empty stack ‘S’ and push points[0], points[1] and points[2] to S.

6) Process remaining m-3 points one by one. Do following for every point ‘points[i]’
        4.1) Keep removing points from stack while orientation of following 3 points is not counterclockwise (or they don’t make a left turn).
            a) Point next to top in stack
            b) Point at the top of stack
            c) points[i]
         4.2) Push points[i] to S

5) Print contents of S
 */