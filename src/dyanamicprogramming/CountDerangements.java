/*

A Derangement is a permutation of n elements, such that no element appears in its original position.
For example, a derangement of {0, 1, 2, 3} is {2, 3, 1, 0}.

Given a number n, find total number of Derangements of a set of n elements.

Examples:

Input: n = 2
Output: 1
For two elements say {0, 1}, there is only one
possible derangement {1, 0}

Input: n = 3
Output: 2
For three elements say {0, 1, 2}, there are two
possible derangements {2, 0, 1} and {1, 2, 0}

Input: n = 4
Output: 9
For four elements say {0, 1, 2, 3}, there are 9
possible derangements {1, 0, 3, 2} {1, 2, 3, 0}
{1, 3, 0, 2}, {2, 3, 0, 1}, {2, 0, 3, 1}, {2, 3,
1, 0}, {3, 0, 1, 2}, {3, 2, 0, 1} and {3, 2, 1, 0}


 */

package dyanamicprogramming;

/**
 * Created by poorvank on 23/10/16.
 */
public class CountDerangements {

    public int count(int n) {

        if(n==0 || n==1) {
            return 0;
        }

        int[] res = new int[n+1];
        res[0] = res[1] =  0;
        res[2] = 1;

        for (int i=3;i<=n;i++) {
            res[i] = (i-1)*(res[i-1]+res[i-2]);
        }

        return res[n];

    }

    public static void main(String[] args) {
        System.out.println(new CountDerangements().count(5));
    }

}


/*


Let D(n) represents the number of derangements of n letters, we have D(1) = 0, D(2) = 1, and D(3) = 2.

Suppose we want to determine the number of derangement of the n integers 1,2,...,n where n > 2.
Lets consider some element k and move it into the first position. We thus have started a derangement,
for 1 is not in its natural position. Where could 1 be placed? There are two cases we could consider:
either 1 is in position k or 1 is not in position k.

If 1 is in position k, here's what we know: The integers 1 and k have simply traded positions and there are
(n-2) integers yet to derange. This can be done in D(n-2) ways.

If 1 is not in position k, there are now (n-1) integers yet to derange. This can be done in D(n-1) ways.

Putting this together, we have D(n-1) + D(n-2) possible derangements when k is in the first position.
How many different integers could we put in position 1 and carry out this process? All the integers except 1. i.e. (n-1) different integers.

This yields the recursive formula D(n) = (n-1)[D(n-1) + D(n-2)]. As long as we know D(1) = 0 and D(2) = 1, we
can generate subsequent values for D(n).




Let countDer(n) be count of derangements for n elements. Below is recursive relation for it.

countDer(n) = (n-1)*[countDer(n-1) + countDer(n-2)]
How does above recursive relation work?
There are n – 1 ways for element 0 (this explains multiplication with n-1).

Let 0 be placed at index i. There are now two possibilities, depending on whether or not element i is placed at 0 in return.

i is placed at 0: This case is equivalent to solving the problem for n-2 elements as two elements have just swapped their positions.
i is not placed at 0: This case is equivalent to solving the problem for n-1 elements as now there are n-1 elements, n-1 positions
and every element has n-2 choices


----------

from the wiki, The way of counting derangements is,

Suppose that there are n persons numbered 1, 2, ..., n. Let there be n hats also numbered 1, 2, ..., n.
We have to find the number of ways in which no one gets the hat having same number as his/her number.
Let us assume that the first person takes hat i. There are n − 1 ways for the first person to make such a choice.
There are now two possibilities, depending on whether or not person i takes hat 1 in return:

Person i does not take the hat 1. This case is equivalent to solving the problem with n − 1 persons and n − 1 hats:
each of the remaining n − 1 people has precisely 1 forbidden choice from among the remaining n − 1 hats (i's forbidden choice is hat 1).
Person i takes the hat 1. Now the problem reduces to n − 2 persons and n − 2 hats.


Lets call the derangement function f for clarity. At f(n), there are n hats and n people.
Everyone can choose from n-1 hats. Person 1 takes hat i from n-1 choices. Person i still has n-1 hats
to choose from and everyone else has n-2 has to choose from (they can't choose their own hat or i).

Now we need two cases for what person i does. Think of this as

Person i takes hat 1
Person i doesn't take hat 1 and we don't know what they'll take until later
In case 2, we know person i doesn't take hat 1, but nothing more. Previously we knew that person i had n-1 choices, now he has n-2,
just like everyone else. This means we can calculate f(n-1) for this case. In case 1, person i is no longer forbidden to take hat 1.
In essence, we know that person i and person 1 have swapped hats and no longer need to be matched, thus f(n-2).

Either of these cases is possible, so we have a recurrence that multiplies the (n-1) choices by the possibility of either happenning,
f(n) = (n-1)(f(n-1) + f(n-2))

 */