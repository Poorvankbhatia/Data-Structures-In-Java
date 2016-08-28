/*
Suppose you have a 2 dimensional Array and you have a String say "Amazon" inside the Array such that the individual characters
can be present from Left to Right, Right to Left, Top to down and down to up.

I will explain with example :

char[][] a = {
            {B,B,A,B,B,N},
            {B,B,M,B,B,O},
            {B,B,A,B,B,Z},
            {N,O,Z,B,B,A},
            {B,B,B,B,B,M},
            {B,B,B,B,B,A}
    };
The above Array has two Amazon Strings. You need to return the count of number of such strings present.

 */

package graphs.search;

import java.util.ArrayList;

/**
 * Created by poorvank on 19/07/16.
 */
public class FindStrings {

    private static int count = 0;       // Final Count

    public static void find(Character[][] a, String toFind) {

        int rows = a.length;
        int col = a[0].length;

        boolean[][] visited = new boolean[a.length][a[0].length];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                if (a[i][j] == toFind.charAt(0)) {
                    findUtil(visited, a, i, j, 0, toFind, new StringBuilder(), rows - 1, col - 1, new ArrayList<>());
                    visited[i][j] = false;
                }
            }
        }

    }

    private static void findUtil(boolean[][] visited, Character[][] a, int i, int j, int index, String toFind, StringBuilder result, int R, int C, ArrayList<String> list) {

        result.append(a[i][j]);
        list.add(i + "-" + j);
        if (index == toFind.length() - 1 && result.toString().equals(toFind)) {
            //System.out.println(i + " " + j); // End point of String toFind
            System.out.println(list.toString());
            count++;
            return;
        }
        visited[i][j] = true; // Just to mark the character so that one character is not visited twice for a string match
        int nextIndex = index + 1; //Next index of the String to be compared

        int nextR, nextC;

        //Down
        if (i + 1 >= 0 && j >= 0 && i + 1 <= R && j <= C && !visited[i + 1][j] && a[i + 1][j] == toFind.charAt(nextIndex)) {
            nextR = i + 1;
            nextC = j;
            findUtil(visited, a, nextR, nextC, nextIndex, toFind, new StringBuilder(result), R, C, new ArrayList<>(list));
            //Every time we are done with the next character in the 2D Array we mark its visited value false
            visited[nextR][nextC] = false;
        }

        //Right
        if (i >= 0 && j + 1 >= 0 && i <= R && j + 1 <= C && !visited[i][j + 1] && a[i][j + 1] == toFind.charAt(nextIndex)) {
            nextR = i;
            nextC = j + 1;
            findUtil(visited, a, nextR, nextC, nextIndex, toFind, new StringBuilder(result), R, C, new ArrayList<>(list));
            visited[nextR][nextC] = false;
        }

        //Left
        if (i >= 0 && j - 1 >= 0 && i <= R && j - 1 <= C && !visited[i][j - 1] && a[i][j - 1] == toFind.charAt(nextIndex)) {
            nextR = i;
            nextC = j - 1;
            findUtil(visited, a, nextR, nextC, nextIndex, toFind, new StringBuilder(result), R, C, new ArrayList<>(list));
            visited[nextR][nextC] = false;
        }

        //Up
        if (i - 1 >= 0 && j >= 0 && i - 1 <= R && j <= C && !visited[i - 1][j] && a[i - 1][j] == toFind.charAt(nextIndex)) {
            nextR = i - 1;
            nextC = j;
            findUtil(visited, a, nextR, nextC, nextIndex, toFind, new StringBuilder(result), R, C, new ArrayList<>(list));
            visited[nextR][nextC] = false;
        }


    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {

        Character[][] a = new Character[][]{
                {'B', 'B', 'A', 'B', 'B', 'N'},
                {'B', 'B', 'M', 'B', 'B', 'O'},
                {'B', 'B', 'A', 'B', 'B', 'Z'},
                {'B', 'O', 'Z', 'O', 'N', 'A'},
                {'B', 'B', 'O', 'Z', 'B', 'M'},
                {'B', 'B', 'N', 'A', 'M', 'A'}
        };

        String toFind = "AMAZON";

        find(a, toFind);
        System.out.println(getCount());

    }

}
