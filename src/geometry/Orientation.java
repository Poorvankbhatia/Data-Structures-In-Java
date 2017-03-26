/*

Orientation of an ordered triplet of points in the plane can be

counterclockwise
clockwise
collinear

Look images: Orientation of 3 points

If orientation of (p1, p2, p3) is collinear, then orientation of (p3, p2, p1) is also collinear.
If orientation of (p1, p2, p3) is clockwise, then orientation of (p3, p2, p1) is counterclockwise and vice versa is also true.

Given three points p1, p2 and p3, find orientation of (p1, p2, p3).
Example:

Input:   p1 = {0, 0}, p2 = {4, 4}, p3 = {1, 2}
Output:  CounterClockWise

Input:   p1 = {0, 0}, p2 = {4, 4}, p3 = {1, 1}
Output:  Collinear


 */
package geometry;

/**
 * Created by poorvank on 12/12/16.
 */
public class Orientation {

    public static int findOrientation(Point p,Point q,Point r) {
        return ((q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y));
    }

    public static void main(String[] args) {
        Point p1 = new Point(-1,-2);
        Point p2 = new Point(1,-1);
        Point p3 = new Point(0,0);

        int orientation = findOrientation(p1,p2,p3);

        if(orientation<0) {
            System.out.println("Counter clockwise");
        } else if(orientation>0) {
            System.out.println("Clockwise");
        } else {
            System.out.println("Collinear");
        }

    }

}


/*

How to compute Orientation?

The idea is to use slope.


Slope of line segment (p1, p2): σ = (y2 - y1)/(x2 - x1)
Slope of line segment (p2, p3): τ = (y3 - y2)/(x3 - x2)

If  σ < τ, the orientation is counterclockwise (left turn)
If  σ = τ, the orientation is collinear
If  σ > τ, the orientation is clockwise (right turn)

Using above values of σ and τ, we can conclude that,
the orientation depends on sign of  below expression:

(y2 - y1)*(x3 - x2) - (y3 - y2)*(x2 - x1)

Above expression is negative when σ < τ, i.e., counterclockwise
Above expression is 0 when σ = τ, i.e., collinear
Above expression is positive when σ > τ, i.e., clockwise

((y2-y1)/(x2-x1)) - ( (y3-y1) / (x3-x1)

 */