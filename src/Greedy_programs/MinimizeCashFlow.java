/*

Minimize Cash Flow among a given set of friends who have borrowed money from each other
Given a number of friends who have to give or take some amount of money from one another.
Design an algorithm by which the total cash flow among all the friends is minimized.


 */

package Greedy_programs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by poorvank on 4/4/15.
 */

public class MinimizeCashFlow {
    
    private static int maxCreditIndex;
    private static int maxDebitIndex;
    
    public static void main(String[] args) {
        
        System.out.println("Enter number of friends");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] cashGraph = new int[n][n];

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                System.out.println("Enter amount " + i + " has to pay " + j);
                int amount = scanner.nextInt();
                cashGraph[i][j] = amount;
            }
        }

//        int[][] cashGraph = new int[][]{
//                { 0, 617, 1867},
//                { 0, 0, 317},
//                { 0, 0, 0},
//        };
        
        netAmountPaid(cashGraph);
        
    }
    
    
    private static void netAmountPaid(int[][] cashGraph) {
        
        int[] amount = new  int[cashGraph.length];
        
        for (int i=0;i<amount.length ; i++) {
            for (int p =0;p<amount.length;p++) {
                amount[p] += cashGraph[i][p] - cashGraph[p][i];
            }
        }
        
        System.out.println(Arrays.toString(amount));
        
        cashFlowRec(amount);
    }
    
    private static int minOf2(int a ,int b) {
        
        return Math.abs(a)>Math.abs(b)?Math.abs(b):Math.abs(a);
        
    }
    
    private static void cashFlowRec(int[] amount) {
        
        maxMinArray(amount);
        
        if(amount[maxCreditIndex] == 0 && amount[maxDebitIndex] == 0) {
            return;
        }
        
        int min = minOf2(amount[maxCreditIndex],amount[maxDebitIndex]);
        
        amount[maxCreditIndex] -=min;
        amount[maxDebitIndex] +=min;
        
        System.out.println("person~" + maxDebitIndex + " pays " + Math.abs(min) + " Rs/- to person~" + maxCreditIndex);
        
        cashFlowRec(amount);
    }
    
    private static void maxMinArray(int[] amount) {

        for (int i=1;i<amount.length;i++) {
            if(amount[i] > amount[maxCreditIndex]) {
                maxCreditIndex = i;
            }
            else if(amount[i] < amount[maxDebitIndex]) {
                maxDebitIndex = i;
            }
        }
        
    }
    
}


/*

The idea is to use Greedy algorithm where at every step, settle all amounts of one person and recur for
 remaining n-1 persons.
How to pick the first person? To pick the first person, 
calculate the net amount for every person where net amount is obtained by subtracting 
all debts (amounts to pay) from all credits (amounts to be paid). Once net amount 
for every person is evaluated, find two persons with maximum and minimum net amounts. 
These two persons are the most creditors and debtors. The person with minimum of two 
is our first person to be settled and removed from list. Let the minimum of two amounts be x. 
We pay ‘x’ amount from the maximum debtor to maximum creditor and settle one person. 
If x is equal to the maximum debit, then maximum debtor is settled, else maximum creditor is settled.

The following is detailed algorithm.

Do following for every person Pi where i is from 0 to n-1.
1) Compute the net amount for every person. The net amount for person ‘i’ can be
 computed be subtracting sum of all debts from sum of all credits.

2) Find the two persons that are maximum creditor and maximum debtor. 
Let the maximum amount to be credited maximum creditor be maxCredit and 
maximum amount to be debited from maximum debtor be maxDebit. Let the maximum debtor be Pd and maximum creditor be Pc.

3) Find the minimum of maxDebit and maxCredit. Let minimum of two be x. 
Debit ‘x’ from Pd and credit this amount to Pc

4) If x is equal to maxCredit, then remove Pc from set of persons and recur for remaining (n-1) persons.

5) If x is equal to maxDebit, then remove Pd from set of persons and recur for remaining (n-1) persons.

 */