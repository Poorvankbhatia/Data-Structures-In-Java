/*

You have to rotate an n*n array right by 90 degree.

 */

package interviews;

/**
 * Created by poorvank on 5/7/15.
 */
public class RotateArray {

    public static void main(String[] args) {

        int[][] array = new int[][]{{1, 2, 3, 4},
                                    {5, 6, 7, 8},
                                    {9, 10, 11, 12}};

        int row = array.length;
        int col = array[0].length;

        System.out.println("Source Array ");
        printArray(array, row, col);

        rotateArray(array, row, col);

    }

    private static void printArray(int[][] array, int row, int col) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void rotateArray(int[][] array, int row, int col) {

        int[][] destinationArray = new int[col][row];

        for (int i = 0; i < col; i++) {
            int k = row;
            for (int j = 0; j < row; j++) {
                //System.out.println("i,j = " + i + "," + j + " && k-1,j= " + (k - 1) + "," + j);
                destinationArray[i][j] = array[k - 1][i];
                k--;
            }
        }

        System.out.println("Destination Array");
        printArray(destinationArray, col, row);

    }

}


/*

Each row is transformed into different column of final image. We need to construct columns of final image. 
See the following algorithm (transformation)

for(r = 0; r < m; r++)
{
   for(c = 0; c < n; c++)
   {
      // Hint: Map each source element indices into
      // indices of destination matrix element.
       dest_buffer [ c ] [ m - r - 1 ] = source_buffer [ r ] [ c ];
   }
}

 */