package dyanamicprogramming;

/**
 * Created by poorvank on 7/30/15.
 */
public class WordBreakDP {

    public static void main(String[] args) {

        String str = "man";
        System.out.println(wordBreak(str));

    }


    private static boolean wordBreak(String str) {

        int size = str.length();
        boolean[] validWord = new boolean[size + 1];

        validWord[0] = true;


        for (int i = 0; i <= size; i++) {

            for (int j=0;j<i;j++) {

                if(!validWord[j]) {
                    continue;
                }
                String check = str.substring(j, i);
                if (Dictionary.contains(check)) {
                    validWord[i] = true;
                    break;
                }

            }
        }
        return validWord[size];

    }

}

/*

The key to solve this problem by using DP approach:

Define an array validWord[] such that validWord[i]==true => 0-(i-1) can be segmented using dictionary
Initial state validWord[0] == true


Complexity : Time: O(n square)

Memory: O(N)

 */