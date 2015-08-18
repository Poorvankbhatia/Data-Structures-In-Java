package dp;

/**
 * Created by poorvank on 7/30/15.
 */
public class WordBreakDP {

    public static void main(String[] args) {

        String str = "samsungandmango";
        System.out.println(wordBreak(str));

    }


    private static boolean wordBreak(String str) {

        int size = str.length();
        boolean[] validWord = new boolean[size + 1];

        validWord[0] = true;


        for (int i = 0; i <= size; i++) {

            //should continue from match position
            if (!validWord[i]) {
                continue;
            }

            for (String string : Dictionary.dictionary) {

                int wordSize = string.length();
                int end = wordSize + i;

                if (end > str.length()) {
                    continue;
                }

                if (validWord[end]) {
                    continue;
                }

                if (str.substring(i, end).equals(string)) {
                    validWord[end] = true;
                    break;
                }

            }


        }


        return validWord[size];

    }

}

/*

The key to solve this problem by using dynamic programming approach:

Define an array t[] such that t[i]==true => 0-(i-1) can be segmented using dictionary
Initial state t[0] == true

 */