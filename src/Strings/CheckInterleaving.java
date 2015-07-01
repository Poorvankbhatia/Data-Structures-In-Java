/*

Check whether a given string is an interleaving of two other given strings
Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B. 
C is said to be interleaving A and B, if it contains all characters of A and B and order of all characters 
in individual strings is preserved

 */

package strings;

/**
 * Created by poorvank on 5/26/15.
 */
public class CheckInterleaving {
    
    public static void main(String[] args) {
        
        String a = "AB";
        String b = "CD";
        
        String check1 = "CABD";
        String check2 = "CAGB";
        
        System.out.println(check(a,b,check1));
        System.out.println(check(a,b,check2));
        
    }
    
    public static boolean check(String a,String b,String c) {
        
        if(c.length()!=a.length()+b.length()) {
            return false;
        }
        
        int i=0;
        int j=0;
        int k=0;
        
        
        while (i<c.length()) {
            
            if(j<a.length() && c.charAt(i)==a.charAt(j)) {
                j++;
            }
            else if(k< b.length() && c.charAt(i)==b.charAt(k)){
                k++;
            }
            else {
                return false;
            }
            
            i++;
            
        }
        
        if(j!=a.length() || k!=b.length()) {
            return false;
        }
        
        return true;
    }
    
}

/*

Pick each character of C one by one and match it with the first character in A. 
If it doesn’t match then match it with first character of B. 
If it doesn’t even match first character of B, then return false. 
If the character matches with first character of A, then repeat the above process from second character of C, 
second character of A and first character of B. If first character of C matches with the first character of B 
(and doesn’t match the first character of A), then repeat the above process from the second character of C, 
first character of A and second character of B. If all characters of C match either with a character of 
A or a character of B and length of C is sum of lengths of A and B, then C is an interleaving A and B.

 */