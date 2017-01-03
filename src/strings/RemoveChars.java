/*

Given a string, eliminate all “b” and “ac” in the string, you have to replace them in-place,
and you are only allowed to iterate over the string once.

Examples:

acbac   ==>  ""
aaac    ==>  aa
ababac  ==>   aa
bbbbd   ==>   d


 */
package strings;

/**
 * Created by poorvank on 04/01/17.
 */
public class RemoveChars {

    public String remove(String s) {

        int n = s.length();
        char[] arr = s.toCharArray();

        int i=-1,j=0;

        while (j<n) {

            if(j<n-1 && arr[j]=='a' && arr[j+1]=='c') {
                j +=2;
            } else if(arr[j]=='b') {
                j +=1;
                /**
                 *  if input string is “aacacc”, the above code produces output as “ac” which looks correct as it
                 *  removes consecutive occurrences of ‘a’ and ‘c’. What if the requirement is to not have an “ac”
                 *  in output string at all. Can we modify the above program to produce output as empty string for input
                 *  “aacacc” and produce output as “d” when input is “abcaaccd”? It turns out that it can also be done with given restrictions.
                 */
            } else if(i>=0 && arr[i]=='a' && arr[j]=='c') {
                i--;
                j++;
            } else {
                arr[++i] = arr[j++];
            }


        }

        return i==-1?"":new String(arr).substring(0,i+1);

    }

    public static void main(String[] args) {
        System.out.println(new RemoveChars().remove("aaac"));
        System.out.println(new RemoveChars().remove("ababaac"));
    }

}
