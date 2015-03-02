package Trees;

public class NonRecursiveTraversal {
    
    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        System.out.println("Pre Order Traversal is - ");
        preOrder(root);
        System.out.println("\nIn Order Traversal is - ");
        inOrder(root);
        
        
        
    }
    
    private static void preOrder(Node root) {
        
        if(root == null) {
            return;
        }
        Stack stack = new Stack();
        
        stack.push(root);
        
        while (!stack.isEmpty()) {
            
            root = stack.pop();
            System.out.print(root.info + " ");
            
            if(root.right!=null) {
                stack.push(root.right);
            }
            if(root.left!=null) {
                stack.push(root.left);
            }
            
        }
        
    }
    
    private static void inOrder(Node root) {
        
        if(root == null) {
            return;
        }
        
    }
    
    private static void postOrder(Node root) {
        
    
            
        
        
    }
    
}