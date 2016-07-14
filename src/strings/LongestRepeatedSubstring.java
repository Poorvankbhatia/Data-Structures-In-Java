/*

What is the longest substring that appears at least
twice in a given string? For example, the longest repeated substring in the string
"to be or not to be" is the string "to be".

 */

package strings;


import utility.Suffix;
import utility.priorityQueueClasses.LongestCommonPrefix;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * Created by poorvank on 13/07/16.
 */

public class LongestRepeatedSubstring {
    /*
        Longest common Prefix:  it takes
        time proportional to the length of
        the match.
     */
    private static String lcp(String s1,String s2) {
        return LongestCommonPrefix.value(s1,s2);
    }

    public static String lrs(String text) {

        // form the N suffixes
        int N  = text.length();
        Suffix[] suffixes = new Suffix[N];
        for (int i = 0; i < N; i++) {
            suffixes[i] = new Suffix(text,i);
        }

        //For optimised version use 3 way Quick Sort
        Arrays.sort(suffixes);

        String lrs = "";
        for (int i=0;i<N-1;i++) {
            String lcp = LongestCommonPrefix.value(suffixes[i].toString(),suffixes[i+1].toString());
            if(lcp.length()>lrs.length()) {
                lrs = lcp;
            }
            //System.out.println("current i = " + i + " lrs = " + lrs);
        }

        return lrs;
    }

    public static void main(String[] args) {

        try {
            String absolutePath = new File("").getAbsolutePath();
            File file = new File(absolutePath + "/src/inputFiles/mobydick.txt");
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            String text = new String(data, "UTF-8");
            text = text.replace("\n", "");
            text = text.replaceAll("\\s+", " ");

            Long startTime = System.currentTimeMillis();
            System.out.println("Longest repeated substring = " + lrs(text));
            System.out.println("Total time taken = " + (System.currentTimeMillis() - startTime));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

/*

brute-force solution immediately suggests itself: we compare the substring starting at
each string position i with the substring starting at each other starting position j, keeping
track of the longest match found. This code is not useful for long strings, because
its running time is at least quadratic in the length of the string: as usual, the number of
different pairs i and j is N (N1)
 2, so the number of calls on lcp() for this approach
would be ~N^2/2. Using this solution for a genomic sequence with millions of characters
would require trillions of lcp() calls, which is infeasible.


The key to the algorithm is that every
substring appears somewhere as a prefix of
one of the suffixes in the array. After sorting, the
longest repeated substrings will appear in adjacent
positions in the array. Thus, we can make
a single pass through the sorted array, keeping
track of the longest matching prefixes between
adjacent strings




 */
