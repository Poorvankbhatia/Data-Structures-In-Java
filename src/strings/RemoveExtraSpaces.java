/*

Remove extra spaces from a string
Given a string containing many consecutive spaces, trim all spaces so that all words should contain only a single space between them.
The conversion should be done in-place and solution should handle trailing and leading spaces and also remove preceding spaces before
common punctuation like full stop, comma and a question mark.

Examples:

Input:
str = "   Hello Geeks . Welcome   to  GeeksforGeeks   .    ";
Output:
"Hello Geeks. Welcome to GeeksforGeeks."

Input:
str = "GeeksforGeeks";
Output:
"GeeksforGeeks"
(No change is needed)


 */

package strings;

/**
 * Created by poorvank on 25/07/16.
 */
public class RemoveExtraSpaces {


    public static void remove(String text) {

        char[] array = text.toCharArray();

        int n = text.length();
        boolean spaceFound = false;

        int i=0,j=-1;

        //Handle leading spaces
        while (j++<n && array[j]==' ');


        while (j<n) {

            if(array[j]!=' ') {

                if((array[j]=='?' || array[j]=='.' || array[j]=='?') && i-1>=0 && array[i-1]==' ') {
                    array[i-1] = array[j++];
                } else {
                    array[i++] = array[j++];
                }

                spaceFound = false;

            } else  if(array[j++]==' '){

                if (!spaceFound)
                {
                    array[i++] = ' ';
                    spaceFound = true;
                }

            }

        }

        text = new String(array);
        text = text.substring(0,i);

        System.out.println(text);



    }

    public static void main(String[] args) {

        String str = "   Hello Geeks . Welcome   to  GeeksforGeeks   .    ";
        System.out.println(str);
        remove(str);

    }

}

/*

This problem is an extension of Remove spaces from a given string

We strongly recommend you to minimize your browser and try this yourself first.

The idea is to maintain 2 pointers. Initially both point to the beginning of the array.

The first pointer keeps track of next position to be filled in output string.

The second pointer is advanced to read all characters of the string one by one.

On finding any non-space character, the character is copied to the location of the first pointer and then both the first and
second pointers are advanced.

If non-space character is full stop, comma or a question mark, we also remove any preceding space before it.

On finding consecutive space characters, one only space is copied to the location of the first pointer and rest are ignored.
The leading and trailing spaces are handled separately in the solution.

 */
