/*
You’ve been given a list of words to study.
how you can concatenate the words to generate the lexicographically lowest possible string.

As input for playing this game you will receive a text file containing an integer N, the number of
word sets you need to play your game against. This will be followed by N word sets, each starting with an integer M,
the number of words in the set, followed by M words. All tokens in the input will be separated by some whitespace and,
aside from N and M, will consist entirely of lowercase letters.

Output
Your submission should contain the lexicographically shortest strings for each corresponding word set, one per line and in order.


Same Questions as:

Arrange given numbers to form the biggest number
Given an array of numbers, arrange them in a way that yields the largest value. For example, if the given numbers are {54, 546, 548, 60},
the arrangement 6054854654 gives the largest value. And if the given numbers are {1, 34, 3, 98, 9, 76, 45, 4}, then the arrangement 998764543431
gives the largest value.

The idea is to use any comparison based sorting algorithm. In the used sorting algorithm, instead of using the default comparison,
write a comparison function myCompare() and use it to sort numbers. Given two numbers X and Y, how should myCompare() decide which number to
put first – we compare two numbers XY (Y appended at the end of X) and YX (X appended at the end of Y). If XY is larger, then X should come before
Y in output, else Y should come before. For example, let X and Y be 542 and 60. To compare X and Y, we compare 54260 and 60542. Since 60542 is
greater than 54260, we put Y first.




 */

package strings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by poorvank on 07/02/17.
 */
public class LexicographicLowestString {

    private static class CustomComparator implements Comparator<String> {

        @Override
        public int compare(String s1,String s2) {

            String s1s2 = s1+s2;
            String s2s1 = s2+s1;

            return s1s2.compareTo(s2s1);
        }

    }

    public static String lowestString(String[] strings) {

        Arrays.sort(strings,new CustomComparator());

        StringBuilder sb = new StringBuilder();

        for (String s : strings) {
            sb.append(s);
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        String[] strings = new String[]{"jibw","ji","jp","bw","jibw"};
        System.out.print(lowestString(strings));
    }

}

/*

he first most natural way to approach this problem is sorting. Most people will reason that you can sort and
concatenate each individual word together to form the lexicographically smallest string. This is incorrect, as illustrated in one of the sample inputs:

jibw ji jp bw jibw
By sorting and concatenate, the answer is:

bwjijibwjibwjp,
while the correct answer should be:

bwjibwjibwjijp.


Notice in the above words, “ji” is the prefix of “jibw“. The “sort and concatenate” method definitely does not work
when there is a case where a word is a prefix of one or more other words.



We make an easy observation that if all words in the list are of equal length, then sort + concatenate must yield the
smallest dictionary order. In fact, a better argument would be:

If no word appears to be a prefix of any other words, then the simple sort + concatenate must yield the smallest dictionary order string.
To solve this problem correctly, we must also handle the special case where a word appears as a prefix of other words. One efficient and
easy (non-trivial to prove but easy to reason) solution for this problem is to re-define the order relation of two words, s1 and s2, as:

s1 is less than s2 iff (if and only if)


Here is a concrete example why this works. We use an example where the list of words are:

ji jibw jijibw
By the definition of s1 is less than s2 iff s1+s2 < s2+s1, we found that the lowest ordered word in the list is “jibw“.
This is because “jibwji” < "jijibw” and “jibwjijibw” < "jijibwjibw“.

Now, the key to understand why the order relation s1+s2 < s2+s1 yields the smallest dictionary order is:

We have found the smallest-ordered word such that s1+x < x+s1. Therefore, it is impossible to swap the words to yield a smaller dictionary order.
For a case with more words, then this order relation holds: s1+x < x+s1, and x+y < y+x. As swapping at any point could not possibly
yield a smaller dictionary order, therefore s1+x+y must yield the smallest dictionary order.
This result can be generalized to all M words by induction, due to the transitive property mentioned above.

s1 + s2 < s2 + s1.

 */