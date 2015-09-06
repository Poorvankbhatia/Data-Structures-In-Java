/*

You are given a String S that consists of characters '0' and '1' only.Return the smallest positive integer
 K such that it is possible to cut S into K pieces, each of them being a power of 5. If there is no such K, 
 return -1 instead. 
Examples 
0)	

"101101101" 
Returns: 3 
We can split the given string into three "101"s. 

Note that "101" is 5 in binary. 

1)	

"1111101" 
Returns: 1 
"1111101" is 5^3. 

2)	

"110011011" 
Returns: 3 
Split it into "11001", "101" and "1". 

3)	

"1000101011" 
Returns: -1 

4)	

"111011100110101100101110111" 
Returns: 5

 */

package dp;

import java.util.Arrays;

/**
 * Created by poorvank on 8/26/15.
 */
public class CutStrings {
    
    public static void main(String[] args) {
        
        String str = "1111101";
        
        int n = str.length();
        int[] ways = new int[n+1];
        
        ways[0] = 0;
        
        for (int i=1;i<=n;i++) {
            
            ways[i] = Integer.MAX_VALUE;
            
            for (int j=1;j<=i;j++) {
                
                if(str.charAt(j-1)=='0') {
                    continue;
                }
                
                int num = Integer.parseInt(str.substring(j-1,i),2);
                System.out.println(num + " " + isPower(num) + " i = " + i + " j = " + j);
                
                if(isPower(num)) {
                    if(ways[j-1]!=Integer.MAX_VALUE)
                      ways[i] = Math.min(ways[i],ways[j-1]+1);
                }
                
            }
            
        }
        
        System.out.println(Arrays.toString(ways));
        
        System.out.println(ways[n]!=Integer.MAX_VALUE?ways[n]:-1);
        
    }
    
    private static boolean isPower(int num) {
        
        if(num==0) {
            return false;
        }
        int n = (int)(Math.log(num)/Math.log(5));
        
        return (Math.pow(5,n)==num);
    }
    
}


/*

dynamic programming
f[i] = min (f[j -1] + 1, f[i])

http://www.careercup.com/question?id=6239886770176000

 */