package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 7/2/15.
 */
public class NextGreatestNumber {

    public static void main(String[] args) {

        int n = 218765;
        String s = Integer.toString(n);
        printNextNo(s);

    }

    private static void printNextNo(String s) {

        int i;

        for (i = s.length() - 2; i >= 0; i--) {
            if (Character.getNumericValue(s.charAt(i)) < Character.getNumericValue(s.charAt(i + 1))) {
                break;
            }
            if (i == 0) {
                System.out.println("Not possible");
                return;
            }
        }

        System.out.println(i);

        int minIndex = i + 1;

        for (int j = i + 2; j < s.length(); j++) {
            if (Character.getNumericValue(s.charAt(j)) < Character.getNumericValue(s.charAt(minIndex)) && Character.getNumericValue(s.charAt(minIndex)) > Character.getNumericValue(s.charAt(i))) {
                minIndex = j;
            }
        }

        String replacedString = replacedString(s, i, minIndex);

        char[] chars = replacedString.toCharArray();
        Arrays.sort(chars, i + 1, s.length());

        System.out.println(String.copyValueOf(chars));


    }

    private static String replacedString(String s, int i, int j) {

        StringBuilder sb = new StringBuilder(s);
        char c = s.charAt(i);
        sb.setCharAt(i, s.charAt(j));
        sb.setCharAt(j, c);

        return sb.toString();

    }

}
