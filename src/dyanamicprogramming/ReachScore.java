/*

Consider a game where a player can score 3 or 5 or 10 points in a move.
 Given a total score n, find number of ways to reach the given score.

 */

package dyanamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class ReachScore {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the maximum points - ");
        int n = sc.nextInt();

        int[] arr = new int[n + 1];

        arr[0] = 1;

        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i] + arr[i - 3];
        }
        for (int i = 5; i <= n; i++) {
            arr[i] = arr[i] + arr[i - 5];
        }
        for (int i = 10; i <= n; i++) {
            arr[i] = arr[i] + arr[i - 10];
        }

        System.out.println(Arrays.toString(arr) + " \nNo of ways  - " + arr[n]);

    }

}

/*

The idea is to create a table of size n+1 to store counts of all scores from 0 to n. 
For every possible move (3, 5 and 10), increment values in table.



For exercise problem we can use this recurrence relation

T(n) = T(n-3)+T(n-5)+T(n-10)

T(0)=T(1)=T(2)=T(4)=T(7)=0
T(3)=T(5)=T(6)=T(9)=1
T(8)=T(10)=2

Now use memoization technique to compute n>10 .

 */