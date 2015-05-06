package Trees;

/**
 * Created by poorvank on 5/6/15.
 */
public class SwappedNodes {
    
    public static Node first=null,middle=null,last=null,previous=null;
    
    public static void main(String[] args) {
        
        Node root = new Node(5);
        root.left = new Node(2);
        root.left.right = new Node(9);
        root.right = new Node(7);
        root.right.right = new Node(4);

        System.out.println("Before in order sequence");
        Traversal.in_Order(root);
        root = correctTree(root);
        System.out.println("\nAfter correction in order sequence");
        Traversal.in_Order(root);
        
        
    }
    
    private static Node correctTree(Node root) {
        
        correctTreeUtil(root,first,middle,last,previous);



        if(first!=null && last!=null) {
            swap(first,last);
        }
        else if(first!=null && middle!=null) {
            swap(first,middle);
        }
        
        return root;
        
    }
    
    private static void swap(Node n1,Node n2) {
        
        int temp = n1.info;
        n1.info = n2.info;
        n2.info = temp;
        
    }
    
    private static void correctTreeUtil(Node root,Node first,Node middle,Node last,Node previous) {
        
        if(root!=null) {
            
            correctTreeUtil(root.left,first,middle,last,previous);
            
            if(previous!=null && root.info < previous.info) {
                
                if(first==null) {
                    first = previous;
                    middle = root;
                    System.out.println();
                }
                else {
                    last = root;
                }
                
            }
            
            previous = root;
            correctTreeUtil(root.right, first, middle, last, previous);
            
        }
        
    }
    
}
