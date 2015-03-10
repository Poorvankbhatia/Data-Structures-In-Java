package Trees;

import java.util.HashMap;
import java.util.Map;

public class VerticalSum {
    
    private static HashMap<Integer,Integer> hashMap = new HashMap<Integer, Integer>();

    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        sum(root,0);
        System.out.println(hashMap);
        
    }
    
    private static void sum(Node root,int hd) {
        
        if(root == null) {
            return ;
        }
        
        sum(root.left,hd-1);
        
        int prevKey = (hashMap.get(hd)==null ? 0 : hashMap.get(hd));
        
        hashMap.put(hd,prevKey + root.info);

        sum(root.right,hd+1);
        
    }
    
}

/*

We need to check the Horizontal Distances from root for all nodes. 
If two nodes have the same Horizontal Distance (HD), then they are on same vertical line. 
The idea of HD is simple. HD for root is 0, a right edge (edge connecting to right subtree) is considered as +1 
horizontal distance and a left edge is considered as -1 horizontal distance.


We can do inorder traversal of the given Binary Tree. While traversing the tree, we can recursively calculate HDs.
 We initially pass the horizontal distance as 0 for root. 
 For left subtree, we pass the Horizontal Distance as Horizontal distance of root minus 1. 
 For right subtree, we pass the Horizontal Distance as Horizontal Distance of root plus 1.
 */