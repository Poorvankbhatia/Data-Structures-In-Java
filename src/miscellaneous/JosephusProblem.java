
/*

There are n people standing in a circle waiting to be executed.
The counting out begins at some point in the circle and proceeds around the circle in a fixed direction.
In each step, a certain number of people are skipped and the next person is executed.
The elimination proceeds around the circle (which is becoming smaller and smaller as the executed people are removed),
until only the last person remains, who is given freedom. Given the total number of persons n and a number k which indicates
that k-1 persons are skipped and kth person is killed in circle. The task is to choose the place in the initial circle so that
you are the last one remaining and so survive.

For example, if n = 5 and k = 2, then the safe position is 3. Firstly, the person at position 2 is killed, then person at position
4 is killed, then person at position 1 is killed. Finally, the person at position 5 is killed. So the person at position 3 survives.
If n = 7 and k = 3, then the safe position is 4. The persons at positions 3, 6, 2, 7, 5, 1 are killed in order, and person at position 4 survives.

http://www.exploringbinary.com/powers-of-two-in-the-josephus-problem/

 */

package miscellaneous;

import utility.Queue;

import java.util.Scanner;

/**
 * Created by poorvank on 23/07/16.
 */
public class JosephusProblem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter k : ");
        int k = scanner.nextInt();

        System.out.println("Enter n : ");
        int n = scanner.nextInt();

        int[] arr = new int[n];

        for (int i=0;i<n;i++) {
            arr[i] = i+1;
        }

        Queue<Integer> queue = new Queue<>();

        for (Integer i : arr) {
            queue.enqueue(i);
        }

        while (!queue.isEmpty()) {
            if(queue.getSize()==1) {
                System.out.println("Position saved finally : " + queue.dequeue());
                break;
            }
            for (int i=0;i<k-1;i++) {
                queue.enqueue(queue.dequeue());
            }
            System.out.println("Killed = " + queue.dequeue());
        }


    }

}
