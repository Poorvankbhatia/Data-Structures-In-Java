package treesPrograms;

public class SpiralLevelOrder {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        boolean flg = false;
        System.out.println("Recursive method ");
        for (int i = 0; i < height(root); i++) {
            spiralRecursive(root, flg, i);
            flg = !flg;
        }
        System.out.println("\niterative method");
        spiralIterative(root);

    }

    private static void spiralRecursive(Node root, boolean flag, int depth) {

        if (root != null) {
            if (depth == 0) {
                System.out.print(root.info + " ");
                return;
            }
            if (flag) {
                spiralRecursive(root.left, flag, depth - 1);
                spiralRecursive(root.right, flag, depth - 1);
            } else {
                spiralRecursive(root.right, flag, depth - 1);
                spiralRecursive(root.left, flag, depth - 1);
            }
        }


    }

    private static int height(Node root) {

        if (root == null) {
            return 0;
        } else {
            return maxOfTwo(height(root.right), height(root.left)) + 1;
        }

    }

    private static int maxOfTwo(int a, int b) {

        return a > b ? a : b;

    }

    private static void spiralIterative(Node root) {

        if (root == null) {
            return;
        }

        Stack stack1 = new Stack();
        Stack stack2 = new Stack();

        stack1.push(root);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {

            while (!stack1.isEmpty()) {

                root = stack1.pop();
                System.out.print(root.info + " ");

                if (root.right != null) {
                    stack2.push(root.right);
                }
                if (root.left != null) {
                    stack2.push(root.left);
                }

            }

            while (!stack2.isEmpty()) {

                root = stack2.pop();
                System.out.print(root.info + " ");

                if (root.left != null) {
                    stack1.push(root.left);
                }
                if (root.right != null) {
                    stack1.push(root.right);
                }

            }

        }


    }

}


/*

ZigZag order traversal

 */