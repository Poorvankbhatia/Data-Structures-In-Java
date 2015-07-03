package arrayPrograms;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by poorvank on 5/8/15.
 */
public class array {

    public static void main(String[] args) {

        int[] result = twoSum(new int[]{5, 3, 8, 3}, 6);

        System.out.println(Arrays.toString(result));

    }

    private static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            } else {
                map.put(nums[i], i);
            }
        }

        return result;

    }

}
