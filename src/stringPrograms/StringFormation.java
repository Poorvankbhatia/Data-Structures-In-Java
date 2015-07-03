/*

Print all possible strings of length k that can be formed from a set of n characters
Given a set of characters and a positive integer k, print all possible strings of length k that 
can be formed from the given set.

Examples:

Input: 
set[] = {'a', 'b'}, k = 3

Output:
aaa
aab
aba
abb
baa
bab
bba
bbb


Input: 
set[] = {'a', 'b', 'c', 'd'}, k = 1
Output:
a
b
c
d


 */

package stringPrograms;

/**
 * Created by poorvank on 4/16/15.
 */
public class StringFormation {

    public static void main(String[] args) {

        char[] set = new char[]{'a', 'b'};
        int k = 3;
        String str = "";
        printString(set, k, str);
    }

    public static void printString(char[] set, int k, String str) {

        if (k == 0) {
            System.out.println(str);
            return;
        }

        for (int i = 0; i < set.length; i++) {

            printString(set, k - 1, str + set[i]);

        }

    }

}


/*


For a given set of size n, there will be n^k possible strings of length k. 
The idea is to start from an empty output string (we call it prefix in following code). 
One by one add all characters to prefix. For every character added, print all possible strings with current prefix 
by recursively calling for k equals to k-1.

 */