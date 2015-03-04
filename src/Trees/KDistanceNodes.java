package Trees;

public class KDistanceNodes {
    
    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        printNodes(root,2);
        
    }
    
    private static void printNodes(Node root,int k) {
        
        if(root == null) {
            return;
        }
        if(k==0) {
            System.out.print(root.info+ " " );
            return;
        }
        printNodes(root.left,k-1);
        printNodes(root.right,k-1);
        
    }
    
    
}