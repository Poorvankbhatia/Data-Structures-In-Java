package Trees;

public class BSTCeil {
    
    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        for(int i=0;i<10;i++) {
            System.out.println("key - " + i + " ceil " + ceil(root,i) + " floor " + floor(root,i));
        }
        //System.out.println(floor(root,6));
        
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
    
    private static int floor(Node root,int key) {
        
        if(root == null) {
            return -1;
        }
        
        if(root.info == key) {
            return root.info;
        }
        
        if(root.info > key) {
            return floor(root.left,key);
        }
        
        int floor = floor(root.right,key);
        return (floor>=root.info) ? floor : root.info;
        
    }
    
}

/*

A) Root data is equal to key. We are done, root data is ceil value.

B) Root data < key value, certainly the ceil value can’t be in left subtree. Proceed to search on right subtree as reduced problem instance.

C) Root data > key value, the ceil value may be in left subtree. We may find a node with is larger data than key value in left subtree, if not the root itself will be ceil node.

 */