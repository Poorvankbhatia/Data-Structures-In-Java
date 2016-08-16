/*

GOOGLE INTERVIEW QUESTION

Given a puzzle of letters/ characters e.g.

a e r o p s
b h a r l s
w r i s l o
a s n k t q

Write a function to which this puzzle and a word will be passed to test whether that word exists in the puzzle or not.
e.g. rain and slow will return true. rain is present in the third column and slow in the third row wrapped around.



 */

package graphs.search;

import java.util.ArrayList;

/**
 * Created by poorvank on 15/08/16.
 */
public class StringPuzzle {

    private static int row, col;

    //Traversing in all 4 directions
    private static int x[] = {0, 1, -1,  0};
    private static int y[] = {1, 0,  0, -1};


    public static boolean find(String toFind, Character[][] puzzle) {

        row = puzzle.length;
        col = puzzle[0].length;

        boolean[][] visited = new boolean[row][col];
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if ((puzzle[i][j] == toFind.charAt(0)) && !visited[i][j]) {

                    if (findUtil(toFind, puzzle, i, j, visited, new StringBuilder(), 0,new ArrayList<>())) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }

        return false;

    }


    private static boolean findUtil(String toFind, Character[][] puzzle, int i, int j, boolean[][] visited, StringBuilder sb, int index, ArrayList<String> list) {

        sb.append(puzzle[i][j]);
        //List used to print path when a string is found
        list.add("(" + i+ "," + j +")");
        if (sb.toString().equals(toFind)) {

            System.out.println("Path is = " + list.toString());
            return true;
        }
        if(index==toFind.length()) {
            return false;
        }

        visited[i][j] = true;
        int nextIndex = index + 1;
        int nextR, nextC;

        for (int k = 0; k < 4; k++) {

            nextR = i + x[k];
            nextC = j + y[k];

            if (isValid(nextR, nextC, toFind, nextIndex, visited, puzzle)) {
                return findUtil(toFind, puzzle, nextR, nextC, visited, sb, nextIndex,new ArrayList<>(list));

            }

            //Checking Wrap Around cases
            //Corner Cases
            if ((i == 0 && j == 0) || (i == row - 1 && j == col - 1)) {
                nextR = row - 1;
                nextC = 0;

                if (isValid(nextR, nextC, toFind, nextIndex, visited, puzzle)) {
                    return findUtil(toFind, puzzle, nextR, nextC, visited, sb, nextIndex,new ArrayList<>(list));

                }

                nextR = 0;
                nextC = col - 1;

                if (isValid(nextR, nextC, toFind, nextIndex, visited, puzzle)) {
                    return findUtil(toFind, puzzle, nextR, nextC, visited, sb, nextIndex,new ArrayList<>(list));

                }


            } else if ((i == 0 && j == col - 1) || (i == row - 1 && j == 0)) {
                nextR = row - 1;
                nextC = col - 1;
                if (isValid(nextR, nextC, toFind, nextIndex, visited, puzzle)) {
                    return findUtil(toFind, puzzle, nextR, nextC, visited, sb, nextIndex,new ArrayList<>(list));

                }

                nextR = 0;
                nextC = 0;

                if (isValid(nextR, nextC, toFind, nextIndex, visited, puzzle)) {
                    return findUtil(toFind, puzzle, nextR, nextC, visited, sb, nextIndex,new ArrayList<>(list));

                }


            } //Side cases
            else if (i == 0) {
                nextR = row - 1;
                nextC = j;

                if (isValid(nextR, nextC, toFind, nextIndex, visited, puzzle)) {
                    return findUtil(toFind, puzzle, nextR, nextC, visited, sb, nextIndex,new ArrayList<>(list));

                }

            } else if (i == row - 1) {
                nextR = 0;
                nextC = j;

                if (isValid(nextR, nextC, toFind, nextIndex, visited, puzzle)) {
                    return findUtil(toFind, puzzle, nextR, nextC, visited, sb, nextIndex,new ArrayList<>(list));

                }

            } else if (j == 0) {
                nextR = i;
                nextC = col - 1;

                if (isValid(nextR, nextC, toFind, nextIndex, visited, puzzle)) {
                    return findUtil(toFind, puzzle, nextR, nextC, visited, sb, nextIndex,new ArrayList<>(list));

                }


            } else if (j == col - 1) {
                nextR = i;
                nextC = 0;

                if (isValid(nextR, nextC, toFind, nextIndex, visited, puzzle)) {
                    return findUtil(toFind, puzzle, nextR, nextC, visited, sb, nextIndex,new ArrayList<>(list));

                }


            }


        }

        //mark last visited node(while backtracking) as false again so that it can be used again
        visited[i][j] = false;
        sb.deleteCharAt(sb.length()-1);


        return false;


    }


    private static boolean isValid(int i, int j, String toFind, int index, boolean[][] visited, Character[][] puzzle) {
        return (i >= 0 && j >= 0 && i < row && j < col && !visited[i][j] && puzzle[i][j] == toFind.charAt(index));
    }


    public static void main(String[] args) {

        Character[][] puzzle = new Character[][]{{'a', 'e', 'r', 'o', 'p', 's'},
                                                 {'b', 'h', 'a', 'l', 'l', 's'},
                                                 {'w', 'r', 'i', 's', 'l', 'o'},
                                                 {'a', 's', 'n', 'k', 't', 'q'}};

        String toFind = "slow";

        System.out.println(StringPuzzle.find(toFind,puzzle));

    }

}
