package trees.tree;

public class PrintAncestors {

    private static int immediateAncestor = -1;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        if (!ancestor(root, 9)) {
            System.out.println("No ancestor");
        }


        System.out.println("\nimmediate ancestor - " + immediateAncestor);

    }

    private static boolean ancestor(Node root, int target) {

        if (root == null) {
            return false;
        }

        if (root.info == target) {
            return true;
        }

        if (ancestor(root.left, target) || ancestor(root.right, target)) {
            if (immediateAncestor == -1) {
                immediateAncestor = root.info;
            }
            System.out.print(root.info + " ");
            return true;
        }

        return false;

    }

}