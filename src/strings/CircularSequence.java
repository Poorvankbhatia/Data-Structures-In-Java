package strings;

/*

Given a sequence of moves for a robot, check if the sequence is circular or not. 
A sequence of moves is circular if first and last positions of robot are same.
A move can be on of the following.

  G - Go one unit
  L - Turn left
  R - Turn right 

 Examples:

Input: path[] = "GLGLGLG"
Output: Given sequence of moves is circular

Input: path[] = "GLLG"
Output: Given sequence of moves is circular

 */

import java.util.Scanner;

/**
 * Created by poorvank on 3/26/15.
 */
public class CircularSequence {

    private final static int NORTH = 0;
    private final static int EAST = 1;
    private final static int SOUTH = 2;
    private final static int WEST = 3;


    public static void main(String[] args) {

        System.out.println("Enter sequence to be determined :");
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        int x = 0, y = 0, dir = NORTH;

        for (char ch : line.toCharArray()) {
            //Logic!!!
            if (ch == 'L') {
                dir = (dir + 3) % 4;
            } else if (ch == 'R') {
                dir = (dir + 1) % 4;
            } else { //ch == 'G'
                if (dir == NORTH) {
                    y++;
                } else if (dir == EAST) {
                    x++;
                } else if (dir == SOUTH) {
                    y--;
                } else {
                    x--;
                }

            }

        }

        if (x == 0 && y == 0) {
            System.out.println("Yup, Circular");
        } else {
            System.out.println("Nope");
        }

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

 */