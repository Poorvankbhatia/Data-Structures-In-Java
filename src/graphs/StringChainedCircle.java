/*

Given an array of strings, find if the given strings can be chained to form a circle.
A string X can be put before another string Y in circle if the last character of X is same as first character of Y.

Examples:

Input: arr[] = {"geek", "king"}
Output: Yes, the given strings can be chained.
Note that the last character of first string is same
as first character of second string and vice versa is
also true.

Input: arr[] = {"for", "geek", "rig", "kaf"}
Output: Yes, the given strings can be chained.
The strings can be chained as "for", "rig", "geek"
and "kaf"

Input: arr[] = {"aab", "bac", "aaa", "cda"}
Output: Yes, the given strings can be chained.
The strings can be chained as "aaa", "aab", "bac"
and "cda"

Input: arr[] = {"aaa", "bbb", "baa", "aab"};
Output: Yes, the given strings can be chained.
The strings can be chained as "aaa", "aab", "bbb"
and "baa"

Input: arr[] = {"aaa"};
Output: Yes

Input: arr[] = {"aaa", "bbb"};
Output: No

Input  : arr[] = ["abc", "efg", "cde", "ghi", "ija"]
Output : Yes
These strings can be reordered as, “abc”, “cde”, “efg”,
“ghi”, “ija”

Input : arr[] = [“ijk”, “kji”, “abc”, “cba”]
Output : No

 */
package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank.b on 03/04/18.
 */
public class StringChainedCircle {

    public boolean formsCircle(String[] words) {

        int n = words.length;
        int[] inDegree = new int[26];
        int[] outDegree = new int[26];
        Map<Character,List<Character>> map = new HashMap<>();
        boolean[] mark = new boolean[26];

        for (String word : words) {
            char start = word.charAt(0);
            char end = word.charAt(word.length()-1);

            inDegree[end-'a']++;
            outDegree[start-'a']++;
            mark[start-'a']=true;
            mark[end-'a']=true;


            if(map.containsKey(start)) {
                map.get(start).add(end);
            } else {
                List<Character> list = new ArrayList<>();
                list.add(end);
                map.put(start,list);
            }

        }

        for (int i=0;i<26;i++) {
            if(inDegree[i]!=outDegree[i]) {
                return false;
            }
        }

        return isConnected(map,mark,words[0].charAt(0));

    }

    private boolean isConnected(Map<Character,List<Character>> map,boolean[] mark,char start) {

        boolean[] visited = new boolean[26];

        dfs(map,visited,start);

        for(int i=0;i<26;i++) {
            if(mark[i] && !visited[i]) {
                return false;
            }
        }

        return true;

    }

    private void dfs(Map<Character,List<Character>> map,boolean[] visited,char start) {
        visited[start-'a']=true;

        for(Character c : map.get(start)) {
            if(!visited[c-'a']) {
                dfs(map,visited,c);
            }
        }

    }

    public static void main(String[] args) {
        String[] arr = new String[]{"abc", "efg", "cde", "ghi", "ija"};
        System.out.println(new StringChainedCircle().formsCircle(arr));
    }

}

/*

We solve this problem by treating this as a graph problem, where vertices will be first and last character of strings
and we will draw an edge between two vertices if they are first and last character of same string, so number of edges in
graph will be same as number of strings in the array.

See Image string circle

Now it can be clearly seen after graph representation that if a loop among graph vertices is possible then we can reorder the strings otherwise not.
a loop can be found in first and third array of string but not in second array of string.
Now to check whether this graph can have a loop which goes through all the vertices, we’ll check two conditions,
1)	Indegree and Outdegree of each vertex should be same.
2)	Graph should be strongly connected.

First condition can be checked easily by keeping two arrays, in and out for each character.
For checking whether graph is having a loop which goes through all vertices is same as checking complete directed graph is
strongly connected or not because if it has a loop which goes through all vertices then we can reach to any vertex from any other vertex that is,
graph will be strongly connected and same argument can be given for reverse statement also.
Now for checking second condition we will just run a DFS from any character and visit all reachable vertices from this,
now if graph has a loop then after this one DFS all vertices should be visited, if all vertices are visited then we will
return true otherwise false so visiting all vertices in a single DFS flags a possible ordering among strings.

 */