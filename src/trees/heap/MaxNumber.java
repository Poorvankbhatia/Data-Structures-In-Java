/*

Given two arrays where digits of one array  represent a number,maximize the number
by replacing it with elements of second array.
eg:
arr = {3,1,4,5,6}
sec = {1,9,5,2,3}
after replacement
arr = {9,5,4,5,6}
one digit of sec can be used to replace only once.

 */

package trees.heap;

import java.util.Arrays;

/**
 * Created by poorvank on 12/19/15.
 */
public class MaxNumber {
    
    public static void main(String[] args) {
        
        int[] primaryArray = new int[]{3,1,4,5,6};
        int[] secArray = new int[]{1,9,5,2,3};
        int length = secArray.length;
        
        maxHeap(secArray,length);
        
        for (int i=0;i<primaryArray.length;i++) {
            
            if(primaryArray[i]<secArray[0]) {
                primaryArray[i] = secArray[0];
                secArray[0] = secArray[length-1];
                maxHeap(secArray,length-1);
            }
            
        }
        
        System.out.println(Arrays.toString(primaryArray));
        
    }
    
    
    
    private static void maxHeap(int[] arr,int length) {
        
        for (int i=length/2;i>=0;i--) {
            heapify(arr,i,length);    
        }
        
    }
    
    private static void heapify(int[] arr,int i,int length) {
        
        int leftChild = (2*i)+1;
        int rightChild = (2*i)+2;
        int n = arr[i];
        
        while (rightChild<=length-1) {
            
            if(n>=arr[leftChild] && n>=arr[rightChild]) {
                arr[i] = n;
                return;
            }
            else if(arr[leftChild]>arr[rightChild]) {
                arr[i] = arr[leftChild];
                i = leftChild;
            }
            else if(arr[rightChild]>arr[leftChild]) {
                arr[i] = arr[rightChild];
                i = rightChild;
            }
            
            leftChild = (2*i)+1;
            rightChild = (2*i)+2;
            
        }
        
        if(leftChild==length-1 && arr[leftChild]>n) {
            arr[i] = arr[leftChild];
            i=leftChild;
        }
        
        arr[i] = n;
        
        
    }
    
}

/*

construct a max heap of sec array.And ran a loop checking if the max element of sec was greater than the element
in arr and then  replaced it in arr and deleted the same from the maxheap.

 */