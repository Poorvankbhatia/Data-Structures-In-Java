package BitManuplation;

import java.util.Scanner;

/**
 * Created by poorvank on 5/1/15.
 */
public class NextPowerOfTwo {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number ");
        int n = scanner.nextInt();
        
        System.out.println("Method 1 - " + method1(n));
        
    }
    
    /* If n is a power of 2 then return n
    1  If (n & !(n&(n-1))) then return n
    2  Else keep right shifting n until it becomes zero
    and count no of shifts
    a. Initialize: count = 0
    b. While n ! = 0
    n = n>>1
    count = count + 1

    Now count has the position of set bit in result
    Return (1 << count)
    */

    private static int method1(int n) {
        
        if(n!=0 && ((n & (n-1))==0)) {
            return n;
        }
        int count =0;
        while (n!=0) {
            n = n >> 1;
            count = count+1;
        }
        
        
        /*
         
         above can also be done as : 
         unsigned int p = 1;
         while(p<n) {
           p = p << 1;
         }
        
         */
        
        return 1 << count;
        
    }
    
}


/*

Another Method

1. Subtract n by 1
       n = n -1

    2. Set all bits after the leftmost set bit.

     Below solution works only if integer is 32 bits
n = n | (n >> 1);
        n = n | (n >> 2);
        n = n | (n >> 4);
        n = n | (n >> 8);
        n = n | (n >> 16);
        3. Return n + 1

 */