package graphPrograms;

/**
 * Created by poorvank on 4/2/15.
 */
public class DFS_itr {

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();
        boolean[] visited = new boolean[vArray.length];


        for (boolean b : visited) {
            b = false;
        }

        iterative(vArray, 0, visited);

    }

    private static void iterative(Vertex[] vArray, int v, boolean[] visited) {

        Stack stack = new Stack();

        stack.push(vArray[v]);

        while (!stack.isEmpty()) {

            Vertex popped = (Vertex) stack.pop();

            if (!visited[popped.info]) {

                visited[popped.info] = true;

                System.out.print(popped.info + " ");

                for (int vertex : popped.adjacentVertices) {
                    stack.push(vArray[vertex]);
                }

            }

        }

    }

}


/*

In a breadth first search, you start at the root node, and then scan each node in the
 first level starting from the leftmost node, moving towards the right. 
 Then you continue scanning the second level (starting from the left) and the third level, 
 and so on until you’ve scanned all the nodes, or until you find the actual node that you 
 were searching for. In a BFS, when traversing one level, we need some way of knowing which nodes 
 to traverse once we get to the next level. The way this is done is by storing the pointers to a level’s
  child nodes while searching that level. The pointers are stored in FIFO (First-In-First-Out) queue.
   This, in turn, means that BFS uses a large amount of memory because we have to store the pointers.

 */