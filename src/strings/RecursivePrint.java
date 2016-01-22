/*

Recursively print all sentences that can be formed from list of word lists

Given a list of word lists How to print all sentences possible taking one word from a list at a time via recursion?

 */

package strings;

import java.util.ArrayList;

/**
 * Created by poorvank on 4/13/15.
 */
public class RecursivePrint {

    private static int max = 0;

    public static void main(String[] args) {

        String[][] jaggedArray = {{"you", "we"},
                                  {"have", "are"},
                                  {"sleep", "eat", "drink"}};

        print(jaggedArray);


    }

    private static void print(String[][] jaggedArray) {

        for (String[] array : jaggedArray) {
            if (max < array.length) {
                max = array.length;
            }
        }

        for (int i = 0; i < jaggedArray[0].length; i++) {
            if (jaggedArray[0][i] != null) {
                dfsUtil(jaggedArray, 0, i, new ArrayList<String>());
            }
        }


    }

    private static void dfsUtil(String[][] jaggedArray, int m, int n, ArrayList<String> arrayList) {

        arrayList.add(jaggedArray[m][n]);

        if (m == jaggedArray.length - 1) {
            System.out.println(arrayList.toString());
            return;
        }

        for (int i = 0; i < max; i++) {

            //Condition to check when array element is empty :  DRY RUN
            if (jaggedArray[m + 1].length >= i + 1 && jaggedArray[m + 1][i] != null) {
                dfsUtil(jaggedArray, m + 1, i, new ArrayList<String>(arrayList));
                System.out.println();
            }
        }


    }

}


/*

The idea is based on simple depth first traversal. We start from every word of first list as first word of
 an output sentence, then recur for the remaining lists.

 */