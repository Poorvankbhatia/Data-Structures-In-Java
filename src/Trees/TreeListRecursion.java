package trees;

public class TreeListRecursion {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        Node head = treeToList(root);
        System.out.println("Head of the converted list is - " + head.info);
        System.out.println("Converted list is -  ");
        printList(head);


    }

    private static void printList(Node root) {

        Node tmp = root;

        while (tmp != null) {
            System.out.print(tmp.info + " ");
            tmp = tmp.right;
            if (tmp == root) {
                break;
            }
        }

    }

    private static Node treeToList(Node root) {

        Node aList, bList;

        //Empty tree -> Empty list
        if (root == null) {
            return root;
        }

        aList = treeToList(root.left);
        bList = treeToList(root.right);

        //Making the single root node into a list
        root.left = root;
        root.right = root;

        //Combining 3 lists together
        aList = append(aList, root);
        aList = append(aList, bList);


        System.out.println("currnt list - ");
        printList(aList);

        return aList;

    }


    //Append Nodes to list
    private static Node append(Node a, Node b) {

        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        //Create a cyclic list of  nodes/list

        Node aLast = a.left;
        Node bLast = b.left;

        join(aLast, b);
        join(bLast, a);

        return a;

    }

    //Join to Nodes
    private static void join(Node a, Node b) {

        a.right = b;
        b.left = a;

    }

}