package arrayPrograms;

import java.util.Arrays;

/**
 * Created by poorvank on 6/12/15.
 */
public class BooleanArray {
    
    public static void main(String[] args) {

        int[] arr = new int[]{0,1};

        arr[arr[0]] = arr[arr[0]];
        arr[arr[1]] = arr[arr[1]];
        
        System.out.println(Arrays.toString(arr));
        
    }
    
}
