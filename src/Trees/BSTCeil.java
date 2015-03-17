package Trees;

public class BSTCeil {
    
    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        for(int i=0;i<10;i++) {
            System.out.println("key - " + i + " ceil " + ceil(root,i));
        }
        
    }
    
    private static int ceil(Node root,int key) {
        
        if(root == null) {
            return -1;
        }
        
        if(root.info == key) {
            return root.info;
        }
        
        if(root.info < key) {
            return ceil(root.right,key);
        }
        
        
        int ceil = ceil(root.left,key);
        
        return (ceil >= key) ? ceil : root.info;
        
        
    }
    
}