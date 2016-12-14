/*

Given a sequence of moves for a robot, check if the sequence is circular or not.
A sequence of moves is circular if first and last positions of robot are same. A move can be on of the following.

  G - Go one unit
  L - Turn left
  R - Turn right

Examples:

Input: path[] = "GLGLGLG"
Output: Given sequence of moves is circular

Input: path[] = "GLLG"
Output: Given sequence of moves is circular

 */
package miscellaneous;

/**
 * Created by poorvank on 14/12/16.
 */
public class RobotMotion {

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    public static boolean isCircular(String moves) {

        int x = 0,y=0,dir=0;

        for (Character c : moves.toCharArray()) {

            if(c=='R') {
                dir = (dir+1)%4;
            } else if(c=='L') {
                dir = (4 + dir -1)%4;
            } else if(c=='G') {
                if(dir==0) {
                    y++;
                } else if(dir==1) {
                    x++;
                } else if(dir==2) {
                    y--;
                } else {
                    x--;
                }
            }

        }

        return x==0 && y==0;

    }

    public static void main(String[] args) {
        System.out.println(isCircular("GLGLGLG"));
    }

}

/*

The idea is to consider the starting position as (0, 0) and direction as East (We can pick any values for these).
If after the given sequence of moves, we come back to (0, 0), then given sequence is circular, otherwise not.

           N
           |
           |
   W -------------- E
           |
           |
           S
The move ‘G’ changes either x or y according to following rules.
a) If current direction is North, then ‘G’ increments y and doesn’t change x.
b) If current direction is East, then ‘G’ increments x and doesn’t change y.
c) If current direction is South, then ‘G’ decrements y and doesn’t change x.
d) If current direction is West, then ‘G’ decrements x and doesn’t change y.

The moves ‘L’ and ‘R’, do not change x and y coordinates, they only change direction according to following rule.
a) If current direction is North, then ‘L’ changes direction to West and ‘R’ changes to East
b) If current direction is East, then ‘L’ changes direction to North and ‘R’ changes to South
c) If current direction is South, then ‘L’ changes direction to East and ‘R’ changes to West
d) If current direction is West, then ‘L’ changes direction to South and ‘R’ changes to North.

ALTERNATE METHOD:

Each run(one run is executing all commands of the given string once) changes two things:
the direction which the robot looks to and its position(that is, each run shifts it by some vector
(the direction of this vector depends on the its initial direction before the run) and rotates it).
The number of possible directions is 4. Thus, after running the simulation 4 times it looks in the
same direction as it did initially(each rub rotates it by the same angle).
That's why 4 consecutive runs just shift it by some vector without any rotation.
Thus, we can just run the simulation 4 times in a row and see if it stopped in the original point.
If it did, we can find the minimum circle that contains its path. Otherwise, no such circle exists.

 */
