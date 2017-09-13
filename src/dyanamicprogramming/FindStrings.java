/*

Given a length n, return the number of strings of length n that can be made up of the letters 'a', 'b', and 'c',
where there can only be a maximum of 1 'b's and can only have up to two consecutive 'c's

Example:
findStrings(3) returns 19
since the possible combinations are: aaa,aab,aac,aba,abc,aca,acb,baa,bac,bca,caa,cab,cac,cba,cbc,acc,bcc,cca,ccb
and the invalid combinations are:
abb,bab,bba,bbb,bbc,bcb,cbb,ccc


 */
package dyanamicprogramming;

/**
 * Created by poorvank.b on 13/09/17.
 */
public class FindStrings {

    public int countStrings(int n) {

        int[] containsB = new int[n+1];
        int[] doesNotContainB = new int[n+1];

        containsB[0]=0;doesNotContainB[0]=0;
        containsB[1]=doesNotContainB[0]*1;
        doesNotContainB[1]=2;//a,c
        containsB[2]=doesNotContainB[1]*2;
        doesNotContainB[2]=4;//aa,ac,ca,cc
        /*
            1. ‘a’ + A type strings with length i - 1    //aaa,aac,aca,acc
            2. ‘ca’ + A type strings with length i - 2   //caa,cac
            3. ‘cca’ + A type strings with length i - 3  //cca

         */
        doesNotContainB[3]=7;
        containsB[3]=3*doesNotContainB[2];

        for (int i=4;i<=n;i++) {
            containsB[i]=doesNotContainB[i-1]*i;
            doesNotContainB[i]=doesNotContainB[i-1]+doesNotContainB[i-2]+doesNotContainB[i-3];
        }

        return containsB[n]+doesNotContainB[n];

    }

    public static void main(String[] args) {
        System.out.println(new FindStrings().countStrings(5));
    }

}

/*

O(n) solution is provided.

We can divide strings in two types;
A type which does not contain ‘b’
and B type which contain ‘b’.
We can define matrix A and B as follows.

A[i]: the number of strings of length i in A type.
B[i]: the number of strings of length i in B type.

And the answer is A[n] + B[n]

Because B type strings with length i can be generated
by picking any sting in A type strings with length i-1,
and put ‘b’ in any position in the string.
There are total i positions to insert ‘b’,
thus, following equation holds between A[i] and B[i].

B[i] = i * A[i-1]

So, it is enough to compute A[i].
For considering strings in A, there are three possible
prefixes which end with ‘a’.
(because there is no constraint in the substring after ‘a’)

1. ‘a’ + A type strings with length i - 1
2. ‘ca’ + A type strings with length i - 2
3. ‘cca’ + A type strings with length i - 3

i.e., A[i] = A[i-1] + A[i-2] + A[i-3]
where A[1] = 2, A[2] = 4, A[3] = 7

We can compute matrix A iteratively.

As an example, the number of strings of length 3 is
A[3] + B[3] = A[3] + 3 * A[2] = 7 + 3*4 = 19.

 */