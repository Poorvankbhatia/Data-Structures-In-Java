package strings;

import java.util.Arrays;

/**
 * Created by poorvank on 08/07/16.
 */
public class ArrayOfSuffixes {

    public static String[] suffixes(String str) {

        int length = str.length();
        String[] suffix = new String[length];
        for (int i=0;i<length;i++) {
            //a constant-time implementation of this method, as in Java’s standard implementation
            suffix[i] = str.substring(i,length);
        }
        return suffix;

    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(ArrayOfSuffixes.suffixes("poorvankbhatia")));

    }

}


/*

String Builder uses O(1) to add (Amortised) and string uses O(n)
String builder used O(n) to find substring and string uses O(1)

 */
