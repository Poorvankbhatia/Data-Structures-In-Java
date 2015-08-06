/*

Build Lowest Number by Removing n digits from a given number
Given a string ‘str’ of digits and an integer ‘n’, build the lowest possible number by 
removing ‘n’ digits from the string and not changing the order of input digits.

Examples:

Input: str = "4325043", n = 3
Output: "2043"

Input: str = "765028321", n = 5
Output: "0221"

Input: str = "121198", n = 2
Output: "1118" 

 */

package miscellaneous;

/**
 * Created by poorvank on 3/30/15.
 */


class DecreasingResult {

    public int firstDecreasing;
    public int nextStart;

    public DecreasingResult(int firstDecreasing, int nextStart) {
        this.firstDecreasing = firstDecreasing;
        this.nextStart = nextStart;
    }
}

public class BuildLowestNo {


    public static void main(String[] args) {

        String s1 = "4325043";
        int k1 = 3;

        String s2 = "765028321";
        int k2 = 5;

        String s3 = "121198";
        int k3 = 2;

        printLowestNumber(s1, k1);
        printLowestNumber(s2, k2);
        printLowestNumber(s3, k3);
        
    }

    private static void printLowestNumber(String number, int k) {

        String leastNumber = number;
        int start = 0;

        while (k > 0 && leastNumber.length() > 0) {

            DecreasingResult result = getNextDecreasing(leastNumber, start);
            if (result.firstDecreasing >= 0) {
                leastNumber = removeDigits(leastNumber, result.firstDecreasing);
            } else {
                leastNumber = removeDigits(leastNumber, leastNumber.length() - 1);
            }

            k--;
            start = result.nextStart;
        }

        System.out.println(leastNumber);
        
    }

    /**
     * If the ith digit is the first digit which is greater than the next one, all digits before the
     * *  ith digit are increasingly sorted. The (i-1)th digit might be less than the (i+1)th digit,
     * *  the next digit of the (i-1)th digit after the ith digit is deleted. Therefore,
     * *  it is safe to start from the (i-1)th digit in the next round of search.
     * *  With this optimization strategy, the efficiency gets improved from O(n*k) to O(n),
     * *  if the length of the input number has n digits and k digits are deleted.
     *
     * @param number
     * @param start
     * @return
     */

    private static DecreasingResult getNextDecreasing(String number, int start) {

        int firstDecreasing = -1;
        int nextStart = 0;

        for (int i = start; i < number.length() - 1; i++) {

            int currentDigit = number.charAt(i) - '0';
            int nextDigit = number.charAt(i + 1) - '0';

            if (currentDigit > nextDigit) {
                firstDecreasing = i;
                break;
            }

        }

        if (firstDecreasing == 0) {
            nextStart = 0;
        } else if (firstDecreasing > 0) {
            nextStart = firstDecreasing - 1;
        } else {
            nextStart = number.length();
        }

        DecreasingResult result = new DecreasingResult(firstDecreasing, nextStart);

        return result;
    }


    private static String removeDigits(String number, int index) {

        StringBuilder result = new StringBuilder();

        if (index > 0) {
            result.append(number.substring(0, index));
        }

        if (index < number.length()) {
            result.append(number.substring(index + 1));
        }

        return result.toString();

    }
    
}

/*

Analysis: Let’s delete a digit from the number at each step. What’s the first digit to be deleted from the number 24635, in order to get the least number with the remaining digits? We may list all the remaining numbers after deleting a digit, in the following table:

Deleted Digit
Remaining Number
2
4635
4
2635
6
2435
3
2465
5
2463

The number 2435 is the least one in all remaining numbers, by deleting the digit 6. 
Notice that the digit 6 is the first digit in the number 24635 which is greater than the next digit.

Let’s delete another digit from the number 2435, the remaining least number after the first step. 
We may summarize the remaining numbers after delete every digit from it in the following table:

Deleted Digit Remaining Number
2           435
4           235
3           245
5           243

The number 235 is the least one in all remaining numbers, by deleting the digit 4. 
Notice that the digit 4 is the first digit in the number 2435 which is greater than the next digit.

The remaining three digits in the number 235 are increasingly sorted. 
What is the next digit to be deleted to get the least remaining number? 
Again, we may list the remaining numbers after deleting each digit in a table:

Deleted Digit Remaining Number
2               35
3               25
5               23

The number 23 is the least one in all remaining numbers, by deleting the last digit 5.

If we are going to deleting more digits from a number whose digits are increasingly 
sorted to get the least number, the last digit is deleted at each step.

Now we get the rules to delete digits to get the least remaining number: If there are 
digits who are greater than the next one, delete the first digit. 
If all digits in the number are increasingly sorted, delete the last digit gets deleted. 
The process repeats until the required k digits are deleted.

 */
