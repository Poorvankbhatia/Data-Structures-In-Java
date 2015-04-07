/*

Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv,
 vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.

for 6 vertices and edges as 
5,0
5,2
4,0
4,1
2,3
3,1

5 4 2 3 1 0

 */

package graphs;

/**
 * Created by poorvank on 4/7/15.
 */
public class TopologicalSort {
    
    public static void main(String[] args) {
        
        Vertex[] vArray = Input.graphInput();
        boolean[] visited = new boolean[vArray.length];
        
        sort(vArray,visited);
        
        
    }
    
    
    private static void sort(Vertex[] vArray,boolean[] visited) {
        
        Stack stack = new Stack();

        for (int i=0;i<vArray.length;i++) {
            if(!visited[i]) {
                sortUtil(vArray,visited,stack,i);
            }
        }
        
        while (!stack.isEmpty()) {
            System.out.print(" " + ((Vertex)stack.pop()).info);
        }
        
    }
    
    
    private static void sortUtil(Vertex[] vArray,boolean[] visited,Stack stack,int i) {
        
        visited[i] = true;
        
        for (int v : vArray[i].adjacentVertices) {
            if(!visited[v]) {
                sortUtil(vArray,visited,stack,v);
            }
        }
        
        stack.push(vArray[i]);
    }
    
}


/*

In topological sorting, we use a temporary stack. We don’t print the vertex immediately, we first recursively call
 topological sorting for all its adjacent vertices, then push it to a stack.
  Finally, print contents of stack. Note that a vertex is pushed to stack only 
  when all of its adjacent vertices (and their adjacent vertices and so on) are already in stack.


 */