/**
 * All ways to add parenthesis for evaluation
 * Given a string that represents an expression constituting numbers and binary operator +, – and * only.
 * We need to parenthesize the expression in all possible way and return all evaluated values.
 * Input : expr = “3-2-1”
 * Output : {0, 2}
 * ((3-2)-1) = 0
 * (3-(2-1)) = 2
 *
 *
 * Input : expr = "5*4-3*2"
 * Output : {-10, 10, 14, 10, 34}
 * (5*(4-(3*2))) = -10
 * (5*((4-3)*2)) = 10
 * ((5*4)-(3*2)) = 14
 * ((5*(4-3))*2) = 10
 * (((5*4)-3)*2) = 34
 */
package dyanamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by poorvank on 29/12/16.
 */
public class AllWaysToAddParenthesis {

    private HashMap<String,List<Integer>> map;

    public AllWaysToAddParenthesis() {
        this.map = new HashMap<>();
    }

    private boolean isOperator(char c) {
        return (c=='*' || c=='+' || c=='-');
    }

    private List<Integer> fillMap(String input) {

        List<Integer> result = new ArrayList<>();

        for (int i=0;i<input.length();i++) {
            char c = input.charAt(i);
            if(isOperator(c)) {

                List<Integer> leftResult = fillMap(input.substring(0,i));
                List<Integer> rightResult = fillMap(input.substring(i+1));

                for (Integer leftElement : leftResult) {
                    for (Integer rightElement : rightResult) {

                        if(c=='*') {
                            result.add(leftElement*rightElement);
                        } else if(c=='+') {
                            result.add(leftElement+rightElement);
                        } else {
                            result.add(leftElement-rightElement);
                        }

                    }
                }

            }

        }

        if(result.size()==0) {
            result.add(Integer.parseInt(input));
        }

        map.put(input,result);
        return result;
    }

    public static void main(String[] args) {

        String input = "5*4-3*2";
        AllWaysToAddParenthesis a = new AllWaysToAddParenthesis();
        a.fillMap(input);
        System.out.println(a.map.get(input));

    }

}

/**
 *  Parenthesizing all possible valid substring of the expression and then evaluating them, but as we can see that it
 *  will involve solving lots of repeating subproblem, to save ourselves we can follow a dynamic programming approach.
 *  We store the result for each substring in a map and if string in recursion is already solved, we return result from map
 *  instead of solving that again.
 *  Below code runs a loop in the string and if at any instant, character is operator then we divide the input from there,
 *  recursively solve each part and then combine the result in all possible ways.
 */