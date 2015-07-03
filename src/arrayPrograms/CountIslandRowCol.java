/*

Count number of islands where every island is row-wise and column-wise separated
Given a rectangular matrix which has only two possible values ‘X’ and ‘O’.
 The values ‘X’ always appear in form of rectangular islands and these islands are always
  row-wise and column-wise separated by at least one line of ‘O’s. Note that islands
   can only be diagonally adjacent. Count the number of islands in the given matrix.

Examples:

mat[M][N] =  {{'O', 'O', 'O'},
              {'X', 'X', 'O'},
              {'X', 'X', 'O'},
              {'O', 'O', 'X'},
              {'O', 'O', 'X'},
              {'X', 'X', 'O'}
             };
Output: Number of islands is 3

mat[M][N] =  {{'X', 'O', 'O', 'O', 'O', 'O'},
              {'X', 'O', 'X', 'X', 'X', 'X'},
              {'O', 'O', 'O', 'O', 'O', 'O'},
              {'X', 'X', 'X', 'O', 'X', 'X'},
              {'X', 'X', 'X', 'O', 'X', 'X'},
              {'O', 'O', 'O', 'O', 'X', 'X'},
             };
Output: Number of islands is 4

 */

package arrayPrograms;

/**
 * Created by poorvank on 6/8/15.
 */
public class CountIslandRowCol {
    
    public static void main(String[] args) {
        
        char[][] island = new char[][]{{'O', 'O', 'O'},
                                       {'X', 'X', 'O'},
                                       {'X', 'X', 'O'},
                                       {'O', 'O', 'X'},
                                       {'O', 'O', 'X'},
                                       {'X', 'X', 'O'}
        };
        
        System.out.println(count(island));
        
    }
    
    private static int count(char[][] islands) {
        
        int c = 0;
        int row = islands.length;
        int col = islands[0].length;
        
        for (int i=0;i<row;i++) {
            for (int j=0;j<col;j++) {
                
               if(islands[i][j]=='X') {
                   if((i==0 || islands[i-1][j]=='O') && (j==0 || islands[i][j-1]=='O')) {
                       c++;
                   }
               }
                
            }
        }
        
        return c;
        
    }
    
}

/*

The idea is to count all top-leftmost corners of given matrix. 
We can check if a ‘X’ is top left or not by checking following conditions.
1) A ‘X’ is top of rectangle if the cell just above it is a ‘O’
2) A ‘X’ is leftmost of rectangle if the cell just left of it is a ‘O’

 */
