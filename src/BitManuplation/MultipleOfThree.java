package BitManuplation;

import java.util.Scanner;

/**
 * Created by poorvank on 5/2/15.
 */
public class MultipleOfThree {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number");
        int num = scanner.nextInt();
        
        System.out.println("Is a multiple of three - " + isMultiple(num));
    }
    
    private static boolean isMultiple(int n) {
        
        if(n<0) {
          n = Math.abs(n);   
        }
        if(n==0) {
            return true;
        } 
        if(n==1) {
            return false;
        }
        
        int oddCount=0,evenCount=0;
        
        while (n>0) {
            
            //If odd bit is set we increment odd count
            if((n&1)!=0) {
                oddCount++;
            }
            n = n >> 1;
            
            //If even bit is set we increment even count
            if((n & 1)!=0) {
                evenCount++;
            }
            n = n>>1;
            
        }
        
        if(Math.abs(oddCount-evenCount)%3==0) {
            return true;
        }
        return false;
        
    }
    
}


/*

There is a pattern in binary representation of the number that can be used to find if number is a multiple of 3. 
If difference between count of odd set bits (Bits set at odd positions) and even set bits is multiple of 3 then is the number.

Example: 23 (00..10111)
1) Get count of all set bits at odd positions (For 23 it’s 3).
2) Get count of all set bits at even positions (For 23 it’s 1).
3) If difference of above two counts is a multiple of 3 then number is also a multiple of 3.

(For 23 it’s 2 so 23 is not a multiple of 3)

Take some more examples like 21, 15, etc…

Algorithm: isMutlipleOf3(n)
1) Make n positive if n is negative.
2) If number is 0 then return 1
3) If number is 1 then return 0
4) Initialize: odd_count = 0, even_count = 0
5) Loop while n != 0
    a) If rightmost bit is set then increment odd count.
    b) Right-shift n by 1 bit
    c) If rightmost bit is set then increment even count.
    d) Right-shift n by 1 bit
6) return isMutlipleOf3(odd_count - even_count)
Proof:
Above can be proved by taking the example of 11 in decimal numbers. 
(In this context 11 in decimal numbers is same as 3 in binary numbers)
If difference between sum of odd digits and even digits is multiple of 
11 then decimal number is multiple of 11. Let’s see how.

Let’s take the example of 2 digit numbers in decimal
AB = 11A -A + B = 11A + (B – A)
So if (B – A) is a multiple of 11 then is AB.

Let us take 3 digit numbers.

ABC = 99A + A + 11B – B + C = (99A + 11B) + (A + C – B)
So if (A + C – B) is a multiple of 11 then is (A+C-B)

Let us take 4 digit numbers now.
ABCD = 1001A + D + 11C – C + 999B + B – A
= (1001A – 999B + 11C) + (D + B – A -C )
So, if (B + D – A – C) is a multiple of 11 then is ABCD.

This can be continued for all decimal numbers.
Above concept can be proved for 3 in binary numbers in the same way.


Time Complexity: O(logn)


 */