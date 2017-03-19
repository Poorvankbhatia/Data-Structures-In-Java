package utility;

import java.util.Arrays;

/**
 * Created by poorvank.b on 19/03/17.
 */
public class LpsComputation {

    public static int[] compute(String s) {

        if(s==null || s.length()==0) {
            return new int[]{};
        }
        int n =s.length();
        int[] lpsArray = new int[n];

        lpsArray[0] = 0;

        int j=0,i=1;

        while (i<n) {

            if(s.charAt(i)==s.charAt(j)) {
                lpsArray[i] = j+1;
                i++;
                j++;
            } else {
                if(j!=0) {
                    j = lpsArray[j-1];
                } else {
                    lpsArray[i]=0;
                    i++;
                }
            }

        }

        return lpsArray;

    }

    public static void main(String[] args) {
        String s = "ababa";
        System.out.print(Arrays.toString(compute(s)));
    }

}


/*

We assume that LPS[0] to LPS[0..i-1] are already computed when we are trying to compute LPS[i].

Let's say : j-> length of longest proper prefix which is also proper suffix for substring[0..i-1] , So it goes from 0 to j-1;

Hence The string which starts from 0 to j-1(S1) will be same as the string which ends at i-1 and has a length ->j(S2)

So if:
s.charAt(i)==s.charAt(j)

Since S1==S2 and character at index j==i hence the prefix which starts at 0 and ends at j will be equal to suffix which ends at index i
 and has now length j+1;

 So lps[i]= j+1; then we increment i & j.


Now if :

if:
s.charAt(i)!=s.charAt(j)

Now since S1==S2 we need to find the next longest string "S" which is also proper prefix and proper suffix of substring 0..i-1
Then we update j accordingly and compare again str.charAt(j) and str.charAt(i) // If equal lps[i]=S.length()+1


How to find "S"?
Lets assume we found "S" , Since S1==S2 , and S is a suffix for S2. Hence S should also be a suffix of S1.
Now if we see S1. It is obvious that S has to be the longest proper prefix which is also proper suffix for substring[0..j-1]
which is nothing but lps[j-1].


DO WATCH :

https://www.youtube.com/watch?v=tWDUjkMv6Lc

 */