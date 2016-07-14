//Implementation of all suffixes of an

package utility;

/**
 * Created by poorvank on 14/07/16.
 */
public class Suffix implements Comparable<Suffix> {

    private final String text;                  //The entire text
    private final int index;                    //Index in the unsorted array

    public Suffix(String text, int index) {
        this.text = text;
        this.index = index;
    }

    //Length uniquely determines the starting point
    public int getLength() {
        return text.length()-index;
    }

    public int charAt(int i) {
        return text.charAt(index+i);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Suffix that) {
        if(this==that) {
            return 0;
        }
        int n = Math.min(that.getLength(),this.getLength());
        for (int i=0;i<n;i++) {
            if(this.charAt(i)>that.charAt(i)) {
                return 1;
            }
            if(this.charAt(i)<that.charAt(i)) {
                return -1;
            }
        }
        return this.getLength()-that.getLength();
    }

    public String toString() {
        return text.substring(index);
    }


}
