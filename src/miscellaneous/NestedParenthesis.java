/*

We are given a string having parenthesis like below
     “( ((X)) (((Y))) )”
We need to find the maximum depth of balanced parenthesis, like 4 in above example.
 Since ‘Y’ is surrounded by 4 balanced parenthesis.


Stack can also be used.. the maximum value of top will be the answer
 */


package miscellaneous;

import java.util.Scanner;

/**
 * Created by poorvank on 3/26/15.
 */
public class NestedParenthesis {
    
    public static void main(String[] args) {
        
        System.out.println("Enter the string");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        
        int max = 0;
        int current_max = 0;
        
        for (char ch : line.toCharArray()) {
            
            if(ch=='(') {
                
                current_max++;
                if(current_max>max) {
                    max = current_max;
                }
                
            }
            else if(ch == ')') {
                
                if(current_max<=0) {
                    System.out.println(-1);
                    break;
                }
                current_max--;
                
            }
        }
        
        if(current_max!=0) {
            System.out.println(-1);
        }
        System.out.println("Maximum number of braces - " + max);
        
        
    }
    
}


/*

Method used here without any auxiliary space O(1)

1) Take two variables max and current_max, initialize both of them as 0.
2) Traverse the string, do following for every character
    a) If current character is ‘(’, increment current_max and 
       update max value if required.
    b) If character is ‘)’. Check if current_max is positive or
       not (this condition ensure that parenthesis are balanced). 
       If positive that means we previously had a ‘(’ character 
       so decrement current_max without worry. 
       If not positive then the parenthesis are not balanced. 
       Thus return -1. 
3) If current_max is not 0, then return -1 to ensure that the parenthesis
   are balanced. Else return max
   
   


 */