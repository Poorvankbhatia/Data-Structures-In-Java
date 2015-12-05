/*

Design a data structure that supports insert, delete, search and getRandom in constant time
Design a data structure that supports following operations in Θ(1) time.

insert(x): Inserts an item x to the data structure if not already present.

remove(x): Removes an item x from the data structure if present.

search(x): Searches an item x in the data structure.

getRandom(): Returns a random element from current set of elements


 */

package miscellaneous;

import java.util.*;

/**
 * Created by poorvank on 4/14/15.
 */

class EfficientDS {

    Map<Integer, Integer> map = new HashMap<>();
    ArrayList<Integer> arrayList = new ArrayList<>();


    //Addition
    public void add(int x) {

        if (map.get(x) != null) {
            System.out.println("Already present at position - " + map.get(x));
            return;
        }

        int size = arrayList.size();
        arrayList.add(x);

        map.put(x, size);

    }

    //Removal
    public void remove(int x) {

        if (map.get(x) == null) {
            System.out.println("Not present");
        } else {
            int pos = map.get(x);
            map.remove(x);

            int lastElement = arrayList.get(arrayList.size() - 1);
            Collections.swap(arrayList, pos, arrayList.size() - 1);

            arrayList.remove(arrayList.size() - 1);

            map.put(lastElement, pos);
        }

    }

    public void search(int x) {

        if (map.get(x) != null) {
            System.out.println("present at position - " + map.get(x));
        } else {
            System.out.println("Not present");
        }
    }

    public void getRandom() {

        if (arrayList.size() == 0) {
            System.out.println("No element present");
            return;
        }

        Random random = new Random();

        int index = random.nextInt(arrayList.size());

        System.out.println("A random number is - " + arrayList.get(index));

    }
}

public class DataStructureDesign {

    public static void main(String[] args) {

        EfficientDS ds = new EfficientDS();
        ds.add(10);
        ds.add(20);
        ds.add(30);
        ds.add(40);
        ds.search(30);
        ds.remove(20);
        ds.add(50);
        ds.search(50);
        ds.getRandom();

    }

}


/*

Look at amortized analysis notes

We can use hashing to support first 3 operations in Θ(1) time. How to do the 4th operation? 
The idea is to use a resizable array (ArrayList in Java, vector in C) together with hashing.
 Resizable arrays support insert in Θ(1) amortized time complexity. To implement getRandom(), 
 we can simply pick a random number from 0 to size-1 (size is number of current elements) and return the 
 element at that index. The hash map stores array values as keys and array indexes as values.

Following are detailed operations.

insert(x)
1) Check if x is already present by doing a hash map lookup.
2) If not present, then insert it at the end of the array.
3) Add in hash table also, x is added as key and last array index as index.

remove(x)
1) Check if x is present by doing a hash map lookup.
2) If present, then find its index and remove it from hash map.
3) Swap the last element with this element in array and remove the last element.
Swapping is done because the last element can be removed in O(1) time.
4) Update index of last element in hash map.

getRandom()
1) Generate a random number from 0 to last index.
2) Return the array element at the randomly generated index.

search(x)
Do a lookup for x in hash map.

 */