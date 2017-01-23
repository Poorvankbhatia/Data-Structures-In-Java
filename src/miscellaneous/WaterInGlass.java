/*

There are some glasses with equal capacity as 1 litre. The glasses are kept as follows:

    			   1
		         2   3
		      4    5    6
		    7    8    9   10
You can put water to only top glass. If you put more than 1 litre water to 1st glass, water overflows and fills equally in both
2nd and 3rd glasses. Glass 5 will get water from both 2nd glass and 3rd glass and so on.
If you have X litre of water and you put that water in top glass, how much water will be contained by jth glass in ith row?

Example. If you will put 2 litre on top.
1st – 1 litre
2nd – 1/2 litre
3rd – 1/2 litre

 */

package miscellaneous;

/**
 * Created by poorvank on 23/01/17.
 */
public class WaterInGlass {

    private float waterAmount(int i,int j,float X) {

        int index = 0;
        float[] glass = new float[(i*(i+1)/2)];
        glass[index] = X;

        for (int row = 1; row < i && X !=0.0; ++row)
        {
            // Fill glasses in a given row. Number of columns in a row
            // is equal to row number
            for (int col = 1; col <= row; ++col, ++index)
            {
                // Get the water from current glass
                X = glass[index];

                // Keep the amount less than or equal to
                // capacity in current glass
                glass[index] = (X >= 1.0f) ? 1.0f : X;


                // Get the remaining amount
                X = (X >= 1.0f) ? (X - 1) : 0.0f;

                // Distribute the remaining amount to the down two glasses
                glass[index + row] += X / 2;
                glass[index + row + 1] += X / 2;
            }
        }

        // The index of jth glass in ith row will be i*(i-1)/2 + j - 1
        return glass[i*(i-1)/2 + j - 1];

    }


    public static void main(String[] args) {

        int i = 2, j = 2;
        float X = 2.0f;

        System.out.print(new WaterInGlass().waterAmount(i,j,X));

    }

}

/*

The approach is similar to Method 2 of the Pascal’s Triangle. If we take a closer look at the problem, the problem boils down to Pascal’s Triangle.

                           1   ---------------- 1
		                 2   3 ---------------- 2
                      4    5    6  ------------ 3
		            7    8    9   10  --------- 4
Each glass contributes to the two glasses down the glass. Initially, we put all water in first glass. Then we keep 1 litre (or less than 1 litre)
 in it, and move rest of the water to two glasses down to it. We follow the same process for the two glasses and all other glasses till ith row.
  There will be i*(i+1)/2 glasses till ith row.


Time Complexity: O(i*(i+1)/2) or O(i^2)
Auxiliary Space: O(i*(i+1)/2) or O(i^2)


 */