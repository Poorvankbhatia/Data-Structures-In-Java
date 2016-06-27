/*

Find the first circular tour that visits all petrol pumps
Suppose there is a circle. There are n petrol pumps on that circle. You are given two sets of data.

1. The amount of petrol that petrol pump will give.
2. Distance from that petrol pump to the next petrol pump.

Calculate the first point from where a truck will be able to complete the circle
 (The truck will stop at each petrol pump and it has infinite capacity). 
 Expected time complexity is O(n). Assume for 1 litre petrol, the truck can go 1 unit of distance.

For example, let there be 4 petrol pumps with amount of petrol and distance 
to next petrol pump value pairs as {4, 6}, {6, 5}, {7, 3} and {4, 5}. 
The first point from where truck can make a circular tour is 2nd petrol pump.
Output should be “start = 1″ (index of 2nd petrol pump).

 */

package miscellaneous;

/**
 * Created by poorvank on 8/2/15.
 */


public class CircularTour {

    private static class PetrolPump {

        int petrol;
        int distance;

        PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {

        PetrolPump[] pumps = new PetrolPump[]{new PetrolPump(6, 4), new PetrolPump(3, 6), new PetrolPump(7, 3)};

        int n = pumps.length;
        int start = 0;
        int end = 1;

        int currentPetrol = pumps[0].petrol - pumps[0].distance;

        while (currentPetrol < 0 || start != end) {

            while (currentPetrol < 0 && start != end) {

                currentPetrol -= pumps[start].petrol - pumps[start].distance;

                start = (start + 1) % n;


                if (start == 0) {
                    System.out.println("No solution exists");
                    start = end;
                    break;
                }

            }

            currentPetrol += pumps[end].petrol - pumps[end].distance;

            end = (end + 1) % n;

        }


        System.out.println(start);

    }

}

/*

In this question ,you need to find from which petrol pump you should start your journey so that you can reach 
all other pump and finally to the starting point forming a circular track without having negative amount of petrol 
in your truck.

We will take an example of {4, 6}, {6, 5}, {7, 3} and {4, 5}.
In the beginning you will have 4 lt of petrol but have to go 6 km to reach the next petrol pump and since mileage 
of the truck is 1km/ liter hence it will run out in next 4 km.

4 - 6= -2. curr_petrol = -2 ,start = 0, end= 1
Enter 1st while loop ,enter 2nd while loop. since you can't start from this petrol pump hence start index will 
increase by first making the curr_petrol 0 ( -2+6-4 ). 
{ It also checks if you aren't just trapped in a loop to find the starting point by checking (start==0), as you 
have already visited the 0th index petrol pump in the very beginning of the function and if you are visiting it 
again for finding the starting point that means there's no starting point so return with -1.}

start index will increase by one.
starting from second petrol pump now.
6-5=1, 1litre petrol left after reaching 3rd petrol pump.
1+7-3 =5lt petrol left when you reach 4th pump.
5+4-5=4lt when you reach 0th. (since its a circular track so after reaching 4th you will move to 0th).

4+4-6=2lt when you reach 1st. in here start==end && curr_petrol >=0. so exit from 1st while loop and return the 
starting point.


Time Complexity: Seems to be more than linear at first look.
 If we consider the items between start and end as part of a circular queue, 
 we can observe that every item is enqueued at most two times to the queue. 
 The total number of operations is proportional to total number of enqueue operations. 
 Therefore the time complexity is O(n).

Auxiliary Space: O(1)



 */