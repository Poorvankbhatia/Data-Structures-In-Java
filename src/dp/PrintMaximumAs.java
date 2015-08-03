/*
   Revise and practice again
   
   Below is the problem statement.

    Imagine you have a special keyboard with the following keys:
    Key 1:  Prints 'A' on screen
    Key 2: (Ctrl-A): Select screen
    Key 3: (Ctrl-C): Copy selection to buffer
    Key 4: (Ctrl-V): Print buffer on screen appending it
                     after what has already been printed.

    If you can only press the keyboard for N times (with the above four
    keys), write a program to produce maximum numbers of A's. That is to
    say, the input parameter is N (No. of keys that you can press), the
    output is M (No. of As that you can produce).
    Examples:

    Input:  N = 3
    Output: 3
    We can at most get 3 A's on screen by pressing
    following key sequence.
    A, A, A

    Input:  N = 7
    Output: 9
    We can at most get 9 A's on screen by pressing
    following key sequence.
    A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V

    Input:  N = 11
    Output: 27
    We can at most get 27 A's on screen by pressing
    following key sequence.
    A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V, Ctrl A,
    Ctrl C, Ctrl V, Ctrl V
   
 */

package dp;

import java.util.Arrays;

public class PrintMaximumAs {

    private static int[] screen = new int[30];

    public static void main(String[] args) {

        System.out.println("for 10 keystrokes - " + findOptimal(14));

    }

    private static int findOptimal(int N) {

        if (N <= 6) {
            return N;
        }

        for (int n = 1; n <= 6; n++) {
            screen[n - 1] = n;
        }

        for (int n = 7; n <= N; n++) {

            screen[n - 1] = 0;

            for (int b = n - 3; b >= 1; b--) {

                //Dry run it to understand
                int currentNo = (n - b - 1) * screen[b - 1];
                if (currentNo > screen[n - 1]) {
                    screen[n - 1] = currentNo;
                }
            }


        }


        System.out.println(Arrays.toString(screen));

        return screen[N - 1];

    }

}

/*

If you are at breakpoint - b; then till that point you have achieved findoptimal(b) number of A's
Now you have (N-b) chances left. Now you will do ctrl+a and ctrl+c.
So you have findoptimal(b) in your buffer which can be used for copy.
After these two key press you have N-b-2 chances left.
So number of times you can do ctrl+v is N-b-2
So you will get further (N-b-2)*buffer i.e. (N-b-2)*findoptimal(b) of A's

totoal number of A's =
A's till break point b + A's after breakpoint b
=findoptimal(b)+ (N-b-2)*findoptimal(b)
=(N-b-2+1)findoptimal(b)
=(N-b-1)findoptimal(b)

 */