/*

Build BT from ancestor matrix
 */

package trees;

/**
 * Created by poorvank on 7/4/15.
 */
public class BuildTreeFromAncestor {

    private static final int REMOVED = -1;

    private static int[][] ancestorMatrix = {{0, 1, 1, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0}};
    private static int[] nodeName = {1, 4, 5, 8, 10, 30, 40};

    public static void main(String[] args) {

        Node root = createTree();
        Traversal.in_Order(root);

        printMat();

    }

    private static Node createTree() {

        calculateInitialSum();

        int rootValue = findAndRemoveZeroElement();

        if (rootValue == -1) {
            return null;
        }

        Node root = new Node(rootValue);

        createTreeRecursive(root);

        return root;

    }

    /**
     * The below function will set up the initial sum values for each row of the matrix.
     * *  The sum column, will represent total number of ancestors the value has.
     */
    private static void initializeSumZero() {

        int n = nodeName.length;


        for (int i = 0; i < n; i++) {

            if (ancestorMatrix[i][n] != -1)
                ancestorMatrix[i][n] = 0;

        }

    }

    private static void createTreeRecursive(Node root) {

        if (root == null) {
            return;
        }

        decrementParentCount(root.info);

        initializeSumZero();

        calculateInitialSum();

        int value = findAndRemoveZeroElement();
        if (value != -1) {
            root.left = new Node(value);
        }


        value = findAndRemoveZeroElement();
        if (value != -1) {
            root.right = new Node(value);
        }

        if (root.left != null) {
            createTreeRecursive(root.left);
        }

        if (root.right != null) {
            createTreeRecursive(root.right);
        }

    }
    
    /*
    
    Then we need a function which will decrement the sum values from the matrix,
     When a row is removed, all its children’s sum value will be decremented
    
     */

    private static void decrementParentCount(int value) {

        int n = nodeName.length;

        for (int j = 0; j < n; j++) {

            if (nodeName[j] == value) {

                for (int i = 0; i < n; i++) {

                    if (ancestorMatrix[i][j] == 1) {
                        ancestorMatrix[i][j]--;
                    }

                }

            }

        }

    }

    private static int findAndRemoveZeroElement() {

        int n = nodeName.length;

        for (int i = 0; i < n; i++) {

            if (ancestorMatrix[i][n] == 0) {
                ancestorMatrix[i][n] = REMOVED;
                System.out.println(nodeName[i] + " removed");
                return nodeName[i];
            }

        }

        return -1;

    }

    private static void calculateInitialSum() {

        int n = nodeName.length;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                ancestorMatrix[i][n] += ancestorMatrix[i][j];

            }

        }


    }

    private static void printMat() {


        int n = nodeName.length;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n + 1; j++) {

                System.out.print(ancestorMatrix[i][j] + " ");

            }

            System.out.println();

        }

    }


}


/*

http://www.ritambhara.in/build-binary-tree-from-ancestor-matrics/

 */