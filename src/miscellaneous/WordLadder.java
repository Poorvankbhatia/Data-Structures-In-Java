/*

Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end,
 such that only one letter can be changed at a time and each intermediate word must exist in the dictionary. 
 For example, given:

start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", the program should return its length 5.

 */

package miscellaneous;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by poorvank on 8/3/15.
 */

class WordNode {

    String word;
    int numSteps;

    public WordNode(String word, int numSteps) {
        this.word = word;
        this.numSteps = numSteps;
    }
}


public class WordLadder {

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";

        Set<String> dictionary = new HashSet<String>() {{
            add("hot");
            add("dot");
            add("lot");
            add("log");
        }};

        System.out.println(countSteps(beginWord, endWord, dictionary));

    }

    private static int countSteps(String beginWord, String endWord, Set<String> dictionary) {

        Queue<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));

        dictionary.add(endWord);

        while (!queue.isEmpty()) {

            //queue contains the current word
            WordNode topWord = queue.remove();

            if (topWord.word.equals(endWord)) {
                return topWord.numSteps;
            }

            char[] arr = topWord.word.toCharArray();
            for (int i = 0; i < arr.length; i++) {

                for (char c = 'a'; c <= 'z'; c++) {

                    char temp = arr[i];

                    if (arr[i] != c) {
                        arr[i] = c;
                    }

                    String newWord = new String(arr);
                    if (dictionary.contains(newWord)) {
                        
                        //Remove word from dictionary once found and add it to queue
                        queue.add(new WordNode(newWord, topWord.numSteps + 1));
                        System.out.println(newWord + " " + (topWord.numSteps+1));
                        dictionary.remove(newWord);

                    }

                    //for further findings
                    arr[i] = temp;

                }


            }


        }

        return 0;


    }

}
/*

breath-first search guarantees the optimal solution.
 */
