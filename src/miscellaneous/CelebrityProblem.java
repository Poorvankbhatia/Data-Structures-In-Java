/*

In a party of N people, only one person is known to everyone. 
Such a person may be present in the party, if yes, (s)he doesn’t know anyone in the party.
 We can only ask questions like “does A know B? “. Find the stranger (celebrity) in minimum number of questions.

We can describe the problem input as an array of numbers/characters representing persons in the party.
 We also have a hypothetical function HaveAcquaintance(A, B) which returns true if A knows B, false otherwise.
  How can we solve the problem, try yourself first.

We measure the complexity in terms of calls made to HaveAcquaintance().

 */

package miscellaneous;

import stack.ArrayStack;
import stack.Stack;

/**
 * Created by poorvank on 8/5/15.
 */
public class CelebrityProblem {

    static int[][] knowTable = new int[][]{{0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 1, 0}};

    static int peopleCount = 4;

    public static void main(String[] args) {

        findCelebrity();


    }

    private static void findCelebrity() {

        Stack<Integer> stack = new ArrayStack<>();

        for (int i = 0; i < peopleCount; i++) {
            stack.push(i);
        }


        while (stack.size() != 1) {

            int a = stack.pop(), b = -1;
            if (!stack.isEmpty()) {
                b = stack.pop();
            }

            if (knows(a, b)) {
                stack.push(b);
            } else {
                stack.push(a);
            }


        }

        int celeb = stack.pop();

        if (checkCelebrity(celeb, stack)) {
            System.out.println("celeb is - " + celeb);
        } else {
            System.out.println("No celebrity present");
        }

    }

    /**
     * Returns true if a knows b
     *
     * @param a
     * @param b
     * @return
     */
    private static boolean knows(int a, int b) {

        if (b == -1) {
            return false;
        }

        return knowTable[a][b] == 1;

    }

    private static boolean checkCelebrity(int celeb, Stack<Integer> stack) {

        for (int i = 0; i < peopleCount; i++) {
            if (i != celeb) {
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {

            int a = stack.pop();

            if (!knows(a, celeb)) {

                return false;
            }

            if (knows(celeb, a)) {
                return false;
            }

        }

        return true;

    }

}


/*

We have following observation based on elimination technique

If A knows B, then A can’t be celebrity. Discard A, and B may be celebrity.
If A doesn’t know B, then B can’t be celebrity. Discard B, and A may be celebrity.
Repeat above two steps till we left with only one person.
Ensure the remained person is celebrity. (Why do we need this step?)
We can use stack to verity celebrity.

Push all the celebrities into a stack.
Pop off top two persons from the stack, discard one person based on return status of HaveAcquaintance(A, B).
Push the remained person onto stack.
Repeat step 2 and 3 until only one person remains in the stack.
Check the remained person in stack doesn’t have acquaintance with anyone else.
We will discard N elements utmost . If the celebrity is present in the party, we will call
HaveAcquaintance() 3(N-1) times. Here is code using stack.

 */