package treesPrograms;

import java.util.HashMap;

/**
 * Created by poorvank on 6/10/15.
 */

class CloneNode {
    
    int info;
    CloneNode left;
    CloneNode right;
    CloneNode random;
    
    public CloneNode(int info) {
        this(info,null,null,null);
    }
    public CloneNode(int info,CloneNode left,CloneNode right,CloneNode random) {
        this.info = info;
        this.left = left;
        this.right = right;
        this.random = random;
    }
    
}

public class CloneBT {
    
    public static void main(String[] args) {
      
        CloneNode root = new CloneNode(1);
        root.left = new CloneNode(3);
        root.right = new CloneNode(5);
        root.left.left = new CloneNode(2);
        root.left.right = new CloneNode(9);
        root.random = root.left.right;
        root.left.left.random = root;
        root.left.right.random = root.right;
        System.out.println("Before ");
        printInOrder(root);
        CloneNode newRoot = cloneTree(root);
        System.out.println("\nAfter ");
        printInOrder(root);
        System.out.println();
        printInOrder(newRoot);
        
    }
    
    private static CloneNode cloneTree(CloneNode root) {

        HashMap<CloneNode,CloneNode> map = new HashMap<>();
        CloneNode newRoot = copyLeftRight(root,map);
        copyRandom(root,map);
        return newRoot;
    }
    
    private static CloneNode copyLeftRight(CloneNode root,HashMap<CloneNode,CloneNode> map) {
        
        if(root==null) {
            return null;
        }
        CloneNode newNode = new CloneNode(root.info);
        map.put(root,newNode);
        newNode.left = copyLeftRight(root.left,map);
        newNode.right = copyLeftRight(root.right,map);
        return newNode;
        
    }
    
    private static void copyRandom(CloneNode root,HashMap<CloneNode,CloneNode> map) {
        
        if(root==null) {
            return;
        }
        map.get(root).random =map.get(root.random);
        copyRandom(root.left,map);
        copyRandom(root.right,map);
    }
    
    private static void printInOrder(CloneNode root) {
        
        if(root==null) {
            return;
        }
        printInOrder(root.left);
        System.out.print("[" + root.info + " ");
        if(root.random==null){
            System.out.print("NULL] ");
        } else {
            System.out.print(root.random.info + "] ");
        }
        
        printInOrder(root.right);
    }
    
}


/*


The idea is to store mapping from given tree nodes to clone tre node in map. Following are detailed steps.

1) Recursively traverse the given Binary and copy key value, left pointer and right pointer to 
clone tree. While copying, store the mapping from given tree node to clone tree node in a map.
 In the following pseudo code, ‘cloneNode’ is currently visited node of clone tree and ‘treeNode’
  is currently visited node of given tree.

   cloneNode->key  = treeNode->key
   cloneNode->left = treeNode->left
   cloneNode->right = treeNode->right
   map[treeNode] = cloneNode 
2) Recursively traverse both trees and set random pointers using entries from hash table.

   cloneNode->random = map[treeNode->random] 

 */