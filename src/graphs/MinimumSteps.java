/*

Find a number in minimum steps
Given an infinite number line from -INFINITY to +INFINITY and we are on zero. We can move n steps either side at each n’th time.

1st time; we can move only 1 step to both ways, means -1 1;

2nd time we can move 2 steps  from -1 and 1;
-1 :  -3 (-1-2)  1(-1+2)
 1 :  -1 ( 1-2)  3(1+2)

3rd time we can move 3 steps either way from -3, 1, -1, 3
-3:  -6(-3-3) 0(-3+3)
1:   -2(1-3)   4(1+3)
-1:  -4(-1-3)  2(-1+3)
3:     0(0-3)   6(3+3)

Find the minimum number of steps to reach a given number n.
Examples:

Input : n = 10
Output : 4
We can reach 10 in 4 steps,  1, 3, 6, 10


Input : n = 13
Output : 5
We can reach 10 in 4 steps,  -1, 2, 5, 9, 14

 */

package graphs;

import utility.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 14/08/16.
 */
public class MinimumSteps {


    private int MAX = Integer.MAX_VALUE;
    private int MIN = Integer.MIN_VALUE;

    private class Node {
        int value;
        int level;

        public Node(int value, int level) {
            this.value = value;
            this.level = level;
        }
    }

    public int calculateMinSteps(int no) {

        Queue<Node> queue = new Queue<>();
        queue.enqueue(new Node(0,1));
        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {

            Node current = queue.dequeue();

            if(current.value==no) {
                return current.level-1;
            }
            if(current.level>=MAX || current.level<=MIN) {
                break;
            }

            queue.enqueue(new Node(current.value+current.level,current.level+1));
            queue.enqueue(new Node(current.value-current.level,current.level+1));

        }

        return -1;
    }

    public static void main(String[] args) {
        int no = 45;

        MinimumSteps ms = new MinimumSteps();
        System.out.println(ms.calculateMinSteps(no));
    }

}

/*

This problem can be modeled as tree. We put initial point 0 at root, 1 and -1 as children of root.
Next level contains values at distance 2 and so on.

              0
            /   \
         -1       1
        /  \     /  \
       1   -3   -1   3
     /  \  / \  / \  / \
The problem is now to find the closes node to root with value n.

A simple BFS can be used to find the closest value.

 */