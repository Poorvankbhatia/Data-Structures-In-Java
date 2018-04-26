/*

Given an array A of integers, find the index of values that satisfy A + B = C + D, where A,B,C & D are integers values in the array

Note:

1) Return the indices `A1 B1 C1 D1`, so that
  A[A1] + A[B1] = A[C1] + A[D1]
  A1 < B1, C1 < D1
  A1 < C1, B1 != D1, B1 != C1

2) If there are more than one solutions,
   then return the tuple of values which are lexicographical smallest.

Assume we have two solutions
S1 : A1 B1 C1 D1 ( these are values of indices int the array )
S2 : A2 B2 C2 D2

S1 is lexicographically smaller than S2 iff
  A1 < A2 OR
  A1 = A2 AND B1 < B2 OR
  A1 = A2 AND B1 = B2 AND C1 < C2 OR
  A1 = A2 AND B1 = B2 AND C1 = C2 AND D1 < D2
Example:

Input: [3, 4, 7, 1, 2, 9, 8]
Output: [0, 2, 3, 5] (O index)

 */
package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 17/04/18.
 */
public class FourSum {

    class Pair{
        int x;
        int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public String toString(){
            String s = "[" + x + ", " + y + "]";
            return s;
        }
    }

    public ArrayList<Integer> equal(ArrayList<Integer> a) {
        ArrayList<Integer> result = new ArrayList<>();
        int len = a.size();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= len - 4; i++) {
            // A1 is i
            for (int j = i + 1; j < len; j++) {
                // B1 > A1
                int sum = a.get(i) + a.get(j);
                // two sum problem for sublist(i + 1, end) and excludes index j
                map.clear();
                int C1 = len;
                int D1 = -1;
                for (int p = i + 1; p < len; p++) {
                    // C1 > A1, C1 != B1, C1 < D1
                    if (p != j) { // C1 != B1
                        int num = a.get(p);
                        if (map.containsKey(num)) {
                            // found!! and 0-based index
                            if (map.get(num) < C1) {
                                C1 = map.get(num);
                                D1 = p;
                            }
                        } else if (!map.containsKey(sum - num)) {
                            // maintain the smaller index p
                            map.put(sum - num, p);
                        }
                    }
                }
                if (C1 != len) {
                    result.add(i);
                    result.add(j);
                    result.add(C1);
                    result.add(D1);
                    return result;
                }
            }
        }
        return result;
    }
}

