/*

Given a Perfect Binary Tree, reverse the alternate level nodes of the binary tree.


Given tree:
               a
            /     \
           b       c
         /  \     /  \
        d    e    f    g
       / \  / \  / \  / \
       h  i j  k l  m  n  o

Modified tree:
  	       a
            /     \
           c       b
         /  \     /  \
        d    e    f    g
       / \  / \  / \  / \
      o  n m  l k  j  i  h


 */

package treesPrograms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by poorvank on 7/15/15.
 */
public class ReverseAlternateNodesBT {
    
    private static int index = 0;
    
    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(8);
        root.right = new Node(45);
        root.right.right = new Node(46);
        root.right.left = new Node(18);
        
        System.out.print("Inorder before \n");
        Traversal.in_Order(root);
        
        System.out.print("\nIn order after \n");
        Traversal.in_Order(reverseAlternate(root));
        
    }
    
    private static Node reverseAlternate(Node root) {

        List<Integer> list = new ArrayList<>();
        
        storeAlternate(list,root,0);
        
        reverseList(list);
        
        index=0;
        
        modifyTree(list,root,0);
        
        return root;
        
    }
    
    
    private static void modifyTree(List<Integer> list,Node root,int level) {

        if(root==null) {
            return;
        }

        modifyTree(list,root.left,level+1);
        
        if(level%2!=0) {

            root.info = list.get(index);
            index++;

        }

        modifyTree(list, root.right, level + 1);

        
    }
    
    private static void storeAlternate(List<Integer> list,Node root,int level) {
        
        if(root==null) {
            return;
        }
        
        storeAlternate(list,root.left,level+1);

        if(level%2!=0) {
            
            list.add(root.info);
            index++;
            
        }
        
        storeAlternate(list,root.right,level+1);
        
    }
    
    
    private static void reverseList(List<Integer> list) {
        
        Collections.reverse(list);
        
    }
    
}

/*

1) Traverse the given tree in inorder fashion and store all odd level nodes in an auxiliary array. 
For the above example given tree, contents of array become {h, i, b, j, k, l, m, c, n, o}

2) Reverse the array. The array now becomes {o, n, c, m, l, k, j, b, i, h}

3) Traverse the tree again inorder fashion. While traversing the tree, one by one take elements from array 
and store elements from array to every odd level traversed node.
For the above example, we traverse ‘h’ first in above array and replace ‘h’ with ‘o’. Then we traverse ‘i’
 and replace it with n.

 */