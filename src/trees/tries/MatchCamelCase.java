/*

Print all words matching a pattern in CamelCase Notation Dictonary
Given a dictionary of words where each word follows CamelCase notation, print all words in the dictionary that match with a given pattern
consisting of uppercase characters only.

CamelCase is the practice of writing compound words or phrases such that each word or abbreviation begins with a capital letter.
Common examples include: “PowerPoint” and “WikiPedia”, “GeeksForGeeks”, “CodeBlocks”, etc.

Examples:

Input:
dict[] = ["Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek",
"HiTechWorld", "HiTechCity", "HiTechLab"]

For pattern "HT",
Output: ["HiTech", "HiTechWorld", "HiTechCity", "HiTechLab"]

For pattern "H",
Output: ["Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek",
	"HiTechWorld", "HiTechCity", "HiTechLab"]

For pattern "HTC",
Output: ["HiTechCity"]


Input:
dict[] = ["WelcomeGeek","WelcomeToGeeksForGeeks", "GeeksForGeeks"]

For pattern "WTG",
Output: ["WelcomeToGeeksForGeeks"]

For pattern "GFG",
Output: [GeeksForGeeks]

For pattern "GG",
Output: []

 */
package trees.tries;

import utility.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 14/08/16.
 */
public class MatchCamelCase {

    private Trie<List<String>> trie = new Trie<>();

    public MatchCamelCase(String[] dictionary) {

        for (String dictionaryWord : dictionary) {
            StringBuilder trieKey = new StringBuilder();
            for (Character c : dictionaryWord.toCharArray()) {
                if(Character.isUpperCase(c)) {
                    //Append Uppercase character & insert them as a string key in trie
                    trieKey.append(c);
                    if(trie.contains(trieKey.toString())) {
                        trie.get(trieKey.toString()).add(dictionaryWord);
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(dictionaryWord);
                        trie.put(trieKey.toString(),list);
                    }
                }
            }
        }
    }

    public List<String> getAllValues(String pattern) {
        return trie.get(pattern);
    }

    public static void main(String[] args) {

        String[] dictionary = new String[]{"Hi", "Hello", "HelloWorld",  "HiTech", "HiGeek",
                "HiTechWorld", "HiTechCity", "HiTechLab"};

        MatchCamelCase camelCase = new MatchCamelCase(dictionary);

        String pattern = "HT";

        List<String> result = camelCase.getAllValues(pattern);
        if(result!=null) {
            System.out.println(result);
        }


    }

}

/*

The idea is to insert all dictionary keys into the Trie one by one. Here key refers to only Uppercase characters in original word in
CamelCase notation. If we encounter the key for the first time, we need to mark the last node as leaf node and insert the complete word
for that key into the vector associated with the leaf node. If we encounter a key that is already in the trie, we update the vector associated
with the leaf node with current word. After all dictionary words are processed, we search for the pattern in the trie and print all words that
matches the pattern.

 */