package Arrays;

import java.util.HashSet;

/**
 * Created by poorvank on 6/8/15.
 */
public class DuplicateElementsKDistance {
    
    public static void main(String[] args) {
        
        int[] arr = new int[]{1, 2, 3, 1, 4, 5};
        
        int k = 3;
        
        System.out.println(findDuplicate(arr,k));
        
    }
    
    private static boolean findDuplicate(int[] arr,int k) {

        HashSet<Integer> set  =new HashSet<>();
        
        for (int i=0;i<arr.length;i++) {
            
            if(set.contains(arr[i])) {
                return true; 
            }
            
            set.add(arr[i]);
            
            if(i>=k) {
                set.remove(arr[i-k]);
            }
            
            
        }
        
        return false;
        
    }
    
}
