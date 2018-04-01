/*

Given a string separated by a space like "123456 abc+efg" determine
the solution by mapping integers to letters like a:1, b:2, c:3, d:4, e:5, f:6.
The only operations allowed were + or -. So the calculated solution
that made the tests pass was 123+456 = 579.

 */
package miscellaneous;

import java.util.Stack;

/**
 * Created by poorvank.b on 02/04/18.
 */
public class CalculateMappedStringEquation {

    public final static String Equation = "123456930 abc+efg-hjk";

    public static void main(String[] args) {
        String translatedEquation = TranslateEquation(Equation);
        System.out.println("Input = " + Equation);
        System.out.println("Translated = " + translatedEquation);
        int result = CalculateEquation(translatedEquation);
        System.out.println("Result = " + result);
    }

    public static String TranslateEquation(String equation) {
        char[] values = equation.substring(0, equation.indexOf(" ")).toCharArray();
        char[] result = equation.substring(equation.indexOf(" ") + 1).toCharArray();

        for(int i=0, j=0; i<result.length; i++) {
            if( Character.isLetter(result[i]) ) {
                result[i] = values[j++];
            }
        }

        return new String(result);
    }

    public static int CalculateEquation(String equation) {

        Stack<Integer> integerStack = new Stack<>();
        Stack<Character> operand = new Stack<>();

        for (int k=0;k<equation.length();k++) {
            StringBuilder stringBuilder = new StringBuilder();
            if(Character.isLetterOrDigit(equation.charAt(k))) {
                while (k<equation.length() && equation.charAt(k)!='+' && equation.charAt(k)!='-') {
                    stringBuilder.append(equation.charAt(k));
                    k++;
                }
                integerStack.push(Integer.parseInt(stringBuilder.toString()));
                k--;
            } else {
                operand.push(equation.charAt(k));
            }
        }

        while (integerStack.size()>1) {
            int result=0;
            int a = integerStack.pop();
            int b = integerStack.pop();
            char c = operand.pop();
            result = doOperation(c,b,a);
            integerStack.push(result);
        }

        return integerStack.pop();
    }

    public static int doOperation(char operator,int left,int right) {
        int result=0;
        switch(operator) {
            case '+':
                result = left + right;
                break;
            case '-':
                result = left - right;
                break;
        }
        return result;
    }

}
