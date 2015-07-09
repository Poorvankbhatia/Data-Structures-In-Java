/*

Given a building with infinite number of floors. The number of rooms on each floor are 20,21,20,21.. and so on starting from
the ground floor. The room number starts from 1 and keeps on increasing. Given a room number,
find the floor on which room lies and the position of room from the left.

 */

package miscellaneousPrograms;

/**
 * Created by poorvank on 7/9/15.
 */
public class RoomFloors {

    public static void main(String[] args) {

        findFloor(41);
        findFloor(56);


    }

    private static void findFloor(int n) {

        int x = n % 41;
        int room = 0;
        int floor = (n / 41) * 2;

        //if room number is a divisor of 41 meaning it is the last on even numbered floor
        if (x == 0) {
            room = 21;
        }
        // Dry run and understand
        else if (x > 0 && x < 21) {
            floor++;
            room = x;
        }
        //if greater than or equal to 21 then its 2 floors up
        else {
            room = x - 20;
            floor = floor + 2;
        }

        System.out.println("floor = " + floor + " room no =" + room);

    }

}
