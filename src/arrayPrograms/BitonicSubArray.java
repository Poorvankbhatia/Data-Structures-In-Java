package arrayPrograms;

import java.util.Arrays;

/**
 * Created by poorvank on 5/23/15.
 */
public class BitonicSubArray {
    
    public static void main(String[] args) {
        
        int[] array = new int[]{12, 4, 78, 90, 45, 23};
        subArray(array);
    }
    
    public static void subArray(int[] array) {
        
        int[] increasing = new int[array.length];
        int[] decreasing = new int[array.length];
        
        for (int i=0;i<array.length;i++) {
            increasing[i] = 1;
            decreasing[i] = 1;
        }
        
        for (int i=1;i<array.length;i++) {
            if(array[i]>array[i-1]) {
                increasing[i] = increasing[i-1] + 1;
            }
        }
        
        for (int i=array.length-2;i>=0;i--) {

            if(array[i]>array[i+1]) {
                decreasing[i] = decreasing[i+1] + 1;
            }

        }
        
        int maxLength = 0;
        
        System.out.println(Arrays.toString(increasing) + " " + Arrays.toString(decreasing));
        
        for (int i=0;i<array.length;i++) {
            
            if(maxLength<increasing[i]+decreasing[i]-1) {
                maxLength = increasing[i]+decreasing[i]-1;
            }
        }
        
        System.out.println(maxLength);
        
    }
    
}


