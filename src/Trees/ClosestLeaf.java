/*

Given a Binary Tree and a key ‘k’, find distance of the closest leaf from ‘k’.

Examples:

              A
            /    \
           B       C
                 /   \
                E     F
               /       \
              G         H
             / \       /
            I   J     K

Closest leaf to 'H' is 'K', so distance is 1 for 'H'
Closest leaf to 'C' is 'B', so distance is 2 for 'C'
Closest leaf to 'E' is either 'I' or 'J', so distance is 2 for 'E'
Closest leaf to 'B' is 'B' itself, so distance is 0 for 'B'

 */

package trees;

/**
 * Created by poorvank on 6/12/15.
 */
public class ClosestLeaf {
    
    private static int leafNode = 0;
    
    public static void main(String[] args) {
        
        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);
        Node[] ancestors = new Node[100];
        
        System.out.println(findLeafDistance(root,ancestors,0,12));
        
    }
    
    private static int findLeafDistance(Node root,Node[] ancestors,int index,int key) {

        //If(key is not found)
        if(root==null) {
            return Integer.MAX_VALUE;
        }
        
        
        if(root.info == key) {
        
            int result = findClosestDown(root);

            for (int i = index-1 ;i>=0 ;i--) {
                result = Math.min(result, (index - i) + findClosestDown(ancestors[i]));
            }

            return result;
            
        }

        
        ancestors[index] = root;
        
        return Math.min((findLeafDistance(root.left,ancestors,index+1,key)) , 
                (findLeafDistance(root.right,ancestors,index+1,key)));
        
        
        
    }
    
    private static int findClosestDown(Node root) {
        
        if(root==null) {
            return Integer.MAX_VALUE;
        }
        if(root.left == null && root.right==null) {
            return 0;
        }
        
        return 1+Math.min(findClosestDown(root.left),findClosestDown(root.right));
        
    }
    
}


/*

The main point to note here is that a closest key can either be a descendent of given
 key or can be reached through one of the ancestors.
The idea is to traverse the given tree in preorder and keep track of ancestors in an array. 
When we reach the given key, we evaluate distance of the closest leaf in subtree rooted with given key. 
We also traverse all ancestors one by one and find distance of the closest leaf in the subtree rooted with ancestor.
We compare all distances and return minimum.

 */