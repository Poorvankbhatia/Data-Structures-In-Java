/*

Given two line segments (p1, q1) and (p2, q2), find if the given line segments intersect with each other.

 */

package geometry;

/**
 * Created by poorvank on 15/12/16.
 */
public class IntersectingLines {

    private boolean coincides(Point p,Point r,Point q) {

        return  (r.x<=Math.max(p.x,q.x) && r.x>=Math.min(p.x,q.x) && r.y<=Math.max(p.y,q.y) && r.y>=Math.min(p.y,q.y));

    }

    public boolean areIntersecting(Point p1,Point q1,Point p2,Point q2) {

        int o1 = Orientation.findOrientation(p1,q1,p2);
        int o2 = Orientation.findOrientation(p1,q1,q2);
        int o3 = Orientation.findOrientation(p2,q2,p1);
        int o4 = Orientation.findOrientation(p2,q2,q1);

        if(o1!=o2 && o3!=o4) {
            return true;
        }

        if(o1==0 && coincides(p1,p2,q1)) {
            return true;
        }
        if(o2==0 && coincides(p1,q2,q1)) {
            return true;
        }
        if(o3==0 && coincides(p2,p1,q2)) {
            return true;
        }
        if(o4==0 && coincides(p2,q1,q2)) {
            return true;
        }

        return false;

    }

    public static void main(String[] args) {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,2);
        Point q1 = new Point(2,-1);
        Point q2 = new Point(0,-1);
        System.out.print(new IntersectingLines().areIntersecting(p1,p2,q1,q2));
    }

}


/*

How is Orientation useful here?
Two segments (p1,q1) and (p2,q2) intersect if and only if one of the following two conditions is verified

1. General Case:
– (p1, q1, p2) and (p1, q1, q2) have different orientations and
– (p2, q2, p1) and (p2, q2, q1) have different orientations.


2. Special Case
– (p1, q1, p2), (p1, q1, q2), (p2, q2, p1), and (p2, q2, q1) are all collinear and
– the x-projections of (p1, q1) and (p2, q2) intersect
– the y-projections of (p1, q1) and (p2, q2) intersect

SEE IMAGES

intersecting1,2,3

 */