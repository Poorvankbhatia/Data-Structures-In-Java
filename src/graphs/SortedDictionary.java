
/*

Given a sorted dictionary (array of words) of an alien language, find order of characters in the language.

Examples:

Input:  words[] = {"baa", "abcd", "abca", "cab", "cad"}
Output: Order of characters is 'b', 'd', 'a', 'c'
Note that words are sorted and in the given language "baa" 
comes before "abcd", therefore 'b' is before 'a' in output.
Similarly we can find other orders.

Input:  words[] = {"caa", "aaa", "aab"}
Output: Order of characters is 'c', 'a', 'b'

 */

package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 4/8/15.
 */


class DictionaryNode {
    
    int info;
    List<Integer> adjacentVertices;
    
    public DictionaryNode(int info) {
        this.info = info;
        adjacentVertices = new ArrayList<>();
    }
    
}

class DictionaryGraph {
    
    int vertexCount;
    List<DictionaryNode> dictionaryNodeList;
    
    public DictionaryGraph(int n) {
        vertexCount = n;
        dictionaryNodeList = new ArrayList<>();
        
        for (int i=0;i<n;i++) {
            dictionaryNodeList.add(new DictionaryNode(i));
        }
    }
    
    public void addEdge(int a,int b) {
        
        System.out.println("Added an edge from " + (char) ('a' + a) + " to  " + (char) ('a' + b));
        dictionaryNodeList.get(a).adjacentVertices.add(b);
        
    }
    
    
}

public class SortedDictionary {
    
    public static void main(String[] args) {
        
        String[] words = new String[]{"baa", "abcd", "abca", "cab", "cad"};
        printOrderAlphabets(words,4);

        System.out.println();
        
        String[] newWords = new String[]{"caa", "aaa", "aab"};
        printOrderAlphabets(newWords,3);
        
    }
    
    private static void printOrderAlphabets(String[] words,int count) {
        
        DictionaryGraph dg = new DictionaryGraph(count);
        
        for (int i=0;i<words.length-1;i++) {
            
            String word1 = words[i],word2 = words[i+1];
            
            System.out.println("Checking for word - " + word1 +  " & " + word2);
            
            for (int j=0;j<min(word1.length(),word2.length());j++) {
                
                if(word1.charAt(j)!=word2.charAt(j)) {

                    dg.addEdge(word1.charAt(j)-'a',word2.charAt(j)-'a');
                    break;
                }
                
            }
            
        }

        System.out.print("order of characters in the language -  ");
        boolean[] visited = new boolean[count];
        topologicalSort(dg,visited);
        
    }
    
    private static int min(int a , int b) {
        return a>b?b:a;
    }
    
    private static void topologicalSort(DictionaryGraph dg,boolean[] visited) {
        
        Stack stack = new Stack();
        for (int i=0;i<dg.vertexCount;i++) {
            if(!visited[i]) {
                sortUtil(visited,stack,dg,i);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(" " + (char)('a' + ((DictionaryNode) stack.pop()).info));
        }
        
        
    }
    
    private static void sortUtil(boolean[] visited,Stack stack,DictionaryGraph dg,int i) {
        
        visited[i] = true;
        
        for (int v : dg.dictionaryNodeList.get(i).adjacentVertices) {
            
            if(!visited[v]) {
                sortUtil(visited,stack,dg,v);
            }
            
        }
        
        stack.push(dg.dictionaryNodeList.get(i));
        
    }
    
}


/*

The idea is to create a graph of characters and then find topological sorting of the created graph.
 Following are the detailed steps.

1) Create a graph g with number of vertices equal to the size of alphabet in the given alien language. 
For example, if the alphabet size is 5, then there can be 5 characters in words. Initially there are no edges in graph.

2) Do following for every pair of adjacent words in given sorted array.
…..a) Let the current pair of words be word1 and word2. One by one compare characters of both words and
 find the first mismatching characters.
…..b) Create an edge in g from mismatching character of word1 to that of word2.

3) Print topological sorting of the above created graph

 */