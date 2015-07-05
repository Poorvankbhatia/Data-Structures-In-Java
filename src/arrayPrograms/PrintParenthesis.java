/*

Print all combinations of balanced parentheses
Write a function to generate all possible n pairs of balanced parentheses.

For example, if n=1
{}
for n=2
{}{}
{{}}

 */

package arrayPrograms;

/**
 * Created by poorvank on 6/16/15.
 */
public class PrintParenthesis {

    private static char[] par = new char[100];

    public static void main(String[] args) {

        int n = 4;
        print(0, 0, n, 0);

    }

    private static void print(int start, int end, int n, int pos) {

        if (end == n) {
            printArray(par);
            return;
        } else {

            if (start > end) {
                par[pos] = '}';
                print(start, end + 1, n, pos + 1);
            }
            if (start < n) {
                par[pos] = '{';
                print(start + 1, end, n, pos + 1);
            }

        }

    }

    private static void printArray(char[] arr) {

        StringBuilder sb = new StringBuilder();

        for (char c : arr) {
            sb.append(c);
        }

        System.out.println(sb.toString());
        System.out.println();

    }

}

/*

See this
http://stackoverflow.com/questions/3172179/valid-permutation-of-parenthesis

Keep track of counts of open and close brackets. Initialize these counts as 0. 
Recursively call the _printParenthesis() function until open bracket count is less
 than the given n. If open bracket count becomes more than the close bracket count, 
 then put a closing bracket and recursively call for the remaining brackets. 
 If open bracket count is less than n, then put an opening bracket and call _printParenthesis() for the remaining brackets.

 */