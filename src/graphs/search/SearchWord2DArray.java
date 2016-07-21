/*

Search a Word in a 2D Grid of characters
Given a 2D grid of characters and a word, find all occurrences of given word in grid. A word can be matched in all 8 directions at any point.
**Word is said be found in a direction if all characters match in this direction (not in zig-zag form).**

The 8 directions are, Horizontally Left, Horizontally Right, Vertically Up and 4 Diagonal directions.

Example:

Input:  grid[][] = {"GEEKSFORGEEKS",
                    "GEEKSQUIZGEEK",
                    "IDEQAPRACTICE"};
        word = "GEEKS"

Output: pattern found at 0, 0
        pattern found at 0, 8
        pattern found at 1, 0

Input:  grid[][] = {"GEEKSFORGEEKS",
                    "GEEKSQUIZGEEK",
                    "IDEQAPRACTICE"};
        word = "EEE"

Output: pattern found at 0, 2
        pattern found at 0, 10
        pattern found at 2, 2
        pattern found at 2, 12


 */

package graphs.search;

/**
 * Created by poorvank.b on 20/07/16.
 */
public class SearchWord2DArray {

    private static int x[] = {0,1, 1,1, 0,-1,-1,-1};
    private static int y[] = {1,1,-1,0,-1, 0, 1,-1};


    private static int wordExist(String word,Character[][] a,int i,int j) {

        if(a[i][j]!=word.charAt(0)) {
            return 0;
        }

        int count = 0;

        //Going in all 8 directions
        for (int k=0;k<8;k++) {

            int nextX = i + x[k];
            int nextY = j + y[k];

            int len;
            for (len=1;len<word.length();len++) {

                if(!isValid(nextX,nextY,a,len,word)) {
                    break;
                }

                nextX = nextX+x[k];
                nextY = nextY+y[k];
            }

            if(len==word.length()) {
                count++;
            }

        }

        return count;

    }

    private static boolean isValid(int r,int c,Character[][] a,int k,String word) {
        return (r>=0 && c>=0 && r<a.length && c<a[0].length && word.charAt(k)==a[r][c]);
    }


    public static void search(Character[][] a,String word) {

        int rows = a.length;
        int cols = a[0].length;

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                int c = wordExist(word,a,i,j);
                if(c!=0) {
                    System.out.println("Pattern Starts at : " + i + "," + j + " @ " + c + " times");
                }
            }
        }

    }

    public static void main(String[] args) {

        Character[][] a = new Character[][]{
                {'B', 'B', 'A', 'B', 'B', 'N'},
                {'B', 'B', 'M', 'B', 'B', 'O'},
                {'B', 'B', 'A', 'B', 'B', 'Z'},
                {'B', 'O', 'Z', 'O', 'N', 'A'},
                {'B', 'B', 'O', 'Z', 'B', 'M'},
                {'N', 'O', 'Z', 'A', 'M', 'A'}
        };

        String word = "AMAZON";


        search(a,word);

        System.out.println("-----------------------------");

        a = new Character[][]{
                {'G','E','E','K','S','F','O','R','G','E','E','K','S'},
                {'G','E','E','K','S','Q','U','I','Z','G','E','E','K'},
                {'I','D','E','Q','A','P','R','A','C','T','I','C','E'}
        };

        word = "GEEKS";

        search(a,word);
    }

}



