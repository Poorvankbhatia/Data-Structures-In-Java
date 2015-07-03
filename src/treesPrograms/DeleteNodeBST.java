/*

Delete a node in a binary search tree

 */

package treesPrograms;

/**
 * Created by poorvank on 7/3/15.
 */
public class DeleteNodeBST {
    
    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(9);
        root.right = new Node(45);
        root.right.left = new Node(30);
        root.right.right = new Node(46);
        
        System.out.println("Inorder before: ");
        Traversal.in_Order(root);

        System.out.println("\nInorder after: ");
        Traversal.in_Order(delete(root, 45));
        
    }
    
    private static Node delete(Node root,int key) {
        
        if(root==null) {
            return root;
        }
        
        if(root.info < key) {
            delete(root.right,key);
        }
        
        if(root.info > key) {
            delete(root.left,key);
        }
        
        //Found Key
        else {
            
            if(root.left==null && root.right==null) {
                root = null;
                return root;
            }
            
            if(root.left==null) {
                Node temp = root.right;
                root = null;
                return temp;
            }
            
            if(root.right==null) {
                Node temp = root.left;
                root = null;
                return temp;
            }
            
            Node temp = getInorderSuccessor(root.right);
            
            root.info = temp.info;
            
            root.right = delete(root.right,temp.info);
            
        }
        
        return root;
        
    }
    
    private static Node getInorderSuccessor(Node root) {
        
        if(root==null) {
            return root;
        }
        
        while (root.left!=null) {
            root = root.left;
        }
        
        return root;
        
    } 
    
}


/*

When we delete a node, there possibilities arise.
1) Node to be deleted is leaf: Simply remove from the tree.
              50                            50
           /     \         delete(20)      /   \
          30      70       --------->    30     70 
         /  \    /  \                     \    /  \ 
       20   40  60   80                   40  60   80
2) Node to be deleted has only one child: Copy the child to the node and delete the child
              50                            50
           /     \         delete(30)      /   \
          30      70       --------->    40     70 
            \    /  \                          /  \ 
            40  60   80                       60   80
3) Node to be deleted has two children: Find inorder successor of the node. Copy contents of the inorder successor 
to the node and delete the inorder successor. Note that inorder predecessor can also be used.
              50                            60
           /     \         delete(50)      /   \
          40      70       --------->    40    70 
                 /  \                            \ 
                60   80                           80
The important thing to note is, inorder successor is needed only when right child is not empty. 
In this particular case, inorder successor can be obtained by finding the minimum value in right child of the node.

 */