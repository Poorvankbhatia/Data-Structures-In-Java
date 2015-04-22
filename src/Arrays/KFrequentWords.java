/*

Find the k most frequent words from a file
Given a book of words. Assume you have enough main memory to accommodate all words. 
design a data structure to find top K maximum occurring words.
The data structure should be dynamic so that new words can be added.

 */

package Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank on 4/22/15.
 */
public class KFrequentWords {
    
    public static void main(String[] args) {
        
        String str = "Welcome to the world of Geeks \n" +
                "This portal has been created to provide well written well thought and well explained \n" +
                "solutions for selected questions If you like Geeks for Geeks and would like to contribute \n" +
                "here is your chance You can write article and mail your article to contribute at \n" +
                "geeksforgeeks org See your article appearing on the Geeks for Geeks main page and help \n" +
                "thousands of other Geeks";


        HashMap<String,Integer> map = new HashMap<>();
        
        String[] array = str.split("\\s+");
        
        int max = 0;
        
        for (String string : array) {
            if(map.containsKey(string)) {
                int currentVal = map.get(string)+1;
                if(max < currentVal ) {
                    max = currentVal;
                }
                map.put(string,currentVal);
            }
            else {
                map.put(string,1);
            }
        }

        ArrayList<ArrayList<String>> lists = new ArrayList<>(max+1);

        for (int i =0;i<max+1;i++) {
            lists.add(new ArrayList<String>());
        }
       
        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            lists.get(entry.getValue()).add(entry.getKey());
        }
        
        for (int i=1;i<lists.size();i++) {
            if(lists.get(i)!=null && lists.get(i).size()>0)
            System.out.println(lists.get(i).toString() + " occurs " + i + " times " );
        }
        
    }
    
}


/*

This can be done in O(n) time

Solution 1:

Steps:

Count words and hash it, which will end up in the structure like this

var hash = {
  "I" : 13,
  "like" : 3,
  "meow" : 3,
  "geek" : 3,
  "burger" : 2,
  "cat" : 1,
  "foo" : 100,
  ...
  ...
Traverse through the hash and find the most frequently used word (in this case "foo" 100), 
then create the array of that size

Then we can traverse the hash again and use the number of occurrences of words as array index, 
if there is nothing in the index, create an array else append it in the array. Then we end up with an array like:

  0   1      2            3                100
[[ ],[ ],[burger],[like, meow, geek],[]...[foo]]
Then just traverse the array from the end, and collect the k words.

 */