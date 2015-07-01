/*

 Store deepest right leaf nodes in a list. There can be more than one right leaf which are deepest and at same level. 

 */

package trees;

/**
 * Created by poorvank on 6/16/15.
 */

class ListNode {
    
    int info;
    ListNode next;
    
    public ListNode(int n) {
        this(n,null);
    }
    public ListNode(int n,ListNode l) {
        info = n;
        next = l;
    }
    
}

public class ConnectDeepestRightLeaf {
    
    private static ListNode head = null;
    private static int max = 0;
    
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right = new Node(4);
        root.right.right = new Node(6);
        
        findRightLeaf(root,0,true);

        ListNode current = head;
        while (current!=null) {
            System.out.print(current.info + " ");
            current = current.next;
        }
        
        
    }
    
    private static void findRightLeaf(Node root,int level,boolean isRight) {
        
        if(root==null) {
            return;
        }
        
        if((root.left==null && root.right==null) && isRight) {
            
            if(max<level) {
                max = level;
                head = new ListNode(root.info);
            }
            else if(level==max) {
                if(head!=null) {
                    ListNode node = new ListNode(root.info);
                    head.next = node;
                    node.next=null;
                }
                else {
                    head = new ListNode(root.info);
                }
            }
            
        }

        findRightLeaf(root.left,level+1,false);
        findRightLeaf(root.right,level+1,true);
        
    }
    
}


/*

Check deepest leaf node

 */