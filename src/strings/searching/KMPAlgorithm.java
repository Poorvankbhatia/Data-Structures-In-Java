package strings.searching;

import java.util.Arrays;

/**
 * Created by poorvank on 8/8/15.
 */

class KMP {

    static int[] table;

    private static void buildTable(String pattern) {

        int len = 0;
        int i = 0;
        int m = pattern.length();

        table = new int[pattern.length()];

        table[0] = 0;
        i = 1;

        while (i < m) {

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                table[i] = len;
                i++;
            } else {

                if (len != 0) {
                    //Compare current i with previous elements. AAACAAAA and i = 7
                    len = table[len - 1];
                } else {
                    table[i] = 0;
                    i++;
                }

            }

        }

        System.out.println("Table build is - " + Arrays.toString(table));

    }

    public static void firstOccurrence(String text, String pattern) {

        buildTable(pattern);

        int i = 0, j = 0;
        int n = text.length(), m = pattern.length();

        while (i < n) {

            // System.out.println(pattern.charAt(i) + " " + pattern.charAt(j));

            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println("\nFound pattern " + (i - j));
                break;
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {

                if (j == 0) {
                    i++;
                } else {
                    i += j - table[j - 1];
                    j = 0;
                }

            }

        }

    }

}

public class KMPAlgorithm {

    public static void main(String[] args) {

        String text = "aaacaa";
        String pattern = "aa";

        KMP.firstOccurrence(text, pattern);

    }

}


/*

Useful references :

http://jakeboxer.com/blog/2009/12/13/the-knuth-morris-pratt-algorithm-in-my-own-words/

http://stackoverflow.com/questions/13271856/understanding-knuth-morris-pratt-algorithm


 */