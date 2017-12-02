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

    public static void main(String[] args) {
        String s = "mississipie";
        String ans = removeDuplicate("mississipie");
        while (s.length() != ans.length()) {
            s = ans;
            ans = removeDuplicate(s);
        }
        System.out.println(ans);
    }

}
