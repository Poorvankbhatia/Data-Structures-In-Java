/*

MS Excel columns has a pattern like A, B, C, … ,Z, AA, AB, AC,…. ,AZ, BA, BB, … ZZ, AAA, AAB ….. etc. 
In other words, column 1 is named as “A”, column 2 as “B”, column 27 as “AA”.

Given a column number, find its corresponding Excel column name. Following are more examples.

Input          Output
 26             Z
 51             AY
 52             AZ
 80             CB
 676            YZ
 702            ZZ
 705            AAC

 */

package stringPrograms;

import java.util.Scanner;

/**
 * Created by poorvank on 3/29/15.
 */
public class ExcelColumn {

    public static void main(String[] args) {

        System.out.println("Enter column number");
        column(new Scanner(System.in).nextInt());
        System.out.println(printColumn(new Scanner(System.in).nextInt()));

    }

    private static void column(int n) {

        StringBuilder sb = new StringBuilder("");

        while (n > 0) {

            int rem = n % 26;

            if (rem == 0) {
                sb.append('Z');
                n = (n / 26) - 1;
            } else {
                sb.append((char) ((rem - 1) + 'A'));
                n = n / 26;
            }

        }

        System.out.println("Column name is- " + sb.reverse().toString());
        

    }
    
    private static String printColumn(int column) {

        column--;
        if (column >= 0 && column < 26) {
            System.out.println(column + " " +  Character.toString(((char)('A' + column))));
            return Character.toString(((char)('A' + column)));
        }
        else if (column > 25)
            return printColumn(column / 26) + printColumn(column % 26 + 1);
        else
            return  "Invalid Column #" + (column + 1);
        
    }

}

/*


 Suppose we have a number n, let’s say 28. so corresponding to it we need to print the column name.
  We need to take remainder with 26.

If remainder with 26 comes out to be 0 (meaning 26, 52 and so on) then we put ‘Z’ in the 
output string and new n becomes n/26 -1 because here we are considering 26 to be ‘Z’
 while in actual it’s 25th with respect to ‘A’.

Similarly if the remainder comes out to be non zero. (like 1, 2, 3 and so on) then we
 need to just insert the char accordingly in the string and do n = n/26.

Finally we reverse the string and print.

 If column would have started from 0 to 25 after every remainder n would be n = n/26 -1
 The -1 on the divide/equals statement shifts A=1...Z=26.
 Then finally the generated string is reversed to get it to be in the correct order. 

*/