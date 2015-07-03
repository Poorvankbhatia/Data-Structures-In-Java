package treesPrograms;

/**
 * Created by poorvank on 6/11/15.
 */
public class TreeToDLL1 {
    
    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);
        
        root = convert(root);
        
        while (root.left!=null) {
            root = root.left;
        }
        
        System.out.println("After conversion : \n");

        //convert returns root node of the converted
        // DLL.  We need pointer to the leftmost node which is
        // head of the constructed DLL, so move to the leftmost node
        while (root!=null) {
            System.out.print(root.info + " ");
            root = root.right;
        }
        
    }
    
    private static Node convert(Node root) {
        
        if(root==null) {
            return null;
        }


        // Convert the left subtree and link to root
        if(root.left!=null) {
            
            Node l = convert(root.left);

            // Find inorder predecessor. After this loop, left
            // will point to the inorder predecessor
            while (l.right!=null) 
                l = l.right;
            
            l.right = root;
            
            root.left = l;
            
        }
        
        if(root.right!=null) {
            
            Node r = convert(root.right);

            // Find inorder successor. After this loop, right
            // will point to the inorder successor
            while (r.left!=null) {
                r = r.left;
            }
            
            root.right = r;
            
            r.left = root;
            
        }
        
        return root;
        
    }
    
}


/*

1. If left subtree exists, process the left subtree
…..1.a) Recursively convert the left subtree to DLL.
…..1.b) Then find inorder predecessor of root in left subtree (inorder predecessor is rightmost node in left subtree).
…..1.c) Make inorder predecessor as previous of root and root as next of inorder predecessor.
2. If right subtree exists, process the right subtree (Below 3 steps are similar to left subtree).
…..2.a) Recursively convert the right subtree to DLL.
…..2.b) Then find inorder successor of root in right subtree (inorder successor is leftmost node in right subtree).
…..2.c) Make inorder successor as next of root and root as previous of inorder successor.
3. Find the leftmost node and return it (the leftmost node is always head of converted DLL).
 */