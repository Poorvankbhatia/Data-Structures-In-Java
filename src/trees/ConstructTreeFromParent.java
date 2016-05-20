package trees;

import java.util.*;
import java.lang.*;


class ConstructTreeFromParent {
    public static class Node {
        int value;
        List<Node> children = new ArrayList<Node>();

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node buildTree(int arr[]) {
        Map<Integer, Node> map = new HashMap<Integer, Node>();

        int rootIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(i);
            map.put(i, node);

            if (arr[i] == -1) {
                rootIndex = i;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            Node child = map.get(i);
            Node parent = map.get(arr[i]);

            if (parent != null) {
                parent.children.add(child);
            }
        }

        return map.get(rootIndex);
    }

    public static void main(String[] args) throws java.lang.Exception {
        int arr[] = new int[]{1, -1, 1, 2, 3, 1};
        Node n = buildTree(arr);
    }
}
