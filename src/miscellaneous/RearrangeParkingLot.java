/*

There is a parking lot with only one empty spot. Given the initial state
of the parking lot and the final state. Each step we are only allowed to move a car
out of its place and move it into the empty spot. The goal is to find out the least movement needed to rearrange the parking lot
from the initial state to the final state.

Say the initial state is an array:

[1,2,3,0,4],
where 1,2,3,4 are different cars, and 0 is the empty spot.

And the final state is

[0,3,2,1,4].
We can swap 1 with 0 in the initial array to get [0,2,3,1,4] and so on. Each step swap with 0 only.

 */
package miscellaneous;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by poorvank on 13/12/16.
 */
public class RearrangeParkingLot {

    /*

    Initial state = [1,2,3,0,4]
    Final state   = [0,3,2,1,4]

     */

    public static  int stepCount(int[] initialState,int[] finalState) {

        int result = 0;

        HashMap<Integer,Integer> finalNoToPos = new HashMap<>();
        HashMap<Integer,Integer> initialNoToPos = new HashMap<>();


        for (int i=0;i<finalState.length;i++) {
            finalNoToPos.put(finalState[i],i);
        }

        for (int i=0;i<initialState.length;i++) {
            initialNoToPos.put(initialState[i],i);
        }

        /*

        Just move the car to be parked in the empty positon.
        Then move the car that needs to be placed in the new empty postion.
        By this way you'll move only those cars need to be moved only once.

         */
        while (!initialNoToPos.get(0).equals(finalNoToPos.get(0))) {
            int zeroPos = initialNoToPos.get(0);
            int noToBePlaced = finalState[zeroPos];
            int posOfNoToPlaceInzeroPos = initialNoToPos.get(noToBePlaced);
            result++;
            initialNoToPos.put(noToBePlaced,zeroPos);
            initialNoToPos.put(0,posOfNoToPlaceInzeroPos);
        }

        int stillPlacedIncorrect = 0;
        /*
          Ones empty is at its current position, the total count required to place the remaining cars correctly is #cars+1
         */
        for (Integer key : initialNoToPos.keySet()) {
            if(!Objects.equals(initialNoToPos.get(key), finalNoToPos.get(key))) {
                stillPlacedIncorrect++;
            }
        }

        return result + stillPlacedIncorrect+1;

    }


    public static void main(String[] args) {

        int[] initalState = new int[]{0,1,2,3,7,6,4,5};
        int[] finalState  = new int[]{4,6,5,1,7,3,2,0};

        System.out.println(stepCount(initalState,finalState));

    }

}


/*

7 swaps
state: e1237645
desired: 4651732e
4->e
412376e5
2->e
41e37625
5->e
4153762e
1->e
4e537621
6->e
46537e21
3->e
465e7321
1->e
4651732e

 */