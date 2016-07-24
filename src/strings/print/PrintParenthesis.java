/*

Print all combinations of balanced parentheses
Write a function to generate all possible n pairs of balanced parentheses.

For example, if n=1
{}
for n=2
{}{}
{{}}

 */

package strings.print;

/**
 * Created by poorvank on 6/16/15.
 */
public class PrintParenthesis {

    private static char[] par = new char[100];

    public static void main(String[] args) {

        int n = 4;
        print(0, 0, n, 0);
        print(3, 0, "");

    }
    
    /*
    
       The most important optimization is in the string building aspect. 
       Although it looks like a simple string concatenation on the surface, 
       the above solution actually has a "hidden" O(N^2) string building component 
       (because concatenating one character to an immutable String of length N is an O(N) operation). 
       Generally we optimize this by using a mutable StringBuilder instead, but for this particular case we can also 
       simply use a fixed-size char[] and an index variable.
    
    
     */

    private static void print(int openBracket, int closedBracket, String s) {

        if (openBracket == 0 && closedBracket == 0) {
            System.out.println(s);
        }
        if (openBracket > 0) {
            print(openBracket - 1, closedBracket + 1, s + "<");
        }
        if (closedBracket > 0) {
            print(openBracket, closedBracket - 1, s + ">");
        }


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