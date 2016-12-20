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

        int firstPointer=0,secondPointer=0;

        //Handle leading spaces
        while (secondPointer<n && array[secondPointer]==' ') {
            secondPointer++;
        }


        while (secondPointer<n) {

            if(array[secondPointer]!=' ') {

                if((array[secondPointer]=='?' || array[secondPointer]=='.' || array[secondPointer]=='?') && firstPointer-1>=0 && array[firstPointer-1]==' ') {
                    array[firstPointer-1] = array[secondPointer++];
                } else {
                    array[firstPointer++] = array[secondPointer++];
                }

                spaceFound = false;

            } else  if(array[secondPointer++]==' '){

                if (!spaceFound)
                {
                    array[firstPointer++] = ' ';
                    spaceFound = true;
                }

            }

        }

        text = new String(array);
        text = text.substring(0,firstPointer);

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

The idea is to maintain 2 pointers. Initially both point to the beginning of the array.

The first pointer keeps track of next position to be filled in output string.

The second pointer is advanced to read all characters of the string one by one.

On finding any non-space character, the character is copied to the location of the first pointer and then both the first and
second pointers are advanced.

If non-space character is full stop, comma or a question mark, we also remove any preceding space before it.

On finding consecutive space characters, one only space is copied to the location of the first pointer and rest are ignored.
The leading and trailing spaces are handled separately in the solution.

 */
