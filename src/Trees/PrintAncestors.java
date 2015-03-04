package Trees;

public class PrintAncestors {
    
    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        if(! ancestor(root,9)) {
            System.out.println("No ancestor");
        }
        
        
    }
    
    private static boolean ancestor(Node root,int target) {
        
        if(root==null) {
            return false;
        }
        
        if(root.info == target) {
            return true;
        }
        
        if(ancestor(root.left,target) || ancestor(root.right,target)) {
            System.out.print(root.info + " ");
            return true;
        }
        
        return false;
         
    }
    
}