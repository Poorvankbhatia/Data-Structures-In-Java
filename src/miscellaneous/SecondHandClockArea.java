/*

Given a timestamp in hh:mm:ss format find whether second hand lies in larger or smaller area formed by hour and minute hands.

 */

package miscellaneous;

import java.util.Scanner;

/**
 * Created by poorvank on 12/14/15.
 */
public class SecondHandClockArea {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter hours (24 hour clock)");
        int hours = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Minutes");
        int minutes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Seconds");
        int seconds = scanner.nextInt();


        int hourDegree = (360 / 12) * hours;
        int minuteDegree = (360 / 60) * minutes;
        int secondDegree = (360 / 60) * seconds;

        System.out.println(hourDegree + " " + minuteDegree + " " + secondDegree);

        if (hourDegree > minuteDegree) {

            int area1 = hourDegree - minuteDegree;
            int area2 = 360 - area1;


            boolean area1IsBigger = area1 >= area2;

            if (secondDegree <= hourDegree && secondDegree >= minuteDegree) {

                if (area1IsBigger) {
                    System.out.println("Inside Larger Area");
                } else {
                    System.out.println("Inside Smaller Area");
                }

            } else if ((secondDegree <= hourDegree && secondDegree <= minuteDegree) || (secondDegree >= hourDegree && secondDegree >= minuteDegree)) {

                if (area1IsBigger) {
                    System.out.println("Inside Larger Area");
                } else {
                    System.out.println("Inside Smaller Area");
                }
            }

        } else if (minuteDegree > hourDegree) {

            int area1 = minuteDegree - hourDegree;
            int area2 = 360 - area1;

            boolean area1IsBigger = area1 >= area2;

            if (secondDegree >= hourDegree && secondDegree <= minuteDegree) {

                if (area1IsBigger) {
                    System.out.println("Inside Larger Area");
                } else {
                    System.out.println("Inside Smaller Area");
                }

            } else if ((secondDegree <= hourDegree && secondDegree <= minuteDegree) || (secondDegree >= hourDegree && secondDegree >= minuteDegree)) {

                if (area1IsBigger) {
                    System.out.println("Inside Larger Area");
                } else {
                    System.out.println("Inside Smaller Area");
                }
            }

        } else {
            if (minuteDegree == hourDegree) {
                System.out.println("All three hands collide");
            } else {
                System.out.println("Inside the only area");
            }
        }

    }

}
