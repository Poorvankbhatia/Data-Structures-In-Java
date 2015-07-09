package dyanamicProgramming;

/**
 * Created by poorvank on 5/22/15.
 */
public class CuttingRod {
    
    public static void main(String[] args) {
        
        int[] prices = new int[]{1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(cutRecursive(prices,prices.length));
        cutDP(prices,prices.length);
        
    }
    
    private static int cutRecursive(int[] prices,int n) {
        
        if(n==0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        
        for (int i=0;i<n;i++) {
            
            max = Math.max(max,prices[i]+cutRecursive(prices,n-i-1));
            /*
                max(prices[0]+prices[7],prices[1]+prices[6],prices[2]+prices[5],prices[3]+prices[4]...)
             */
        }
        
        return max;
    }
    
    private static void cutDP(int[] prices,int n) {
        
        int[] val = new int[n+1];
        
        val[0] = 0;
        
        for (int i=1;i<=n;i++) {
            
            int max = Integer.MIN_VALUE;
            
            for (int j=0;j<i;j++) {
                max = Math.max(max,prices[j] + val[i-j-1]);
            }
            
            val[i] = max;
            
        }
        
        System.out.println(val[n]);
    }
    
}


/*

1) Optimal Substructure: 
We can get the best price by making a cut at different positions and comparing the values 
obtained after a cut. We can recursively call the same function for a piece obtained after a cut.

Let cutRoad(n) be the required (best possible price) value for a rod of lenght n. cutRod(n) can be written as following.

cutRod(n) = max(price[i] + cutRod(n-i-1)) for all i in {0, 1 .. n-1}


Considering the above implementation, following is recursion tree for a Rod of length 4.

cR() ---> cutRod() 

                            cR(4)
                 /        /      \     \
                /        /        \      \
            cR(3)       cR(2)     cR(1)   cR(0)
           /  |  \       /  \       |
          /   |   \     /    \      |  
     cR(2) cR(1) cR(0) cR(1) cR(0) cR(0)
    / \       |          |
   /   \      |          |   
 cR(1) cR(0) cR(0)      cR(0)
In the above partial recursion tree, cR(2) is being solved twice. We can see that there are 
many subproblems which are solved again and again. Since same subroblems are called again,
this problem has Overlapping Subprolems property.



Better comparison: rk=max(pi+rk−i) over all 1≤i≤k

Here's a table showing what each ri depends on
r4
i	ri	Minimum of
1	r1	p1+r0
2	r2	p1+r1,p2+r0
3	r3	p1+r2,p2+r1,p3+r0
4	10	p1+r3,p2+r2,p3+r1,p4+r0
...	...	...

How do we fill in table entry rk:
For each possible first cut (ie p1..pk),
Calculate the sum of the value of that cut (ie pi) and the best that could be done with the rest of the rod (ie rk−i).
Choose the largest sum (pi+rk−i).
Notice that each value of ri depends only on values higher in the table

Repeat the value/price table for easy reference:
length i	1	2	3	4	5	6	7	8
price pi	1	5	8	9	10	17	17	20

i	ri	optimal solution
1	1	1 (no cuts)
2	5	2 (no cuts)
3	8	3 (no cuts)
4	10	2 + 2
5	13	2 + 3
6	17	6 (no cuts)
7	18	1 + 6 or 2 + 2 + 3
8	22	2 + 6


Examples:
r4=max(p1+r3,p2+r2,p3+r1,p4+r0)=max(1+8,5+5,8+1,9+0)=max(9,10,9,9)=10
r5=max(p1+r4,p2+r3,p3+r2,p4+r1,p5+r0)=max(1+10,5+8,8+5,9+1,10+0)=max(11,13,13,10,10)=13
Assume r0=0
We will discuss finding the solution (ie 2,3) later

 */