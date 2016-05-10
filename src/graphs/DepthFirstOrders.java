package graphs;

import utility.*;
import utility.Stack;

/**
 * Created by poorvank on 10/05/16.
 */
public class DepthFirstOrders {

    private boolean[] marked;

    private Stack<Integer> reversePost;
    private Queue<Integer> post;

    public DepthFirstOrders(Digraph G) {
        int size = G.getVertexCount();
        marked = new boolean[size];
        reversePost = new Stack<>();
        post = new Queue<>();
        for (int i=0;i<size;i++) {
            if(!marked[i]) {
                dfs(G,i);
            }
        }

    }

    private void dfs(Digraph G,int i) {
        marked[i] = true;
        for (Integer vertex: G.getAdj(i)) {
            if(!marked[vertex]) {
                dfs(G,vertex);
            }
        }
        reversePost.push(i);
        post.enqueue(i);
    }

    public Iterable<Integer> getReversePost() {
        return reversePost;
    }

    public Iterable<Integer> getPost() {
        return post;
    }
}
