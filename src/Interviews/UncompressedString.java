/*

DIRECTI

There is a compressed string eg. ”ab2c3”, the string has lowercase characters and numbers.
 We can uncompress the given string as follows: whenever we get a number “n” in the string, 
 the portion of the string before the number will repeat “n” times. So in the above example,
  we get a 2, so string will become “ababc3”, now we get a 3, so final string will be “ababcababcababc”.
Given a compressed string and a number k, you have to output the k’th character in the uncompressed string.

1 <= length of string <= 1500
1 <= n <= 1000
1 <= k < 2^31
example:
input: ab2c3 10
output: c


 */

package Interviews;

/**
 * Created by poorvank on 3/31/15.
 */
public class UncompressedString {
    
    public static void main(String[] args) {
        
        kthCharacter("abc3", 9);
        kthCharacter("abc3z2pq3", 7);
        kthCharacter("abc3z2pq3", 22);
        kthCharacter("abc3z2pq3", 30);
        
    }
    
    private static void kthCharacter(String str,int n) {
        
        int previousLength = 0,currentLength = 0,i=0;
        
        while (currentLength < n) {
            
            previousLength = currentLength;
            
            if(Character.isAlphabetic(str.charAt(i))) {
                currentLength ++;
            } else {
                currentLength = currentLength*(Character.getNumericValue(str.charAt(i)));
            }
            i++;
        }
        
        
        /*
            previouslength is the repeated window substring hence modulo n%previouslength.. to reduce search space
            it simply means that that string till pl is repeated by the number found
            so simply search in that prefix :)
         */
        
        if(currentLength > n) {
            int tmp = n%previousLength;
            if(tmp==0) {
                tmp = previousLength;
            }
          //  System.out.println("cl - " + currentLength + " pl - " + previousLength + " tmp -  " + tmp );
            kthCharacter(str,tmp);
        }
        
        /*
          
          if current length is equals to the position and there was no
 {                                   number at the last of the prefix : print answer
        
         */
        if(currentLength == n && Character.isAlphabetic(str.charAt(i-1))) {
            System.out.println(str.charAt(i-1));
        } 
        /*
        
            if current length is equals to the position and there was a number
 {                                              at the last simply print the character before the number
        
         */
        else if(currentLength == n && !Character.isAlphabetic(str.charAt(i-1))) {
            System.out.println(str.charAt(i-2));
        }
        
        
    }
    
}
