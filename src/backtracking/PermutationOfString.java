package backtracking;

/**
 * Created by poorvank on 7/21/15.
 */
public class PermutationOfString {
    
    public static void main(String[] args) {
        
        String str = "123";
        printPermutation("",str);
        
    }
    
    private static void printPermutation(String prefix,String str) {
        
        int n = str.length();
        if(n==0) {
            System.out.println(prefix);
        }
        else {
            for (int i=0;i<n;i++) {
                
                printPermutation(prefix+str.charAt(i),str.substring(0,i)+str.substring(i+1,n));
                
            }
        }
        
    }
    
}

//See images