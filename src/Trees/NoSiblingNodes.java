package Trees;

/**
 * Created by poorvank on 3/24/15.
 */
public class NoSiblingNodes {
    
    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        System.out.print("No sibling roots are : - ");
        printNoSibling(root);
        
    }
    
    private static void printNoSibling(Node root) {
        
        if(root == null) {
            return;
        }
        
        if(root.left != null && root.right != null) {
            printNoSibling(root.left);
            printNoSibling(root.right);
        }
        
        else if(root.left!=null) {
            System.out.print(root.left.info + " ");
            printNoSibling(root.left);
        }
        
        else if(root.right!=null) {
            System.out.print(root.right.info + " ");
            printNoSibling(root.right);
        }
        
        
    }
    
}
