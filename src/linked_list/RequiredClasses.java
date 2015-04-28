package linked_list;

import java.util.Scanner;

/**
 * Created by poorvank on 4/28/15.
 */
class LLNode {
    
    int info;
    LLNode link;
    
    public LLNode(int info) {
        this(info,null);
    }
    public LLNode(int info,LLNode link) {
        this.info = info;
        this.link = link;
    }
    
}

class LinkedList {
    
    public LLNode head;
    
    public LinkedList() {
        head = null;
    }
    
    
    //Make last node head
    public static LLNode add(int n,LLNode node) {
        
        if(node==null) {
            node = new LLNode(n);
        }
        else {
            node = new LLNode(n,node);
        }
        
        return node;
    }
    
    public static LLNode reverse(LLNode node) {
        
        LLNode prev=null,current=node,next=null;
        
        while (current!=null) {
            
            next = current.link;
            current.link = prev;
            prev = current;
            current = next;
            
        }
        
        node = prev;
        
        return node;
        
    }
    
    public static boolean compare(LLNode head1,LLNode head2) {
        
        while (head1!=null && head2!=null) {
            if(head1.info!=head2.info) {
                return false;
            }
            head1 = head1.link;
            head2 = head2.link;
        }
        
        if(head1==null && head2==null) {
            return true;
        }
        
        return false;
    }
    
    public static void printList(LLNode node) {
        LLNode current = node;
        while (current!=null) {
            System.out.print(current.info + " ");
            current = current.link;
        }
    }
    
}

class Input {
    
    public static LLNode listInput() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of nodes ");
        int n = scanner.nextInt();
        System.out.println("Enter nodes ");
        LinkedList linkedList = new LinkedList();
        LLNode root = linkedList.head;
        for (int i=0;i<n;i++) {
            int a = scanner.nextInt();
            root = linkedList.add(a,root);
        }
        
        return root;
    }
    
}
