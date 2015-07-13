package treesPrograms;

/**
 * Created by poorvank on 7/12/15.
 */
public class Node {

    public int info;
    public Node left;
    public Node right;

    public Node(int info) {
        this(info, null, null);
    }

    public Node(int info, Node left, Node right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

