/*

The cost of a stock on each day is given in an array, find the max profit that you can make by 
buying and selling in those days. For example, if the given array is {100, 180, 260, 310, 40, 535, 695}, 
the maximum profit can earned by buying on day 0, selling on day 3. Again buy on day 4 and sell on day 6. 
If the given array of prices is sorted in decreasing order, then profit cannot be earned at all.


 */

package arrayPrograms;

import java.util.ArrayList;

/**
 * Created by poorvank on 4/16/15.
 */

class Interval {

    public int sell;
    public int buy;

}

public class StockBuyAndSell {

    public static void main(String[] args) {

        int price[] = new int[]{100, 180, 260, 310, 40, 535, 695};

        ArrayList<Interval> arrayList = new ArrayList<>();

        int i = 0;
        boolean flag = false;

        while (i < price.length) {

            int count = 0;
            int minElement = price[i];

            Interval interval = new Interval();

            //Finding Local Minimum
            while (i < price.length - 1 && (price[i] >= price[i + 1])) {
                i++;
            }


            if (i == price.length - 1) {
                if (!flag) {
                    System.out.println("There is no day when buying the stock will make profit");
                }
                break;
            }

            flag = true;

            interval.buy = i++;

            //Finding local maximum
            while (i < price.length && (price[i] >= price[i - 1])) {
                i++;
            }

            interval.sell = --i;

            arrayList.add(interval);

        }

        for (Interval interval : arrayList) {
            System.out.println("Buy at - " + interval.buy + "  and sell at " + interval.sell);
        }

    }

}


/*

If we are allowed to buy and sell only once, then we can use following algorithm. Here we are allowed to buy and sell 
multiple times. Following is algorithm for this problem.
1. Find the local minima and store it as starting index. If not exists, return.
2. Find the local maxima. and store it as ending index. If we reach the end, set the end as ending index.
3. Update the solution (Increment count of buy sell pairs)
4. Repeat the above steps if end is not reached.

The outer loop runs till i becomes n-1. The inner two loops increment value of i in every iteration. 
So overall time complexity is O(n)

If you buy at 40 and sell it at 695 then profit you will make is 655, 
but if first buy at 100 and then sell it at 310, and again buy at 40 and sell it at 695 then 
total profit you will make is 210 + 655 = 865.

 */