/*

Function to find Number of customers who could not get a computer
Write a function “runCustomerSimulation” that takes following two inputs
a) An integer ‘n': total number of computers in a cafe and a string:
b) A sequence of uppercase letters ‘seq': Letters in the sequence occur in pairs. 
The first occurrence indicates the arrival of a customer; the second indicates the departure of that same customer.

A customer will be serviced if there is an unoccupied computer. No letter will occur more than two times.
Customers who leave without using a computer always depart before customers who are currently 
using the computers. There are at most 20 computers per cafe.

For each set of input the function should output a number telling how many customers, 
if any walked away without using a computer. Return 0 if all the customers were able to use a computer.

runCustomerSimulation (2, “ABBAJJKZKZ”) should return 0

runCustomerSimulation (3, “GACCBDDBAGEE”) should return 1 as ‘D’ was not able to get any computer

runCustomerSimulation (3, “GACCBGDDBAEE”) should return 0

runCustomerSimulation (1, “ABCBCA”) should return 2 as ‘B’ and ‘C’ were not able to get any computer.

runCustomerSimulation(1, “ABCBCADEED”) should return 3 as ‘B’, ‘C’ and ‘E’ were not able to get any computer.


 */

package miscellaneous;

/**
 * Created by poorvank on 4/14/15.
 */
public class ComputerSimulation {

    public static void main(String[] args) {

        System.out.println(runCustomerSimulation(2, "ABBAJJKZKZ"));
        System.out.println(runCustomerSimulation(3, "GACCBDDBAGEE"));
        System.out.println(runCustomerSimulation(3, "GACCBGDDBAEE"));
        System.out.println(runCustomerSimulation(1, "ABCBCA"));


    }

    public static int runCustomerSimulation(int noOfComputers, String string) {
        
        /*
        
           0 - person has not entered
           1 - entered but did not get
           2 - entered and got a computer
           
         */

        int[] seen = new int[26];
        int occupiedCount = 0;

        int result = 0;

        for (int i = 0; i < string.length(); i++) {

            int pos = string.charAt(i) - 'A';

            //Entered First time
            if (seen[pos] == 0) {

                seen[pos] = 1;

                if (occupiedCount < noOfComputers) {
                    occupiedCount++;
                    seen[pos] = 2;
                } else {
                    result++;
                }

            } else {

                if (seen[pos] == 2) {
                    occupiedCount--;
                }
                seen[pos] = 0;
            }


        }

        return result;

    }

}


/*





1) Initialize result as 0.

2) Traverse the given sequence. While traversing, keep track of occupied computers (this can be done by keeping 
track of characters which have appeared only once and a computer was available when they appeared). At any point, 
if count of occupied computers is equal to ‘n’, and there is a new customer, increment result by 1.

The important thing is to keep track of existing customers in cafe in a way that can indicate whether
the customer has got a computer or not. Note that in sequence “ABCBCADEED”, customer ‘B’ did not get a seat, 
but still in cafe as a new customer ‘C’ is next in sequence.




the answer for the case : 1 , ABAB is 1
since customers who doesn't get seat leave before the customers having seats .
 */