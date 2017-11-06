/*

Given a polygon and a point ‘p’, find if ‘p’ lies inside the polygon or not. The points lying on the border are considered inside.

 */
package geometry;

/**
 * Created by poorvank.b on 13/09/17.
 */
public class PointInside {

    private Integer INFINITY=10000;

    private Point inf;

    private int orientation(Point p,Point q,Point r) {
        return ((q.x-p.x)*(r.y-q.y))-((r.x-q.x)*(q.y-p.y));
    }

    private boolean onSegment(Point p,Point r,Point q) {
        return (r.x>=Math.min(p.x,q.x)) && (r.y>=Math.min(p.y,q.y)) && (r.x<=Math.max(p.x,q.x)) && (r.y<=Math.max(p.y,q.y));
    }

    private boolean doIntersect(Point p1,Point q1,Point p2,Point q2) {

        int o1=orientation(p1,p2,q1);
        int o2=orientation(p1,q2,q1);
        int o3=orientation(p2,p1,q2);
        int o4=orientation(p2,q1,q2);

        if(o1!=o2 && o3!=o4){
            return true;
        }

        if(o1==0) {
            return onSegment(p1,p2,q1);
        } else if(o2==0) {
            return onSegment(p1,q2,q1);
        } else if(o3==0) {
            return onSegment(p2,p1,q2);
        } else {
            return  o4==0 && onSegment(p2,q1,q2);
        }

    }

    private boolean isInside(Point p,Point[] polygon) {

        inf = new Point(INFINITY,p.y);
        int count=0,i=0;
        int n = polygon.length;
        do{

            int next = (i+1)%n;
            // Check if the line segment from 'p' to 'extreme' intersects
            // with the line segment from 'polygon[i]' to 'polygon[next]'
            if(doIntersect(p,inf,polygon[i],polygon[next])) {

                // If the point 'p' is collinear with line segment 'i-next',
                // then check if it lies on segment. If it lies, return true,
                // otherwise false
                if(orientation(p,polygon[i],polygon[next])==0) {
                    return onSegment(p,polygon[i],polygon[next]);
                }

                count++;
            }
            i=next;

        }while (i!=0);

        return ((count&1)!=0);

    }

    public static void main(String[] args) {
        Point polygon[] = {new Point(0, 0), new Point(10, 0), new Point(10, 10), new Point(0, 10)};
        Point p = new Point(5,5);
        System.out.print(new PointInside().isInside(p,polygon));
    }

}

/*

1) Draw a horizontal line to the right of each point and extend it to infinity

1) Count the number of times the line intersects with polygon edges.

2) A point is inside the polygon if either count of intersections is odd or
   point lies on an edge of polygon.  If none of the conditions is true, then
   point lies outside.


   Time Complexity: O(n) where n is the number of vertices in the given polygon.



 */