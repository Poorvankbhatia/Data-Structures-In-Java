/*

Given a string, recursively remove adjacent duplicate characters from string.
The output string should not have any adjacent duplicates. See following examples.

Input:  azxxzy
Output: ay
First "azxxzy" is reduced to "azzy".
The string "azzy" contains duplicates,
so it is further reduced to "ay".

Input: geeksforgeeg
Output: gksfor
First "geeksforgeeg" is reduced to
"gksforgg". The string "gksforgg"
contains duplicates, so it is further
reduced to "gksfor".

Input: caaabbbaacdddd
Output: Empty String

Input: acaaabbbacdddd
Output: acac

 */
package strings;

import java.util.Stack;

/**
 * Created by poorvank.b on 02/12/17.
 */
public class RemoveAdjacentDuplicates {

    public static String removeDuplicate(String s) {

        Stack<Character> st = new Stack<>();
        int flag = 0;

        for (int i = 0; i < s.length(); i++) {

            if (st.empty()) {
                st.push(s.charAt(i));
            } else {
                if (st.peek() == s.charAt(i)) {
                    flag = 1;
                } else {
                    if (flag == 1) {
                        st.pop();
                    }

                    st.push(s.charAt(i));
                    flag = 0;
                }
            }

        }

        if (flag == 1)
            st.pop();

        StringBuilder stringBuilder = new StringBuilder();
        while (!st.empty()) {
            stringBuilder.append(st.peek());
            st.pop();
        }

        return stringBuilder.reverse().toString();

    }

    private static String duplicatesRemoval(String str,int k) {
        int n = k-1;
        int l = 0;
        do {
            l = str.length();
            str = str.replaceAll("(.)\\1{" + n + "}", "");
        } while (l != str.length());
        return str;
    }

    public static void main(String[] args) {
        String s = "abbcccbfgh";
        System.out.println(duplicatesRemoval(s,2));
       /* String ans = removeDuplicate(s);
        while (s.length() != ans.length()) {
            s = ans;
            ans = removeDuplicate(s);
        }
        System.out.println(ans);*/
    }

}
