/*

Count distinct palindromic sub strings in a string

 */

package dyanamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank on 04/02/17.
 */
public class CountDistinctPalindrome {

    public int count(String s) {
        if(null==s || s.length()==0) {
            return 0;
        }

        int n = s.length();

        boolean[][] isPalindrome = new boolean[n][n];

        Set<String> set = new HashSet<>();

        for (int i=0;i<n;i++) {
            isPalindrome[i][i] = true;
            set.add(s.substring(i,i+1));
        }

        for (int gap=2;gap<=n;gap++) {

            for (int i=0;i<n-gap+1;i++) {

                int j = i+gap-1;

                if(s.charAt(i)==s.charAt(j) && gap==2) {
                    isPalindrome[i][j] = true;
                } else if(s.charAt(i)==s.charAt(j)) {
                    isPalindrome[i][j] = isPalindrome[i+1][j-1];
                }

                if(isPalindrome[i][j]) {
                    set.add(s.substring(i,j+1));
                }

            }

        }

        System.out.print(set);

        return set.size();
    }

    public static void main(String[] args) {
        System.out.print(new CountDistinctPalindrome().count("aabaa"));
    }

}
