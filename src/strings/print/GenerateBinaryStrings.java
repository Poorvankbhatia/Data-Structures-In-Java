/*

Generate all binary strings from given pattern
Given a string containing of ‘0’, ‘1’ and ‘?’ wildcard characters, generate all binary strings that can be formed by
replacing each wildcard character by ‘0’ or ‘1’.

Input str = "1??0?101"
Output:
        10000101
        10001101
        10100101
        10101101
        11000101
        11001101
        11100101
        11101101

 */

package strings.print;

/**
 * Created by poorvank on 25/07/16.
 */
public class GenerateBinaryStrings {


    private static void generate(String str,int index) {

        if(index == str.length()) {
            System.out.println(str);
            return;
        }

        if(str.charAt(index)=='?') {

            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(index,'0');
            str = sb.toString();
            generate(str,index+1);



            sb = new StringBuilder(str);
            sb.setCharAt(index,'1');
            str = sb.toString();
            generate(str,index+1);

        }
        else {
            generate(str,index+1);
        }

    }

    public static void main(String[] args) {

        String s = "1??0?101";

        generate(s,0);

    }

}

/*

We pass index of next character to the recursive function. If the current character is a wildcard character ‘?’,
we replace it by ‘0’ or ‘1’ and recurse for remaining characters. We print the string if we reaches its end.

 */