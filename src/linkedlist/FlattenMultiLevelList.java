/*

Flattening a Linked List
Given a linked list where every node represents a linked list and contains two pointers of its type:
(i) Pointer to next node in the main list (we call it ‘right’ pointer in below code)
(ii) Pointer to a linked list where this node is head (we call it ‘down’ pointer in below code).
All linked lists are sorted. See the following example

       5 -> 10 -> 19 -> 28
       |    |     |     |
       V    V     V     V
       7    20    22    35
       |          |     |
       V          V     V
       8          50    40
       |                |
       V                V
       30               45
Write a function flatten() to flatten the lists into a single linked list. 
The flattened linked list should also be sorted. For example, 
for the above input list, output list should be 5->7->8->10->19->20->22->28->30->35->40->45->50.

 */

package linkedlist;

/**
 * Created by poorvank on 6/18/15.
 */

class MultiLevelNode {

    int info;
    MultiLevelNode right;
    MultiLevelNode down;

    public MultiLevelNode(int info) {
        this(info, null, null);
    }

    public MultiLevelNode(int info, MultiLevelNode right, MultiLevelNode down) {
        this.info = info;
        this.right = right;
        this.down = down;
    }

}

public class FlattenMultiLevelList {

    public static void main(String[] args) {

        MultiLevelNode root = new MultiLevelNode(5);
        root.down = new MultiLevelNode(7);
        root.down.down = new MultiLevelNode(8);
        root.down.down.down = new MultiLevelNode(30);
        root.right = new MultiLevelNode(10);
        root.right.down = new MultiLevelNode(20);
        root.right.right = new MultiLevelNode(19);
        root.right.right.down = new MultiLevelNode(22);
        root.right.right.down.down = new MultiLevelNode(50);

        MultiLevelNode result = flatten(root);

        MultiLevelNode current = result;

        while (current != null) {
            System.out.print(current.info + " ");
            current = current.down;
        }
    }


    private static MultiLevelNode flatten(MultiLevelNode root) {

        if (root == null || root.right == null) {
            return root;
        }

        root = merge(root, flatten(root.right));

        return root;
    }

    private static MultiLevelNode merge(MultiLevelNode root1, MultiLevelNode root2) {

        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        MultiLevelNode result = null;
        if (root1.info < root2.info) {
            result = root1;
            result.down = merge(root1.down, root2);
        } else {
            result = root2;
            result.down = merge(root1, root2.down);
        }

        return result;

    }

}

/*

The idea is to use Merge() process of merge sort for linked lists.
We use merge() to merge lists one by one. We recursively merge() the current list with already flattened list.
The down pointer is used to link nodes of the flattened list.

 */