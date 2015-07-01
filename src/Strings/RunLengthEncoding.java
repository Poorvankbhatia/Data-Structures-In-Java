/*


Given an input string, write a function that returns the Run Length Encoded string for the input string.

For example, if the input string is “wwwwaaadexxxxxx”, then the function should return “w4a3d1e1x6″.

 */

package strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by poorvank on 4/16/15.
 */
public class RunLengthEncoding {

    public static void main(String[] args) {

        System.out.println("Enter String to run length encoded");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println("Run length encoding via map method - " + encodingUsingMap(input));
        System.out.println("Run length encoding without map method - " + encodingWithoutMap(input));

    }


    //In hashmap method order is not maintained
    private static String encodingUsingMap(String input) {

        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {

            Character character = input.charAt(i);

            if (hashMap.containsKey(character)) {
                hashMap.put(character, hashMap.get(character) + 1);
            } else {
                hashMap.put(character, 1);
            }


        }

        String string = "";

        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {

            string = string + entry.getKey() + entry.getValue();

        }

        return string;

    }

    private static String encodingWithoutMap(String input) {

        String string = "";

        for (int i = 0; i < input.length(); i++) {

            string = string + input.charAt(i);
            int count = 1;

            while (i + 1 < input.length() && input.charAt(i) == input.charAt(i + 1)) {

                count++;
                i++;

            }

            string = string + count;

        }

        return string;

    }

}

/*

Algorithm:
a) Pick the first character from source string.
b) Append the picked character to the destination string.
c) Count the number of subsequent occurrences of the picked character and append the count to destination string.
d) Pick the next character and repeat steps b) c) and d) if end of string is NOT reached.
Complexity O(n)

 */