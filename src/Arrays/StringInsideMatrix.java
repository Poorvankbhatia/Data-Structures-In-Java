/*

You have to find a string in two-dimensional array. 
The input contains 2-D array of characters and given string. 
You can move in one of eight directions . 
The output contains location of first letter of string if string found completely, otherwise return -1. 
Any one out of multiple answers is accepted, if possible.
For example, Input:
b	t	g
p	a	d
r	k	j

String: rat
Output: (2,0)

 */

package arrays;

/**
 * Created by poorvank on 6/23/15.
 */
public class StringInsideMatrix {
    
    //Goto any 8 directions
    private static int[] x = new int[]{1,1,1,-1,-1,-1,0,0};
    private static int[] y = new int[]{1,0,-1,1,0,-1,1,-1};
    
    public static void main(String[] args) {
        
        char[][] arr = new char[][]{{'a','c','p','r','c'},
                                    {'x','s','o','p','c'},
                                    {'v','o','v','n','i'},
                                    {'w','g','f','m','n'},
                                    {'q','a','t','i','t'}};
        
        String find = "microsoft";
        
        System.out.print(exists(find,arr,arr.length,arr[0].length,0,0,0));
    }
    
    private static boolean exists(String find,char[][] array,int row,int col,int r,int c,int index) {
        
        
        if(index==find.length()) {
            return true;
        }
        
        if(isSafe(find,r,c,row,col,array,index)) {
            System.out.println(r+","+c);
            if(index==find.length()-1) {
                return true;
            }
            for (int n=0;n<8;n++) {
                
                int newRow = r+x[n];
                int newCol = c+y[n];
                
                if((newRow>=0 && newRow<row && newCol>=0 && newCol<col) && (array[newRow][newCol]==find.charAt(index+1))) {

                     if(exists(find,array,row,col,newRow,newCol,index+1)) {
                         return true;
                     }
                     
                }
            }
        }
        else {
            if(c<col-1) {
                   //Check all 
                    if(exists(find,array,row,col,r,c+1,index)) {
                        return true;
                    }
            }
             if(r<row-1) {
                    if (exists(find,array,row,col,r+1,0,index)) {
                        return true;
                    }
            }
        }
        return false;
    }
    
    private static boolean isSafe(String find,int r,int c,int row,int col,char[][] array,int index) {
        
        if(r<row && c<col && c>=0 && r>=0 && array[r][c]==find.charAt(index)) {
            return true;
        }
        
        return false;
        
    }
    
}
