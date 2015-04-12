package Strings;

import java.util.Scanner;

/**
 * Created by poorvank on 3/30/15.
 */
public class CountWords {

    public static void main(String[] args) {

        System.out.println("Enter String ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int state = 1;
        int wordCount = 0;

        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) == ' ' || input.charAt(i) == '\n' || input.charAt(i) == '\t') {
                state = 1;
            } else if (state == 1) {
                wordCount++;
                state = 0;
            }

        }

        System.out.println("Number of words - " + wordCount);
    }

}


/*


The idea is to maintain two states: 0 and 1. The state 1 indicates that a separator is seen. 
State IN indicates that a word character is seen. We increment word count when previous state is 1 and next character
 is a word character.

 */