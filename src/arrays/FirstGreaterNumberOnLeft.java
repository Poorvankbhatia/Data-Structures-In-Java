package arrays;

import stack.ArrayStack;
import stack.Stack;

import java.util.Arrays;

/**
 * Created by poorvank on 8/27/15.
 */
public class FirstGreaterNumberOnLeft {
    
    public static void main(String[] args) {
        
        int[] arr = new int[]{5,3,2,4,8,6};
        int n = arr.length;

        Stack<Integer> stack = new ArrayStack<>();
        stack.push(n-1);
        
        for (int i=n-2;i>=0;i--) {
            
            while (!stack.isEmpty() && arr[i]>arr[stack.getTop()]) {
                arr[stack.getTop()] = arr[i];
                stack.pop();
            }
            stack.push(i);
            
        }
        
        while (!stack.isEmpty()) {
            arr[stack.pop()] = -1;
        }
        
        System.out.println(Arrays.toString(arr));
        
    }
    
}
