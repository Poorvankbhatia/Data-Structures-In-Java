/*

Design a data structure for the below functions.

void add(T elem);
void remove(T elem);
T elem removeRandom();

The operation time requirement : O(1)

 */

package interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by poorvank on 19/06/16.
 */
@SuppressWarnings("unchecked")
public class NewDS<T> {

    private HashMap<T,Integer> map;
    private ArrayList<T> list;


    public NewDS() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public void add(T element) {
        int size = map.size();
        list.add(element);
        map.put(element,size);
    }

    public void delete(T element) {
        int index = map.get(element);              //Get Index of element to be removed
        list.add(index,list.get(map.size()-1));    //Replace the element present at index with the last element in the list
        map.remove(element);                       //Remove the element from the hash map
        map.put(list.get(index),index);            //Update the hash map with the current index
    }

    public T deleteRandomElement() {
        Random r = new Random();
        int  randomIndex = r.nextInt(map.size()-1);
        T element = list.get(randomIndex);
        delete(element);
        return element;
    }

    public int getSize() {
        return map.size();
    }

    public static void main(String[] args) {
        NewDS<Integer> ds = new NewDS<>();
        ds.add(3);
        ds.add(33);
        ds.add(13);
        ds.add(53);
        ds.add(43);
        ds.add(27);

        ds.delete(33);
        System.out.println("Random element deleted is = " + ds.deleteRandomElement());
        System.out.println(ds.getSize());

    }
}