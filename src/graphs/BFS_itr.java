package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 4/2/15.
 */
public class BFS_itr {
    
    public static void main(String[] args) {
        
        Vertex[] vArray = Input.graphInput();

        boolean[] visited = new boolean[vArray.length];
        for (boolean b : visited) {
            b = false;
        }

        iterative(vArray,0,visited);

    }
    
    private static void iterative(Vertex[] vArray,int v,boolean[] visited) {

        Queue<Vertex> queue = new LinkedList<>();
        
        queue.add(vArray[v]);
        
        while (!queue.isEmpty()) {
            
            Vertex vertex = queue.remove();
            System.out.print(vertex.info + " ");
            
            for (int ve : vertex.adjacentVertices) {
                if(!visited[ve]) {
                    queue.add(vArray[ve]);
                    visited[ve] = true;
                }
            }
            
        }
        
    }
    
}


/*

Note that the above code traverses only the vertices reachable
 from a given source vertex. All the vertices may not be reachable from a given vertex

 */