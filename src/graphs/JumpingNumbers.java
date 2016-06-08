/*


Print all Jumping Numbers smaller than or equal to a given value
A number is called as a Jumping Number if all adjacent digits in it differ by 1. 
The difference between ‘9’ and ‘0’ is not considered as 1.
All single digit numbers are considered as Jumping Numbers. For example 7, 8987 and 4343456 are
Jumping numbers but 796 and 89098 are not.

Given a positive number x, print all Jumping Numbers smaller than or equal to x. The numbers can be printed in any order.

Example:

Input: x = 20
Output:  0 1 2 3 4 5 6 7 8 9 10 12

Input: x = 105
Output:  0 1 2 3 4 5 6 7 8 9 10 12
         21 23 32 34 43 45 54 56 65
         67 76 78 87 89 98 101

Note: Order of output doesn't matter, 
i,e., numbers can be printed in any order

 */

package graphs;

import java.util.LinkedList;
import utility.Queue;

/**
 * Created by poorvank on 1/16/16.
 */
public class JumpingNumbers {

    public static void main(String[] args) {

        int x = 50;

        System.out.print("0 ");

        //In case x <9
        for (int i = 1; i <= 9 && i <= x; i++) {
            printJumpNo(i, x);
        }

        System.out.println();

        printOrderedJumpNo(x);

    }

    //Doing a BFS
    private static void printJumpNo(int startNum, int x) {

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(startNum);

        while (!queue.isEmpty()) {

            startNum = queue.dequeue();
            int lastDigit = startNum % 10;

            if (startNum <= x) {

                System.out.print(startNum + " ");

                if (lastDigit == 0) {
                    queue.enqueue(startNum * 10 + (lastDigit + 1));
                } else if (lastDigit == 9) {
                    queue.enqueue(startNum * 10 + (lastDigit - 1));
                } else {
                    queue.enqueue(startNum * 10 + (lastDigit + 1));
                    queue.enqueue(startNum * 10 + (lastDigit - 1));
                }

            }

        }

    }

    //Doing a BFS
    private static void printOrderedJumpNo(int x) {

        int i = 1;
        Queue<Integer> queue = new Queue<>();

        while (i <= 9 && i <= x) {
            queue.enqueue(i);
            i++;
        }

        while (!queue.isEmpty()) {

            int startNum = queue.dequeue();
            int lastDigit = startNum % 10;

            if (startNum <= x) {

                System.out.print(startNum + " ");

                if (lastDigit == 0) {
                    queue.enqueue(startNum * 10 + (lastDigit + 1));
                } else if (lastDigit == 9) {
                    queue.enqueue(startNum * 10 + (lastDigit - 1));
                } else {
                    queue.enqueue(startNum * 10 + (lastDigit + 1));
                    queue.enqueue(startNum * 10 + (lastDigit - 1));
                }

            }

        }

    }

}

/*

An Efficient Solution can solve this problem in O(k) time where k is number of Jumping Numbers smaller than or equal to x.
 The idea is use BFS or DFS.

Assume that we have a graph where the starting node is 0 and we need to traverse it from the start node to all the reachable
 nodes.

With the restrictions given in the graph about the jumping numbers, what do you think should be the restrictions defining
 the next transitions in the graph.

Lets take a example for input x = 90

Start node = 0
From 0, we can move to 1 2 3 4 5 6 7 8 9 
[these are not in our range so we don't add it]

Now from 1, we can move to 12 and 10 
From 2, 23 and 21
From 3, 34 and 32
.
.
.
.
.
.
and so on.

 */