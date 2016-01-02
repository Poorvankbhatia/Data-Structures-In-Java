package stack;

import stack.ArrayStack;

/**
 * Created by poorvank on 8/4/15.
 */
public class CheckBalancedParenthesis {

    public static void main(String[] args) {

        String str = "[{()}]";
        System.out.print(checkBalanced(str));


    }

    private static boolean checkBalanced(String string) {

        stack.Stack<Character> stack = new ArrayStack<>();

        int i = 0;

        while (i < string.length()) {

            char character;

            if (string.charAt(i) == '{' || string.charAt(i) == '(' || string.charAt(i) == '[') {
                stack.push(string.charAt(i));
            } else if (string.charAt(i) == '}' || string.charAt(i) == ')' || string.charAt(i) == ']') {
                if (!stack.isEmpty()) {
                    character = stack.pop();
                    if (!isMatching(character, string.charAt(i))) {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            i++;

        }

        return stack.size() == 0;

    }


    private static boolean isMatching(char character1, char character2) {

        return character1 == '(' && character2 == ')' || character1 == '{' && character2 == '}' || character1 == '[' && character2 == ']';

    }

}
