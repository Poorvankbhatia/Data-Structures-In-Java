/*

A sender sends a binary string to a receiver meanwhile he encrypt the digits. 
You are given a encrypted form of string. Now, the receiver needs to decode the string, 
and while decoding there were 2 approaches.

Let the encrypted binary string be P[] and actual string be S[].

First, receiver starts with first character as 0; 
S[0] = 0 // First decoded bit is 1
Remaining bits or S[i]s are decoded using following formulas.
P[1] = S[1] + S[0] 
P[2] = S[2] + S[1] + S[0] 
P[3] = S[3] + S[2] + S[1] 
and so on ...

Second, Receiver starts with first character as 1; 
S[0] = 1 // First decoded bit is 1
Remaining bits or S[i]s are decoded using following formulas.
P[1] = S[1] + S[0]  
P[2] = S[2] + S[1] + S[0] 
P[3] = S[3] + S[2] + S[1] 
and so on ... 
You need to print two string generated using two different after evaluation from both first and second technique. 
If any string contains other that binary numbers you need to print NONE.

Input1; 0123210
Output: 0111000, NONE

Explanation for first output
S[0] = 0, 
P[1] = S[1] + S[0], S[1] = 1
P[2] = S[2] + S[1] + S[0], S[2] = 1
P[3] = S[3] + S[2] + S[1], S[3] = 1
P[4] = S[4] + S[3] + S[2], S[4] = 0 
P[5] = S[5] + S[4] + S[3], S[5] = 0
P[6] = S[6] + S[5] + S[4], S[6] = 0

Explanation for second output 
S[0] = 1,
P[1] = S[1] + S[0], S[1] = 0
P[2] = s[2] + S[1] + S[0], S[2] = 1
P[3] = S[3] + S[2] + S[1], S[3] = 2, not a binary character so NONE.

 */

package interviewProgram;

import java.util.Arrays;

/**
 * Created by poorvank on 4/27/15.
 */
public class SenderReciever {

    public static void main(String[] args) {

        int[] encryptedArray = new int[]{0, 1, 2, 3, 2, 1, 0};
        decryptUtil(encryptedArray, 0);
        decryptUtil(encryptedArray, 1);

    }


    private static void decryptUtil(int[] encryptedArray, int first) {

        int[] decryptedArray = new int[encryptedArray.length];

        decryptedArray[0] = first;

        int second = 0;

        for (int i = 1; i < encryptedArray.length; i++) {

            decryptedArray[i] = encryptedArray[i] - first - second;

            if (decryptedArray[i] != 0 && decryptedArray[i] != 1) {
                System.out.println("NONE");
                return;
            }

            second = first;
            first = decryptedArray[i];

        }

        System.out.println(Arrays.toString(decryptedArray));

    }

}


/*

The idea to solve this problem is simple, we keep track of last two decoded bits. 
The current bit S[i] can always be calculated by subtracting last two decoded bits from P[i].

 */