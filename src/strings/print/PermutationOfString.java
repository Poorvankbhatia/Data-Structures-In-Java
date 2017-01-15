package strings.print;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank on 7/21/15.
 */
public class PermutationOfString {

    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {

        String str = "ABCA";
        printPermutation("", str);

    }

    private static void printPermutation(String prefix, String str) {

        int n = str.length();
        if (n == 0) {
            /*
             To Handle Duplicates
             */
            if(!set.contains(prefix)) {
                System.out.println(prefix);
                set.add(prefix);
            }
        } else {
            for (int i = 0; i < n; i++) {
                

               /* System.out.println("prefix is - " + prefix + " - " + i);
                System.out.println("SS "  + str + " " + str.substring(0, i) + " - " + prefix + str.charAt(i) + " - " + i);*/

                printPermutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));

               /* System.out.println("return " + i);*/
            }
        }

    }

}

//See images