/*
Remove duplicates from string
 */

package strings;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by poorvank on 12/30/15.
 */
public class RemoveDuplicates {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[] arr = input.toCharArray();
        Arrays.sort(arr);

        input = new String(arr);


        char ch = input.charAt(0);
        int count = 1, i = 1;
        System.out.print(ch);
        while (i < input.length()) {

            while (input.charAt(i) != ch) {
                ch = input.charAt(i);
                System.out.print(ch);
                count++;
            }

            i++;
        }

        System.out.println("\n" + count);
    }

}
