package trees;

import java.util.LinkedList;
import java.util.Queue;

public class QueueLevelOrderTraversal {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        Queue<Node> nodeQueue = new LinkedList<Node>();
        levelTraversal(root, nodeQueue);


    }

    public static void levelTraversal(Node root, Queue<Node> nodeQueue) {

        Node tmp = root;

        while (tmp != null) {

            System.out.print(tmp.info + " ");

            if (tmp.left != null) {
                nodeQueue.add(tmp.left);
            }

            if (tmp.right != null) {
                nodeQueue.add(tmp.right);
            }

            tmp = nodeQueue.poll();

        }

    }

}