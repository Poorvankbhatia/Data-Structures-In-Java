/*

Consider a big party where a log register for guest’s entry and exit times is maintained. 
Find the time at which there are maximum guests in the party. Note that entries in register are not in any order.

Example:

Input: arrl[] = {1, 2, 9, 5, 5}
       exit[] = {4, 5, 12, 9, 12}
First guest in array arrives at 1 and leaves at 4, 
second guest arrives at 2 and leaves at 5, and so on.

Output: 5
There are maximum 3 guests at time 5.  

 */

package miscellaneousPrograms;

import java.util.Arrays;

/**
 * Created by poorvank on 7/9/15.
 */
public class OverlapIntervals {
    
    public static void main(String[] args) {
        
        int[] arrival = new int[]{1, 2, 10, 5, 5};
        int[] departure = new int[]{4, 5, 12, 9, 12};

        Arrays.sort(arrival);
        Arrays.sort(departure);
        
        int n = arrival.length;
        
        int guestIn = 1,maxGuest = 1,i=1,j=0,maxTime=arrival[0];
        
        while (i<n && j<n) {
            
            if(arrival[i] <= departure[j]) {
                
                guestIn ++;
                
                if(maxGuest<guestIn) {
                    
                    maxGuest = guestIn;
                    maxTime = arrival[i];
                }
                
                i++;
                
            }
            else {
                
                guestIn--;
                j++;
                
            }
            
        }
        
        System.out.println("Maximum guest = " + maxGuest + " at time - " + maxTime);
        
    }
    
}

/*

use sorting n O(nLogn) time. The idea is to consider all events (all arrivals and exits) in sorted order. 
Once we have all events in sorted order, we can trace the number of guests at any time keeping track of 
guests that have arrived, but not exited.

Consider the above example.

    arr[]  = {1, 2, 10, 5, 5}
    dep[]  = {4, 5, 12, 9, 12}

Below are all events sorted by time.  Note that in sorting, if two
events have same time, then arrival is preferred over exit.
 Time     Event Type         Total Number of Guests Present
------------------------------------------------------------
   1        Arrival                  1
   2        Arrival                  2
   4        Exit                     1
   5        Arrival                  2
   5        Arrival                  3    // Max Guests
   5        Exit                     2
   9        Exit                     1
   10       Arrival                  2 
   12       Exit                     1
   12       Exit                     0 
Total number of guests at any time can be obtained by subtracting
total exits from total arrivals by that time.

So maximum guests are three at time 5.

 */