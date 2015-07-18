/*

Boggle (Find all possible words in a board of characters)
Given a dictionary, a method to do lookup in dictionary and a M x N board where every cell 
has one character. Find all possible words that can be formed by a sequence of adjacent characters.Note that
we can move to any of 8 adjacent characters, but a word should not have multiple instances of same cell.

Example:

Input: dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
       boggle[][]   = {{'G','I','Z'},
                       {'U','E','K'},
                       {'Q','S','E'}};
      isWord(str): returns true if str is present in dictionary
                   else false.

Output:  Following words of dictionary are present
         GEEKS
         QUIZ

 */
package graphProgram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 4/4/15.
 */
public class PossibleWords {

    private static List<String> dictionary = new ArrayList<String>() {{
        add("GEEKS");
        add("FOR");
        add("QUIZ");
        add("GO");
    }};

    public static void main(String[] args) {

        char[][] boggle = new char[][]{
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}
        };

        findWords(boggle);
    }

    private static void findWords(char[][] boggle) {

        boolean[][] visited = new boolean[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                findWordsUtil(boggle, visited, i, j, new StringBuilder());
            }
        }

    }

    private static void findWordsUtil(char[][] boggle, boolean[][] visited, int i, int j, StringBuilder sb) {

        //System.out.println(i + " " + j);
        visited[i][j] = true;
        sb.append(boggle[i][j]);

        if (dictionary.contains(sb.toString())) {
            System.out.println(sb);
        }

        for (int row = i - 1; row <= i + 1 && row < 3; row++) {
            for (int col = j - 1; col <= j + 1 && col < 3; col++) {
                if (row >= 0 && col >= 0 && !visited[row][col]) {
                    findWordsUtil(boggle, visited, row, col, sb);
                }
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        visited[i][j] = false;

    }

}



/*

The idea is to consider every character as a starting character and find all words starting with it. 
All words starting from a character can be found using Depth First Traversal. 
We do depth first traversal starting from every cell. 
We keep track of visited cells to make sure that a cell is considered only once in a word.

the above solution may print same word multiple times.
 For example, if we add “SEEK” to dictionary, it is printed multiple times.
  To avoid this, we can use hashing to keep track of all printed words.

 */