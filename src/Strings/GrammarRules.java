/*

STATE DIAGRAM

Check a given sentence for a given set of simple grammar rules
A simple sentence if syntactically correct if it fulfills given rules. The following are given rules.

1. Sentence must start with a Uppercase character (e.g. Noun/ I/ We/ He etc.)
2. Then lowercase character follows.
3. There must be spaces between words.
4. Then the sentence must end with a full stop(.) after a word.
5. Two continuous spaces are not allowed.
6. Two continuous upper case characters are not allowed.
7. However the sentence can end after an upper case character.

 */

package Strings;

import java.util.Scanner;

/**
 * Created by poorvank on 3/28/15.
 */
public class GrammarRules {

    public static void main(String[] args) {

        System.out.println("Enter String ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (isValid(line)) {
            System.out.println("Valid");
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean isValid(String sentence) {

        if (Character.isLowerCase(sentence.charAt(0))) {
            return true;
        }

        if (sentence.charAt(sentence.length() - 1) != '.') {
            return false;
        }

        int index = 1;
        int currentState = 0, previousState = 0;

        while (index < sentence.length()) {

            if (Character.isUpperCase(sentence.charAt(index))) {
                currentState = 0;
            } else if (sentence.charAt(index) == ' ') {
                currentState = 1;
            } else if (Character.isLowerCase(sentence.charAt(index))) {
                currentState = 2;
            } else if (sentence.charAt(index) == '.') {
                currentState = 3;
            }

            //Only small characters can be repeated in a sentence

            if (previousState == currentState && currentState != 2) {
                return false;
            }


            // eg - I lovE cinema
            if (previousState == 2 && currentState == 0) {
                return false;
            }

            if (currentState == 3 && previousState != 1) {
                if (index + 1 == sentence.length()) {
                    return true;
                }
            }


            index++;

            previousState = currentState;

        }

        return false;

    }

}


/*

After checking corner cases check state diagram in images

 */