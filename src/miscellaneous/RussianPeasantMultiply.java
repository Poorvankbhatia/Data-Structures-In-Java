package miscellaneous;

/**
 * Created by poorvank on 1/16/16.
 */
public class RussianPeasantMultiply {
    
    public static void main(String[] args) {
        
        int a = 23;
        int b = 44;
        
        System.out.println(multiply(a,b));
    }
    
    private static int multiply(int a,int b) {
        
        int result = 0;
        
        while (b!=0) {
            
            if((b&1)!=0) {
                result +=a;
            }
            
            //Double
            a = a << 1;
            
            //Half
            b = b >> 1;
            
        }
        
        return result;
        
    }
    
}

/*

How does this work?
The value of a*b is same as (a*2)*(b/2) if b is even, otherwise the value is same as ((a*2)*(b/2) + a). 
In the while loop, we keep multiplying ‘a’ with 2 and keep dividing ‘b’ by 2. If ‘b’ becomes odd in loop,
 we add ‘a’ to ‘res’. When value of ‘b’ becomes 1, the value of ‘res’ + ‘a’, gives us the result.
Note that when ‘b’ is a power of 2, the ‘res’ would remain 0 and ‘a’ would have the multiplication.


 */