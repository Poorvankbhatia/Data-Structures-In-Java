package utility;

import java.util.Comparator;

/**
 * Created by poorvank on 16/07/16.
 */
@SuppressWarnings("unchecked")
public class SortFunctions {

    /*
    Java’s convention
    is that the call v.compareTo(w) returns an integer that is negative, zero, or positive
    (usually -1, 0, or +1) when v < w, v = w,
    or v > w, respectively.
     */

    public static boolean less(Comparable a,Comparable b) {
        return a.compareTo(b) < 0;
    }

    public static boolean less(Object a, Object b, Comparator comparator) {
        return comparator.compare(a,b) < 0;
    }

    public static void exchange(Comparable[] array,int i,int j) {
        Comparable t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void assign(Comparable[] array,int i,Comparable key) {
        array[i] = key;
    }

    public static void print(Comparable[] array) {
        for (Comparable anArray : array) {
            System.out.print(anArray.toString() + " ");
        }
    }


}
