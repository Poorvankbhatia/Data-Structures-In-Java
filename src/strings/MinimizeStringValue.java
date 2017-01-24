/*

Given a string of lowercase alphabets and a number k, the task is to print the minimum value of the
 string after removal of ‘k’ characters.  The value of a string is defined as sum of squares of count of
 each distinct character. For example consider the string “saideep”, here frequencies of characters are s-1, a-1, i-1,e-2, d-1, p-1
 and value of the string is 1^2 + 1^2 + 1^2 + 1^2 + 1^1 + 2^2 = 9

 Input:

 abccc
1
aaab
2
Output
6
2

 */
package strings;

import utility.priorityQueueClasses.MaxPriorityQueue;

/**
 * Created by poorvank on 24/01/17.
 */
public class MinimizeStringValue {

    private class FrequencyObject implements Comparable<FrequencyObject> {

        int frequency;
        char c;

        public FrequencyObject(int frequency, char c) {
            this.frequency = frequency;
            this.c = c;
        }

        public int compareTo(FrequencyObject frequencyObject) {
            if(this.frequency>frequencyObject.frequency) {
                return 1;
            } else if(this.frequency<frequencyObject.frequency) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public int minValue(String s,int k) {

        int[] count = new int[26];
        /*

        Use Max priority queue to get the character with highest frequency.

         */
        MaxPriorityQueue<FrequencyObject> maxPriorityQueue = new MaxPriorityQueue<>();

        for (Character c : s.toCharArray()) {
            count[c-'a']++;
        }

        for (int i=0;i<26;i++) {
            if(count[i]>0) {
                FrequencyObject f = new FrequencyObject(count[i],(char) ('a'+i));
                maxPriorityQueue.insert(f);
            }
        }

        FrequencyObject current;
        for (int i=0;i<k;i++) {
            if(!maxPriorityQueue.isEmpty()) {
                current = maxPriorityQueue.delMax();
                current.frequency--;
                count[current.c-'a']--;
                if(i==k-1) {
                    break;
                }
                if(current.frequency>1) {
                    maxPriorityQueue.insert(current);
                }
            }
        }

        int result = 0;
        for (int aCount : count) {
            result += aCount * aCount;
        }

        return result;

    }

    public static void main(String[] args) {
        System.out.print(new MinimizeStringValue().minValue("abccc",1));
    }

}
