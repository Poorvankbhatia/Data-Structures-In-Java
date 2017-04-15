/**
 * Given an array filled up with 1 to n^2 in randomized order. Fill it in a square matrix of size n.
 */
package arrays.twodimensionalarrays;

/**
 * Created by poorvank.b on 15/04/17.
 */
public class FillRandomizedArray {

    private static void fillArray(int[] arr,int n) {
        int[][] matrix = new int[n][n];
        for (int anArr : arr) {
            int row = (anArr - 1) / n;
            int col = (anArr - 1) % n;
            matrix[row][col] = anArr;
        }

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                System.out.print(matrix[i][j] +" ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        int[] arr = {1,7,8,4,6,5,9,3,2};
        int n =3;
        fillArray(arr,n);
    }

}
