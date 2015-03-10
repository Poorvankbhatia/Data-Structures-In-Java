package Trees;

public class SumTree {
    
    public static void main(String[] args) {
       
        Node root = new Node(26);
        root.left = new Node(10);
        root.left.left = new Node(4);
        root.left.right = new Node(6);
        root.right = new Node(3);
        root.right.right = new Node(3);
        
        System.out.println(isSumTree(root));
        
    }
    
    public static boolean isSumTree(Node root) {
        
        if(root == null || isLeaf(root)) {
            return true;
        }
        
        if( isSumTree(root.left) && isSumTree(root.right)) {
            
            int ls,rs;
            
            if(root.left == null) {
                ls = 0;
            }
            else if(isLeaf(root.left)) {
                ls = root.left.info;
            }
            else {
                ls = 2 *(root.left.info);
            }

            if(root.right == null) {
                rs = 0;
            }
            else if(isLeaf(root.right)) {
                rs = root.right.info;
            }
            else {
                rs = 2 *(root.right.info);
            }
            
            if(root.info == ls + rs) {
                return true;
            }
            
            
        }

        return false;
        
    }
    
    private static boolean isLeaf(Node node) {
        
        if(node==null) {
            return false;
        }
        
        if(node.left==null && node.right == null) {
            return true;
        }
        
        return false;
        
    }
    
}

/*

The Method 1 uses sum() to get the sum of nodes in left and right subtrees. The method 2 uses following rules to get the sum directly.
1) If the node is a leaf node then sum of subtree rooted with this node is equal to value of this node.
2) If the node is not a leaf node then sum of subtree rooted with this node is twice the value of this node (Assuming that the tree rooted with this node is SumTree).

 */