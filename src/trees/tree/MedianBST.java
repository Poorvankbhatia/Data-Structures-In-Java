/*

Find median of BST in O(n) time and O(1) space
Given a Binary Search Tree, find median of it.

If no. of nodes are even: then median = ((n/2th node + (n+1)/2th node) /2
If no. of nodes are odd : then median = (n+1)th node.


 Given BST(with odd no. of nodes) is :
                    6
                 /    \
                3       8
              /   \    /  \
             1     4  7    9

Inorder of Given BST will be : 1, 3, 4, 6, 7, 8, 9
So, here median will 6.

Given BST(with even no. of nodes) is :
                    6
                 /    \
                3       8
              /   \    /
             1     4  7

Inorder of Given BST will be : 1, 3, 4, 6, 7, 8
So, here median will  (4+6)/2 = 5.

 */
package trees.tree;

/**
 * Created by poorvank.b on 25/05/18.
 */
public class MedianBST {

    private int countNodes(Node root) {

        int count=0;

        if(root==null) {
            return count;
        }

        Node current = root;

        while (current!=null) {

            if(current.left==null) {
                count++;
                current = current.right;
            }else {

                Node prev = current.left;

                while (prev.right!=null && prev.right!=current) {
                    prev = prev.right;
                }

                if(prev.right==null) {
                    prev.right = current;
                    current = current.left;
                } else {
                    prev.right = null;
                    count++;
                    current = current.right;
                }

            }

        }

        return count;

    }

    public int findMedian(Node root) {

        int count = countNodes(root);

        if(count==0) {
            return 0;
        }

        Node current = root;
        Node prev=root,pre=null;
        int currentCount=0;
        while (current!=null) {

            if(current.left==null) {
                currentCount++;

                if((count%2!=0) && (currentCount==(count+1)/2)) {
                    return prev.info;
                }

                if(count%2==0 && currentCount==count/2+1) {
                    return (prev.info+current.info)/2;
                }

                prev = current;
                current = current.right;

            } else {

                pre = current.left;

                while (pre.right!=null && pre.right!=current) {
                    pre=pre.right;
                }

                if(pre.right==null) {
                    pre.right = current;
                    current = current.left;
                } else {
                    pre.right = null;
                    currentCount++;

                    prev = pre;

                    if((count%2!=0) && (currentCount==(count+1)/2)) {
                        return current.info;
                    }

                    if(count%2==0 && currentCount==count/2+1) {
                        return (prev.info+current.info)/2;
                    }

                    prev = current;
                    current = current.right;

                }

            }

        }

        return -1;

    }

    public static void main(String[] args) {

        Node node = new Node(50);
        node.left = new Node(30);
        node.left.left = new Node(20);
        node.left.right = new Node(40);
        node.right = new Node(70);
        node.right.left = new Node(60);
        node.right.right = new Node(80);

        System.out.println(new MedianBST().findMedian(node));

    }

}

/*

The task is very simple if we are allowed to use extra space but Inorder traversal using recursion and stack both uses Space which is not allowed here.
 So, the solution is to do Morris Inorder traversal as it doesn’t require any extra space.

Implementation:
1- Count the no. of nodes in the given BST
   using Morris Inorder Traversal.
2- Then Perform Morris Inorder traversal one
   more time by counting nodes and by checking if
   count is equal to the median point.
   To consider even no. of nodes an extra pointer
   pointing to the previous node is used.

 */