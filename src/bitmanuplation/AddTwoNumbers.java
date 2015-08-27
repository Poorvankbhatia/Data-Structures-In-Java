package bitmanuplation;

/**
 * Created by poorvank on 8/27/15.
 */
public class AddTwoNumbers {
    
    public static void main(String[] args) {
        
        int a=7,b=8;
        
        System.out.println(add(a,b));
        
    }
    
    private static int add(int a,int b) {
        
        while (b!=0) {
            
            int carry = (a&b);
            a = (a^b);
            b = (carry<<1);
            
        }
        
        return a;
        
    }
    
}

/*

Sum of two bits can be obtained by performing XOR (^) of the two bits. Carry bit can be obtained by performing 
AND (&) of two bits.
Above is simple Half Adder logic that can be used to add 2 single bits. We can extend this logic for integers. 
If x and y don’t have set bits at same position(s), then bitwise XOR (^) of x and y gives the sum of x and y.
 To incorporate common set bits also, bitwise AND (&) is used. Bitwise AND of x and y gives all carry bits.
  We calculate (x & y) << 1 and add it to x ^ y to get the required result.

 */