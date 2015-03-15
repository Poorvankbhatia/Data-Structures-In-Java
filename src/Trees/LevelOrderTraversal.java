package Trees;

public class LevelOrderTraversal {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        int height = treeHeight(root);
        System.out.println("height of tree - " + height);
        for (int d = 1; d <= height; d++) {
            traversal(root, d);
        }

    }

    private static void traversal(Node root, int level) {

        if (root != null) {
            if (level == 1) {
                System.out.print(root.info + " ");
            } else {
                traversal(root.left, level - 1);
                traversal(root.right, level - 1);
            }
        }

    }

    private static int treeHeight(Node root) {

        if (root.left == null && root.right == null) {
            return 1;
        } else {
            return maxTwoNo(treeHeight(root.left), treeHeight(root.right)) + 1;
        }

    }

    private static int maxTwoNo(int a, int b) {

        return a > b ? a : b;

    }

}

/*

Time Complexity: O(n^2) in worst case. For a skewed tree, printGivenLevel() takes O(n) time where n is the number of nodes 
in the skewed tree. So time complexity of printLevelOrder() is O(n) + O(n-1) + O(n-2) + .. + O(1) which is O(n^2).

 */