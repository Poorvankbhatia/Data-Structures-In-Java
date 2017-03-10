package arrays;/*

Minimum Moves so that all the the even elements are in front.

 */

/**
 * Created by poorvank.b on 21/02/17.
 */
public class MinimumNumberOfMoves {

    public static void main(String[] args) {

        int[] array = new int[]{1,2,3};

        int start=0,end=array.length-1,count=0;

        while (start<end) {
            if(array[start]%2!=0 && array[end]%2==0) {
                count++;
                end--;
                start++;
            } else if(array[start]%2==0) {
                start++;
            } else if(array[end]%2!=0) {
               end--;
            }

        }

        System.out.print(count);


    }

}

