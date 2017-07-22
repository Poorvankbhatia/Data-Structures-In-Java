/*

Common elements in all rows of a given matrix
Given an m x n matrix, find all common elements present in all rows in O(mn) time and one traversal of matrix.

Example:

Input:
mat[4][5] = {{1, 2, 1, 4, 8},
             {3, 7, 8, 5, 1},
             {8, 7, 7, 3, 1},
             {8, 1, 2, 7, 9},
            };

Output:
1 8 or 8 1
8 and 1 are present in all rows.

 */
package arrays.twodimensionalarrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 20/07/17.
 */
public class CommonElements {

    public static void common(int[][] matrix) {

        if(matrix==null || matrix.length==0) {
            return;
        }

        Map<Integer,Integer> map = new HashMap<>();
        int m = matrix.length;
        int n = matrix[0].length;

        for (int j=0;j<n;j++) {
            map.put(matrix[0][j],1);
        }

        for (int i=1;i<m;i++) {
            for (int j=0;j<n;j++) {

                if(map.containsKey(matrix[i][j]) && map.get(matrix[i][j])==i) {

                    map.put(matrix[i][j],i+1);

                    if(i==m-1) {
                        System.out.println(matrix[i][j] + "  ");
                    }

                }

            }
        }

    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 2, 1, 4, 8},
                {3, 7, 8, 5, 1},
                {8, 7, 7, 3, 1},
                {8, 1, 2, 7, 9},
        };

        common(arr);
    }

}

/*

The idea is to use maps. We initially insert all elements of the first row in an map. For every other element in remaining rows,
 we check if it is present in the map. If it is present in the map and is not duplicated in current row, we increment count of the
 element in map by 1, else we ignore the element. If the currently traversed row is the last row, we print the element if it has
 appeared m-1 times before.

The time complexity of this solution is O(m * n) and we are doing only one traversal of the matrix.

 */