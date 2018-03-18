/*

Given two input arrays, return true if the words array is sorted according to the ordering array
Input:
words = ['cc', 'cb', 'bb', 'ac']
ordering = ['c', 'b', 'a']
Output: True

Input:
words = ['cc', 'cb', 'bb', 'ac']
ordering = ['b', 'c', 'a']
Output: False

 */
package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 18/03/18.
 */
public class SortedAccordingToArray {

    public boolean isSorted(String[] inputs,Character[] orderArray) {

        if(inputs==null || inputs.length==0) {
            return true;
        }

        Map<Character,Integer> map = new HashMap<>();

        for (int i=0;i<orderArray.length;i++) {
            map.put(orderArray[i],i);
        }

        for (int i=1;i<inputs.length;i++) {
            String first =inputs[i-1];
            String second = inputs[i];
            int mismatch =findMismatch(first,second);
            if(mismatch!=-1) {
                char c1 = first.charAt(mismatch);
                char c2 = second.charAt(mismatch);
                if(map.get(c1)>map.get(c2)) {
                    return false;
                }
            }
        }
        return true;

    }

    private int findMismatch(String s1,String s2) {
        for (int i=0;i<Math.min(s1.length(),s2.length());i++) {
            if(s1.charAt(i)!=s2.charAt(i)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] inputs = new String[]{ "cc", "cb", "bb", "ac"};
        Character[] arr = new Character[]{'c','b','a'};
        System.out.println(new SortedAccordingToArray().isSorted(inputs,arr));
    }

}
