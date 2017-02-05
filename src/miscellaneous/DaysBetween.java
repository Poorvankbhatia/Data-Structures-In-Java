/*

Given two dates, find total number of days between them. The count of days must be calculated in O(1) time and O(1) auxiliary space.

Examples:

Input: dt1 = {10, 2, 2014}
       dt2 = {10, 3, 2015}
Output: 393
dt1 represents "10-Feb-2014" and dt2 represents "10-Mar-2015"
The difference is 365 + 28

Input: dt1 = {10, 2, 2000}
       dt2 = {10, 3, 2000}
Output: 29
Note that 2000 is a leap year

Input: dt1 = {10, 2, 2000}
       dt2 = {10, 2, 2000}
Output: 0
Both dates are same

Input:   dt1 = {1, 2, 2000};
         dt2 = {1, 2, 2004};
Output: 1461
Number of days is 365*4 + 1

 */
package miscellaneous;

public class DaysBetween {

    private static int[] monthDays = new int[]{31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31};

    private static class Date {
        int y;
        int m;
        int d;

        public Date(int y, int m, int d) {
            this.y = y;
            this.m = m;
            this.d = d;
        }
    }

    public static int DaysBetween(int year1, int month1, int day1, int year2, int month2, int day2) {

        Date d1 = new Date(year1,month1,day1);
        Date d2 = new Date(year2,month2,day2);


        //Total no of days before d1

        int count1 = d1.y*365+d1.d;

        for (int i=1; i<d1.m; i++)
            count1 += DaysInMonth(i,d1.y);

        count1 += countLeapYears(d1);

        //Total no of days before d2

        int count2 = d2.y*365+d2.d;

        for (int i=1; i<d2.m; i++)
            count2 += DaysInMonth(i,d2.y);

        count2 += countLeapYears(d2);

        return count2-count1;

    }

    private static int countLeapYears(Date d)
    {
        int years = d.y;

        if (d.m <= 2)
            years--;


        return years / 4 - years / 100 + years / 400;
    }

    private static int DaysInMonth(int month,int years) {
        return monthDays[month];
    }

    public static void main(String[] args) {

        System.out.print(DaysBetween(2000,2,1,2004,2,1));

    }

}

/*

A Better and Simple solution is to count total number of days before dt1 from i.e., total days from 00/00/0000 to dt1,
then count total number of days before dt2. Finally return the difference between two counts.

Let the given two dates be "1-Feb-2000" and "1-Feb-2004"
dt1 = {1, 2, 2000};
dt2 = {1, 2, 2004};

Count number of days before dt1. Let this count be n1.
Every leap year adds one extra day (29 Feb) to total days.

n1 = 2000*365 + 31 + 1 + Number of leap years

Count of leap years for a date 'd/m/y' can be calculated
using following formula:
Number leap years
             = y/4 - y/100 + y/400 if m > 2
             = (y-1)/4 - (y-1)/100 + (y-1)/400 if m <= 2
All above divisions must be done using integer arithmetic
so that the remainder is ignored.

For 01/01/2000, leap year count is 1999/4 - 1999/100
+ 1999/400 which is 499 - 19 + 4 = 484
Therefore n1 is 2000*365 + 31 + 1 + 484

Similarly, count number of days before dt2. Let this
count be n2.

Finally return n2-n1

 */