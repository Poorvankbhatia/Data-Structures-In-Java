/*

Find Itinerary from a given list of tickets
Given a list of tickets, find itinerary in order using the given list.

Example:

Input:
"Chennai" -> "Banglore"
"Bombay" -> "Delhi"
"Goa"    -> "Chennai"
"Delhi"  -> "Goa"

Output: 
Bombay->Delhi, Delhi->Goa, Goa->Chennai, Chennai->Banglore, 


Topological sorting can also be done

 */

package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 6/22/15.
 */
public class Itinerary {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("chennai", "banagalore");
        map.put("bombay", "delhi");
        map.put("goa", "chennai");
        map.put("delhi", "goa");

        printRoute(map);

    }

    private static void printRoute(Map<String, String> map) {

        Map<String, String> reverseMap = new HashMap<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }

        String start = "";

        for (Map.Entry<String, String> entry : map.entrySet()) {

            if (!reverseMap.containsKey(entry.getKey())) {
                start = entry.getKey();
                break;
            }

        }

        String end = map.get(start);

        while (end != null) {

            System.out.println(start + " -> " + end);
            start = end;
            end = map.get(end);

        }

    }

}


/*

The idea is to first find the starting point. A starting point would never be on ‘to’ side of a ticket. 
Once we find the starting point, we can simply traverse the given map to print itinerary in order. Following are steps.

1) Create a HashMap of given pair of tickets.  Let the created 
   HashMap be 'dataset'. Every entry of 'dataset' is of the form 
   "from->to" like "Chennai" -> "Banglore"

2) Find the starting point of itinerary.
     a) Create a reverse HashMap.  Let the reverse be 'reverseMap'
        Entries of 'reverseMap' are of the form "to->form". 
        Following is 'reverseMap' for above example.
        "Banglore"-> "Chennai" 
        "Delhi"   -> "Bombay" 
        "Chennai" -> "Goa"
        "Goa"     ->  "Delhi"
 
     b) Traverse 'dataset'.  For every key of dataset, check if it
        is there in 'reverseMap'.  If a key is not present, then we 
        found the starting point. In the above example, "Bombay" is
        starting point.

3) Start from above found starting point and traverse the 'dataset' 
   to print itinerary.

 */