/*

Find memory conflicts among multiple threads
Consider a RAM organized in blocks. There are multiple processes running on the system. 
Every application gets below information.

(Thread T, Memory Block M, time t, R/W) which essentially tells that the thread T was using
 memory block M at time t and operation could be read or write.

Memory conflict is defined as –
– Multiple read operations at the same location are not cause of conflict.
– One write operation between x+5 to x-5 to location M, will be cause of conflict for a thread accessing 
location M at time x where x is some time in standard unit of time measurement.
– Example – If thread T1 accessed memory location M at time x+1 and if a thread T2 accesses location 
M before time x+6, then T1 and T2 are candidate of conflict given one of them does write operation.

You are given with the list of threads accessing memory locations, you have to find all conflicts.

Example–

Input:
  
  (2, 432, 2, W)
  (8, 432, 8, R)
  (1, 512, 1, R)
  (3, 512, 3, R)
  (5, 512, 5, W)
  (7, 835, 7, R)
  (4, 932, 4, R)
  (6, 932, 6, R)
 
  

Output:
Thread 1 & 3 conflict with thread 5
All other operations are safe. 

 */

package miscellaneous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by poorvank on 7/17/15.
 */

public class ThreadConflicts {

    private static class Thread {

        public int id;
        public int memoryBlock;
        public int time;
        public char access;

        public Thread(int id, int memoryBlock, int time, char access) {
            this.id = id;
            this.memoryBlock = memoryBlock;
            this.time = time;
            this.access = access;
        }
    }

    private static class ThreadComparator implements Comparator<Thread> {

        @Override
        public int compare(Thread t1, Thread t2) {

            if (t1.memoryBlock == t2.memoryBlock) {
                if (t1.time > t2.time) {
                    return 1;
                } else if (t1.time < t2.time) {
                    return -1;
                } else {
                    return 0;
                }
            } else {
                if (t1.memoryBlock > t2.memoryBlock) {
                    return 1;
                } else if (t1.memoryBlock < t2.memoryBlock) {
                    return -1;
                } else {
                    return 0;
                }
            }

        }

    }

    public static void main(String[] args) {

        List<Thread> threadList = new ArrayList<>();
        threadList.add(new Thread(1, 512, 1, 'R'));
        threadList.add(new Thread(2, 432, 2, 'W'));
        threadList.add(new Thread(3, 512, 3, 'R'));
        threadList.add(new Thread(4, 932, 4, 'R'));
        threadList.add(new Thread(5, 512, 5, 'W'));
        threadList.add(new Thread(6, 932, 6, 'R'));
        threadList.add(new Thread(7, 835, 7, 'R'));
        threadList.add(new Thread(8, 432, 8, 'R'));


        Collections.sort(threadList, new ThreadComparator());

        for (int i = 1; i < threadList.size(); i++) {

            if (threadList.get(i).memoryBlock == threadList.get(i - 1).memoryBlock) {


                if (threadList.get(i).time <= threadList.get(i).time + 5) {

                    int j = i - 1;

                    while (threadList.get(i).memoryBlock == threadList.get(j).memoryBlock &&
                            threadList.get(i).time <= threadList.get(j).time + 5 &&
                            j >= 0) {

                        if (threadList.get(i).access == 'W' && threadList.get(i).access == 'W') {

                            System.out.println("Conflict will occur between " + threadList.get(i).id + " & " + threadList.get(j).id);

                        }

                        j--;


                    }


                }


            }


        }

        System.out.println("All other threads are safe");


    }

}




/*

The idea is to sort all threads by memory block and if memory block is same, then by time. 
Once we have all threads sorted, we can traverse all threads one by one. For every thread being traversed,
we simply need to check previous
adjacent threads of same block as threads are sorted by time.

read comparable vs comparator notes

 */