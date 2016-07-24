/*

Print all interleavings of given two strings
Given two strings str1 and str2, write a function that prints all interleavings of the given two strings. 
You may assume that all characters in both strings are different

Example:

Input: str1 = "AB",  str2 = "CD"
Output:
    ABCD
    ACBD
    ACDB
    CABD
    CADB
    CDAB

Input: str1 = "AB",  str2 = "C"
Output:
    ABC
    ACB
    CAB
An interleaved string of given two strings preserves the order of characters in individual strings. 
For example, in all the interleavings of above first example, ‘A’ comes before ‘B’ and ‘C’ comes before ‘D’.

 */
package strings.print;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 5/26/15.
 */
public class PrintInterLeavings {

    public static void main(String[] args) {

        String str1 = "ab";
        String str2 = "12";

        System.out.println(print(str1, str2).toString());

    }

    private static List<String> print(String str1, String str2) {

        if (str1.length() == 0) {
            List<String> list = new ArrayList<>();
            list.add(str2);
            return list;
        } else if (str2.length() == 0) {
            List<String> list = new ArrayList<>();
            list.add(str1);
            return list;
        } else {

            char c1 = str1.charAt(0);
            List<String> list1 = multiply(c1, print(str1.substring(1), str2));
            char c2 = str2.charAt(0);
            List<String> list2 = multiply(c2, print(str1, str2.substring(1)));
            list1.addAll(list2);
            return list1;
        }


    }

    private static List<String> multiply(char c, List<String> list) {

        List<String> result = new ArrayList<>();

        for (String s : list) {
            String res = Character.toString(c) + s;
            result.add(res);
        }

        return result;

    }

}

/*

Let the length of str1 be m and the length of str2 be n. 
Let us assume that all characters in str1 and str2 are different. 
Let count(m, n) be the count of all interleaved strings in such strings. 
The value of count(m, n) can be written as following.

     count(m, n) = count(m-1, n) + count(m, n-1)
     count(1, 0) = 1 and count(0, 1) = 1
     
     
     http://stackoverflow.com/questions/6804956/interleaving-of-two-strings
     
     ab 12
     a
      b , 12
       12
        b12
      --> ab12  
        1
         b,2 
          2b b2
        12b 1b2
      --> a12b a1b2
     1
      ab,2
         a
          b,2
           b2 2b
           ab2 a2b
      2ab ab2 a2b
     --> 12ab 1ab2 1a2b
 */