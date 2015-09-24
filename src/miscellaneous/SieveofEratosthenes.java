package miscellaneous;

/**
 * Created by poorvank on 9/24/15.
 */
public class SieveofEratosthenes {
    
    public static void main(String[] args) {
        
        int n = 30;
        System.out.println("\nNumber of primes - " + printPrimes(n));
        
    }
    
    private static int printPrimes(int n) {

        int[] array = new int[n];
        
        int count=0;
        
        for (int i=1;i<n;i++) {
            
            if(array[i]==0) {
                
                count++;
                System.out.print(i + 1 + " ");
                markMultiples(n, i + 1, array);
                
            }
            
        }

       
        return count;
        
        
    }
    
    private static void markMultiples(int n,int a,int[] arr) {
        
        int i=2,num=0;
        
        while ((num=(i*a))<=n) {
            
            arr[num-1] = 1;
            i++;
            
        }
        
    }
    
    
    
}

/*

Given a number n, print all primes smaller than or equal to n. It is also given that n is a small number.
For example, if n is 10, the output should be “2, 3, 5, 7″. If n is 20, the output should be “2, 3, 5, 7, 11, 13, 17, 19″.

The sieve of Eratosthenes is one of the most efficient ways to find all primes smaller than n when n is smaller than 
10 million or so (Ref Wiki).

Following is the algorithm to find all the prime numbers less than or equal to a given integer n by Eratosthenes’ method:

Create a list of consecutive integers from 2 to n: (2, 3, 4, …, n).
Initially, let p equal 2, the first prime number.
Starting from p, count up in increments of p and mark each of these numbers greater than p itself in the list. These numbers 
will be 2p, 3p, 4p, etc.; note that some of them may have already been marked.
Find the first number greater than p in the list that is not marked. If there was no such number, stop. Otherwise, let p now 
equal this number (which is the next prime), and repeat from step 3.
When the algorithm terminates, all the numbers in the list that are not marked are prime.

 */