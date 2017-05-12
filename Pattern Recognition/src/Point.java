/*************************************************************************
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point>{
    // compare points by slope
    public final Comparator<Point> BY_SLOPE_ORDER;    // YOUR DEFINITION HERE

    private final int x; // x coordinate
    private final int y; // y coordinate
    float angle;

    public int getX() { return x; }
    public int getY() { return y; }

    // constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        angle = 0;
        BY_SLOPE_ORDER = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                float angle = (float) Math.toDegrees(Math.atan2(Point.this.y - o1.getY(), Point.this.x - o1.getX()));

                if(angle < 0){
                    angle += 360;
                }
                if(angle >= 180){
                    angle-=180;
                }
                //stores all angles into new Point array (Point constructor w/ x,y, and angle)
                o1.angle = angle;

                //finds o2 angle, repeat steps
                float angle2 = (float) Math.toDegrees(Math.atan2(getY() - o2.getY(), getX() - o2.getX()));

                if(angle2 < 0){
                    angle2 += 360;
                }
                if(angle2 >= 180){
                    angle2-=180;
                }
                o2.angle = angle2;
                //compares angles
                if(o1.angle == o2.angle) {
                    return o1.compareTo(o2);
                }
                if(o1.angle > o2.angle) {
                    return 1;
                }
                else return -1;
            }
        };

    }

  /* public Point(){
        this.x = x;
        this.y = y;
        float angle = 0;
   }*/

    public String toString(){
        return x + ", " + y;
    }

    public static double findSlope(Point a, Point b){
        double slope = (double) (b.y - a.y)/ (double) (b.x - a.x);
        return slope;
    }


    // are the 3 points p, q, and r collinear?
    public static boolean areCollinear(Point p, Point q, Point r) {
        /* YOUR CODE HERE */
        if(findSlope(p,q) == findSlope(q,r) && findSlope(p,q) == findSlope(p,r)){
            return true;
        }
        else return false;
    }

    // are the 4 points p, q, r, and s collinear?
    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        /* YOUR CODE HERE */
        if(findSlope(p,q) == findSlope(q,r) && findSlope(p,q) == findSlope(q,r) && findSlope(p,q) == findSlope(r,s)){
            return true;
        }
        else return false;
    }

    // is this point lexicographically smaller than that one?
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        //if equal, 0
        //if less = -1
        //if greater =1

        if(this.x < that.x){
            return -1;
        }
        else if(this.x > that.x){
            return 1;
        }
        else if(this.x == that.x) {
            if (this.y < that.y) {
                return -1;
            } else if (this.y > that.y) {
                return 1;
            } else if(this.y == that.y) {return 0;}
        }
        return 0;
    }

}
