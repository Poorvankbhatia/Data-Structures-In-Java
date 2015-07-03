package treesPrograms;


import java.util.Arrays;

class StringNode {

    public String info;
    public StringNode left;
    public StringNode right;

    public StringNode(String info) {
        this(info, null, null);
    }

    public StringNode(String info, StringNode left, StringNode right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

public class FindPossibleInterpretations {

    private static final String[] alphabet = {"", "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "v", "z"};

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 1, 3, 4};
        printLeaves(createTree(0, "", arr));

    }

    private static StringNode createTree(int data, String parent, int[] arr) {

        if (data > 26) {
            return null;
        }

        String dataString = parent + alphabet[data];

        StringNode root = new StringNode(dataString);

        if (arr.length != 0) {

            data = arr[0];

            int[] newArray = Arrays.copyOfRange(arr, 1, arr.length);

            root.left = createTree(data, dataString, newArray);

            if (arr.length > 1) {

                data = arr[0] * 10 + arr[1];

                // new array will be from index 2 to end as we 
                // are consuming first two index with this node

                newArray = Arrays.copyOfRange(arr, 2, arr.length);

                root.right = createTree(data, dataString, newArray);

            }

        }

        return root;

    }

    private static void printLeaves(StringNode root) {

        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.println(root.info + " ");
        }

        printLeaves(root.left);
        printLeaves(root.right);

    }


}

//Complexity is O(2^n) 

/*

The idea here is string can take at-most two paths:
1. Proces single digit
2. Process two digits
That means we can use binary tree here. Processing with single digit will be left child and two digits will be right child.
 If value two digits is greater than 26 then our right child will be null as we don’t have alphabet for greater than 26.

Let’s understand with an example .Array a = {1,2,1}. Below diagram shows that how our tree grows.

                           “” {1,2,1}            Codes used in tree
                       /             \               "a" --> 1
                      /               \              "b" --> 2 
                  "a"{2,1}            "l"{1}         "l" --> 12
                 /        \          /     \
                /          \        /       \
            "ab"{1}        "au"    "la"      null
             /    \
            /      \
         "aba"      null
Braces {} contain array still pending for processing. Note that with every level, our array size decreases.
 If you will see carefully, it is not hard to find that tree height is always n (array size)
How to print all strings (interpretations)? Output strings are leaf node of tree. i.e for {1,2,1}, output is {aba au la}.
We can conclude that there are mainly two steps to print all interpretations of given integer array.

Step 1: Create a binary tree with all possible interpretations in leaf nodes.

Step 2: Print all leaf nodes from the binary tree created in step 1.

 */