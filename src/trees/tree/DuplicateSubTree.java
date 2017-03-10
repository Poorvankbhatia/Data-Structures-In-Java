/*

Given a Binary Tree, check whether the Binary tree contains a duplicate sub-tree of size 2 or more.
Note : Two same leaf nodes are not considered as subtree size of a leaf node is one.

Input :  Binary Tree
               A
             /    \
           B        C
         /   \       \
        D     E       B
                     /  \
                    D    E
Output : Yes


 */
package trees.tree;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank.b on 10/03/17.
 */
public class DuplicateSubTree {

    Set<String> set = new HashSet<>();
    private boolean flag = false;

    public boolean isDupTree(Node root) {

        if(root==null) {
            return false;
        }
        isDupUtil(root);
        return isFlag();
    }


    public boolean isFlag() {
        return flag;
    }

    private String isDupUtil(Node root) {

        if(root==null) {
            return null;
        } else if(root.left==null && root.right==null) {
            return root.info+"";
        }

        String left = isDupUtil(root.left);
        if(flag) {
            return left;
        }

        String right = isDupUtil(root.right);
        if(flag) {
            return right;
        }

        String current = root.info+left+right;


        if(current.length()>=3) {
            if(set.contains(current)) {
                flag = true;
            } else {
                set.add(current);
            }
        }

        return current;

    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(2);
        root.right.right.right = new Node(5);
        root.right.right.left= new Node(9);
        System.out.println(new DuplicateSubTree().isDupTree(root));
    }



}

/*

An Efficient solution based on tree serialization and hashing. The idea is to serialize subtrees as strings and store the
strings in hash table. Once we find a serialized tree (which is not a leaf) already existing in hash-table, we return true.

 */