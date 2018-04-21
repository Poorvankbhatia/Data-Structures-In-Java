/*

Given a hashmap M which is a mapping of characters to arrays of replacement characters,
and an input string S, return an array of all possible combinations of S (where any character in S
can be substituted with one of its substitutes in M, if it exists).

What is the time complexity? What is the space complexity? Can you optimize either?

Example input:

M = { f: [F, 4], b: [B, 8] }
S = fab
Expected output:

[FaB, Fa8, 4aB, 4a8]

 */
package graphs;

import java.util.*;

/**
 * Created by poorvank.b on 23/02/18.
 */
public class GeneratePossibleCombinations {

    public List<String> generate(Map<Character,List<Character>> map,String s) {

        Queue<String> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();

        queue.add(s);

        for (int i=0;i<s.length();i++) {
            if(!map.containsKey(s.charAt(i))) {
                continue;
            }
            int size=queue.size();
            while (size>0) {
                String poll = queue.poll();
                char[] arr = poll.toCharArray();
                for (char c : map.get(poll.charAt(i))) {
                    arr[i]=c;
                    queue.add(new String(arr));
                }
                size--;
            }
        }

        // only needs the last layer of the BFS tree.
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }

        return result;

    }

    public static void main(String[] args) {
        Map<Character,List<Character>> map = new HashMap<>();
        map.put('f', new ArrayList<Character>() {{
            add('F');
            add('4');
        }});
        map.put('b', new ArrayList<Character>() {{
            add('B');
            add('8');
            add('k');
        }});

        System.out.println(new GeneratePossibleCombinations().generate(map,"fab0pb"));
    }

}

/*

start from S (e.g. fab), then go to the next layer by replacing the first letter in M (f) to all possible substitutions,
thus [Fab, 4ab]. Then go to the next layer by processing each word in the current list, replacing the next letter in M (b)
to all possible substitutions, thus [FaB, Fa8, 4aB, 4a8].

Do this iteratively, until visiting all the (key, value) pair in M.

The time complexity is linear of the number of all possible replacements.

 */