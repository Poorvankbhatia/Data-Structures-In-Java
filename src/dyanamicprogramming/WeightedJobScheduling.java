/**
 * Given N jobs where every job is represented by following three elements of it.
 * 1. Start Time
 * 2. Finish Time
 * 3. Profit or Value Associated
 *
 * Find the subset of jobs associated with maximum profit such that no two jobs in the subset overlap.
 *
 * Examples:
 *
 * Input:
 * Number of Jobs n = 4
 * Job Details {Start Time, Finish Time, Profit}
 * Job 1:  {1, 2, 50}
 * Job 2:  {3, 5, 20}
 * Job 3:  {6, 19, 100}
 * Job 4:  {2, 100, 200}
 *
 * Output:
 * Jobs involved in maximum profit are
 * Job 1:  {1, 2, 50}
 * Job 4:  {2, 100, 200}
 *
 */
package dyanamicprogramming;

import java.util.*;

/**
 * Created by poorvank on 26/12/16.
 */
public class WeightedJobScheduling {

    private static class Job {
        private Integer startTime;
        private Integer endTime;
        private Integer value;

        public Job(Integer startTime, Integer endTime, Integer value) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", value=" + value +
                    '}';
        }
    }

    private class WeightedJob {

        private Integer finalValue;
        private List<Job> jobs;

        public WeightedJob(Integer finalValue, List<Job> jobs) {
            this.finalValue = finalValue;
            this.jobs = jobs;
        }
    }

    private int lastNonConflict(Job[] jobs,int low,int high,int index) {

        while (low<=high) {

            int mid = low + (high-low)/2;

            if(jobs[mid].endTime<=jobs[index].startTime) {

                if(jobs[mid+1].endTime>=jobs[index].startTime) {
                    return mid;
                } else {
                    low = mid+1;
                }

            } else {
                high = mid-1;
            }

        }

        return -1;
    }

    public int maxProfit(Job[] jobs) {

        Arrays.sort(jobs,(Job j1,Job j2)->(j1.endTime).compareTo(j2.endTime));

        int n = jobs.length;
        WeightedJob[] table = new WeightedJob[n];

        for (int i=0;i<n;i++) {
            table[i] = new WeightedJob(0,new ArrayList<>());
        }

        table[0].finalValue = jobs[0].value;
        table[0].jobs.add(jobs[0]);

        for (int i=1;i<n;i++) {

            int currentProfit = jobs[i].value;
            int l = lastNonConflict(jobs,0,i-1,i);

            if(l!=-1) {
                currentProfit += table[l].finalValue;
            }

            // Store maximum of including and excluding
            if (currentProfit > table[i - 1].finalValue) {
                table[i].finalValue = currentProfit;

                // including previous jobs and current job
                table[i].jobs.add(jobs[l]);
                table[i].jobs.add(jobs[i]);


            } else {
                table[i] = table[i - 1];
            }


        }

        System.out.println("Optimal jobs:");
        for (Job job : table[n-1].jobs) {
            System.out.println(job);
        }

        return table[n-1].finalValue;

    }


    public static void main(String[] args) {

        Job arr[] = {new Job(3,5,20), new Job(1,2,50), new Job(6,19,100), new Job(2,100,200)};

        System.out.println("Max value : " + new WeightedJobScheduling().maxProfit(arr));

    }

}

/*

1) First sort jobs according to finish time.
2) Now apply following recursive process.
   // Here arr[] is array of n jobs
   findMaximumProfit(arr[], n)
   {
     a) if (n == 1) return arr[0];
     b) Return the maximum of following two profits.
         (i) Maximum profit by excluding current job, i.e.,
             findMaximumProfit(arr, n-1)
         (ii) Maximum profit by including the current job
   }

How to find the profit including current job?
The idea is to find the latest job before the current job (in
sorted array) that doesn't conflict with current job 'arr[n-1]'.
Once we find such a job, we recur for all jobs till that job and
add profit of current job to result.
In the above example, "job 1" is the latest non-conflicting
for "job 4" and "job 2" is the latest non-conflicting for "job 3".


Time : O(n Log n)

See NOTES

 */
