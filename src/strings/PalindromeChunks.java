/*

Return the length of longest possible chunked palindrome string.

Examples :

Input : VOLVO
Output : 3
Explanation :
(VO)L(VO)


Input : merchant
Output : 1

Explanation : No chunks possible.

Input :

ghiabcdefhelloadamhelloabcdefghi

Output : 7

Explanation :

(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)



 */
package strings;

/**
 * Created by poorvank on 12/12/16.
 */
public class PalindromeChunks {

    static int longestChunkedPalindrome(String s) {
        int chunkCount = 0;
        String left = "", right = "";
        int i = 0, j = s.length() - 1;
        while (i < j) {
            left = left + s.substring(i, i+1);
            right = right + s.substring(j, j+1);
            if (left.equals(new StringBuilder(right).reverse().toString())) {
                System.out.println(left +" " + right);
                chunkCount += 2;
                left = "";
                right = "";
            }
            ++i;
            --j;
        }
        if ( (!left.equals("") && !right.equals("")) || i == j) // middle chunk left over
            ++chunkCount;
        return chunkCount;
    }
    public static void main(String[] args ) {
        System.out.println("VOLVO: "+longestChunkedPalindrome("VOLPVO")); // 3
        System.out.println("merchant: "+longestChunkedPalindrome("merchant")); // 1
        System.out.println("antaprezatepzapreanta: "+longestChunkedPalindrome("antaprezatepzapreanta")); // 7
    }

}
