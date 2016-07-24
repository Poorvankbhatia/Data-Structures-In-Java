/*

Find all strings formed from characters mapped to digits of a number
Consider below list where each digit from 1 to 9 maps to few characters.

1 -> ['A', 'B', 'C']
2 -> ['D', 'E', 'F']
3 -> ['G', 'H', 'I']
4 -> ['J', 'K', 'L']
5 -> ['M', 'N', 'O']
6 -> ['P', 'Q', 'R']
7 -> ['S', 'T', 'U']
8 -> ['V', 'W', 'X']
9 -> ['Y', 'Z']


Given a number, replace its digits with corresponding characters in given list and print all strings possible.
Same character should be considered for every occurrence of a digit in the number. Input number is positive and doesn’t contain 0.

Examples :

Input : 121
Output : ADA BDB CDC AEA BEB CEC AFA BFB CFC

Input : 22
Output : DD EE FF

 */

package strings;

import java.util.HashMap;

/**
 * Created by poorvank on 24/07/16.
 */
public class MappedDigits {


    private static void printCombinations(String[][] matrix, int pos, HashMap<Integer,Character> map,String input,String result) {

        if(pos==input.length()) {
            System.out.println(result);
            return;
        }

        int currentPosValue = Character.getNumericValue(input.charAt(pos));

        if(map.get(currentPosValue)!=null) {

            result += map.get(currentPosValue);
            printCombinations(matrix,pos+1,map,input,result);

        } else {

            for (int i=0;i<matrix[currentPosValue-1].length;i++) {
                map.put(currentPosValue,matrix[currentPosValue-1][i].charAt(0));
                result += map.get(currentPosValue);
                printCombinations(matrix,pos+1,map,input,result);
                //Clear map after one iteration
                map.remove(currentPosValue);
                result = result.substring(0,result.length()-1);
            }

        }


    }




    public static void main(String[] args) {
        String[][] matrix = new String[][]
                {
                        {"A","B","C"},
                        {"D","E","F"},
                        {"G","H","I"},
                        {"J","K","L"},
                        {"M","N","O"},
                        {"P","Q","R"},
                        {"S","T","U"},
                        {"V","W","X"},
                        {"Y","Z"}
                };

        Integer n = 22;
        String s = Integer.toString(n);

        printCombinations(matrix,0,new HashMap<>(),s, "");
    }

}
