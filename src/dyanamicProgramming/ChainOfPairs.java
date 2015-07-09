/*

You are given n pairs of numbers. In every pair, the first number is always smaller than the second number. 
A pair (c, d) can follow another pair (a, b) if b < c. Chain of pairs can be formed in this fashion. 
Find the longest chain which can be formed from a given set of pairs.

 */
package dyanamicProgramming;

import java.util.Scanner;

/**
 * Created by poorvank on 5/25/15.
 */


class Pair {
    
    public int a;
    public int b;
    
    public Pair(int a,int b) {
        
        this.a = a;
        this.b = b;
        
    }
    
}

public class ChainOfPairs {
    
    public static void main(String[] args) {

        /*TreeMap<Integer,Integer> map = new TreeMap();
        map.put(5,24);
        map.put(39,60);
        map.put(15,28);
        map.put(27,40);
        map.put(50,90);*/
        
        Pair[] array = new Pair[5];
        Scanner scanner = new Scanner(System.in);
        for (int i=0;i<5;i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            array[i] = new Pair(a,b);
        }
        
        longestChainLength(array);
    }
    
    private static void longestChainLength(Pair[] array) {
        
        int[] size = new int[array.length];
        String[] lChain = new String[array.length];
        
        for (int i=0;i<array.length;i++) {
            size[i] = 1;
            lChain[i] = "{ " + array[i].a +  " , " + array[i].b + " }";
        }

        int maxLength = 0;
        
        for (int i=1;i<array.length;i++) {
            for (int j=0;j<i;j++) {
                if(size[j]+1 > size[i] && array[i].a>=array[j].b) {
                    size[i] = size[j] + 1;
                    lChain[i] = lChain[j] + "  " + "{ " + array[i].a +  " , " + array[i].b + " }";
                    if(maxLength<size[i]) {
                        maxLength = size[i];
                    }
                }
            }
        }
        
        
        for (int i=0;i<array.length;i++) {
            if(size[i]==maxLength) {
                System.out.println("maxLength = " + maxLength + " set - " + lChain[i]);
            }
        }
        
        
        System.out.println("Size of chain is - " + maxLength);
        
    }
    
}
/*

This problem is a variation of standard Longest Increasing Subsequence problem. Following is a simple two step process.
1) Sort given pairs in increasing order of first (or smaller) element.
2) Now run a modified LIS process where we compare the second element of already finalized LIS with the first 
element of new LIS being constructed.

 */
