package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 3/23/15.
 */
public class NextRightNode {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        if (nextRight(root, 25) != null) {
            System.out.println("Next root is - " + nextRight(root, 25).info);
        } else {
            System.out.println("null");
        }

    }

    private static Node nextRight(Node root, int key) {

        Queue<Node> nodeQueue = new LinkedList<Node>();
        Queue<Integer> levelQueue = new LinkedList<Integer>();

        nodeQueue.add(root);
        levelQueue.add(0);

        while (nodeQueue.size() != 0) {


            Node current = nodeQueue.remove();
            int level = levelQueue.remove();


            if (current.info == key) {

                if (nodeQueue.size() == 0 || levelQueue.peek() != level) {
                    return null;
                } else {
                    return nodeQueue.peek();
                }

            }
            if (current.left != null) {
                nodeQueue.add(current.left);
                levelQueue.add(level + 1);
            }
            if (current.right != null) {
                nodeQueue.add(current.right);
                levelQueue.add(level + 1);
            }

            //break;
        }

        return null;

    }

}
