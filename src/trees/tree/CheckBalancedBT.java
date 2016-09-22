package trees.tree;


public class CheckBalancedBT {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        Height h = new Height(0);
        if (check(root, new Height(0))) {
            System.out.println("balanced");
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean check(Node root, Height h) {


        if (root == null) {
            h.value = 0;
            return true;
        }

        Height lHeight = new Height(0);
        Height rHeight = new Height(0);

        boolean l = check(root.left, lHeight);
        boolean r = check(root.right, rHeight);

        h.value = (lHeight.value > rHeight.value ? lHeight.value : rHeight.value) + 1;

        if ((lHeight.value - rHeight.value >= 2) || (rHeight.value - lHeight.value >= 2)) {
            return false;
        }

        return (l && r);

    }

}

/*

  calculating the height in the same recursion rather than calling a height() function separately. O(n)

 */