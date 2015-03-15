package Trees;

public class NonRecursiveTraversal {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("Pre Order Traversal is - ");
        preOrder(root);
        System.out.println("\nIn Order Traversal is - ");
        inOrder(root);
        System.out.println("\nPost Order Traversal is - ");
        postOrder(root);


    }

    private static void preOrder(Node root) {

        if (root == null) {
            return;
        }
        Stack stack = new Stack();

        stack.push(root);

        while (!stack.isEmpty()) {

            root = stack.pop();
            System.out.print(root.info + " ");

            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }

        }

    }

    private static void inOrder(Node root) {


        if (root == null) {
            return;
        }

        Stack stack = new Stack();

        boolean flag = true;

        while (flag) {


            while (root.left != null) {
                stack.push(root);
                root = root.left;
            }

            while (root.right == null) {
                System.out.print(root.info + " ");
                if (stack.isEmpty()) {
                    flag = false;
                    break;
                }
                root = stack.pop();
            }
            if (flag) {
                System.out.print(root.info + " ");
                root = root.right;
            }

        }


    }

    private static void postOrder(Node root) {

        if (root == null) {
            return;
        }

        Stack stack = new Stack();

        boolean flag = true;
        Node q = null;

        while (flag) {

            while (root.left != null) {
                stack.push(root);
                root = root.left;
            }

            while (root.right == null || q == root.right) {
                System.out.print(root.info + " ");
                q = root;
                if (stack.isEmpty()) {
                    flag = false;
                    break;
                }
                root = stack.pop();
            }
            if (flag) {
                stack.push(root);
                root = root.right;
            }

        }


    }

}



/*

Validate for input 5 7 6 9 2

 */