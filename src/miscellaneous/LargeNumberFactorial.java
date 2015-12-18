package miscellaneous;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 12/15/15.
 */
public class LargeNumberFactorial {
    
    public static void main(String[] args) {
        
        printFactorial(500);
        
    }
    
    private static void printFactorial(int n) {
        
        List<Integer> list = new ArrayList<>();
        
        list.add(1);
        
        for (int i=2;i<=n;i++) {
            multiply(list,i);
        }
        
        
        for (int i=list.size()-1;i>=0;i--) {
            System.out.print(list.get(i));
        }
    }
    
    private static void multiply(List<Integer> list,int n) {
        
        int carry = 0;
        
        for (int i=0;i<list.size();i++) {
            int product = list.get(i)*n + carry;
            list.set(i,product%10);
            carry = product/10;
        }
        
        while (carry!=0) {
            list.add(carry%10);
            carry = carry/10;
        }
        
    }
    
}

/*

Factorial of a large number
How to compute factorial of 100 using a C/C++ program?
Factorial of 100 has 158 digits. It is not possible to store these many digits even if we use long long int. 
Following is a simple solution where we use an array to store individual digits of the result.
 The idea is to use basic mathematics for multiplication.

The following is detailed algorithm for finding factorial.

factorial(n)
1) Create an array ‘res[]’ of MAX size where MAX is number of maximum digits in output.
2) Initialize value stored in ‘res[]’ as 1 and initialize ‘res_size’ (size of ‘res[]’) as 1.
3) Do following for all numbers from x = 2 to n.
……a) Multiply x with res[] and update res[] and res_size to store the multiplication result.

How to multiply a number ‘x’ with the number stored in res[]?
The idea is to use simple school mathematics. We one by one multiply x with every digit of res[]. 
The important point to note here is digits are multiplied from rightmost digit to leftmost digit.
 If we store digits in same order in res[], then it becomes difficult to update res[] without extra space.
 That is why res[] is maintained in reverse way, i.e., digits from right to left are stored.

multiply(res[], x)
1) Initialize carry as 0.
2) Do following for i = 0 to res_size – 1
….a) Find value of res[i] * x + carry. Let this value be prod.
….b) Update res[i] by storing last digit of prod in it.
….c) Update carry by storing remaining digits in carry.
3) Put all digits of carry in res[] and increase res_size by number of digits in carry.

Example to show working of multiply(res[], x)
A number 5189 is stored in res[] as following.
res[] = {9, 8, 1, 5}
x = 10

Initialize carry = 0;

i = 0, prod = res[0]*x + carry = 9*10 + 0 = 90.
res[0] = 0, carry = 9

i = 1, prod = res[1]*x + carry = 8*10 + 9 = 89
res[1] = 9, carry = 8

i = 2, prod = res[2]*x + carry = 1*10 + 8 = 18
res[2] = 8, carry = 1

i = 3, prod = res[3]*x + carry = 5*10 + 1 = 51
res[3] = 1, carry = 5

res[4] = carry = 5

res[] = {0, 9, 8, 1, 5} 

 */