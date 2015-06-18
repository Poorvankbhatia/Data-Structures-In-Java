/*

Given a linked list where in addition to the next pointer, each node has a child pointer,
which may or may not point to a separate list.
These child lists may have one or more children of their own, and so on, to produce a
multilevel data structure, as shown in below figure.You are given the head of the first level of the list.
Flatten the list so that all the nodes appear in a single-level linked list. You need to flatten
the list in way that all nodes at first level should come first, then nodes of second level, and so on.

//See images

 */

package linked_list;

/**
 * Created by poorvank on 6/18/15.
 */

class ListNode2 {

    int info;
    ListNode2 next;
    ListNode2 child;

    public ListNode2(int info) {
        this(info,null,null);
    }
    public ListNode2(int info,ListNode2 right,ListNode2 down) {
        this.info = info;
        next = right;
        child = down;
    }


}

public class FlattenLinkList2 {
    
    public static void main(String[] args) {


        ListNode2 head = new ListNode2(10);
        head.next = new ListNode2(5);
        head.next.next = new ListNode2(12);
        head.next.next.next = new ListNode2(7);
        head.next.next.next.next = new ListNode2(11);
        head.child = new ListNode2(4);
        head.child.next = new ListNode2(20);
        head.child.next.next = new ListNode2(13);
        head.child.next.child = new ListNode2(2);
        head.next.next.next.child = new ListNode2(7);
        head.next.next.next.child.next = new ListNode2(16);
        
        head = flatten(head);

        ListNode2 current = head;

        while (current!=null) {
            System.out.print(current.info + " ");
            current = current.next;
        }

    }
    
    private static ListNode2 flatten(ListNode2 head) {
        
        if(head==null) {
            return null;
        }
        
        ListNode2 tmp = null,tail=head;
        
        while (tail.next!=null) {
            tail = tail.next;
        }
        
        ListNode2 curr = head;
        
        while (curr!=null) {
            
            if(curr.child!=null) {
                
                tmp = curr.child;
                tail.next = tmp;
                
                while (tmp.next!=null) {
                    
                    tmp = tmp.next;
                }
                tail = tmp;
            }
            
            curr = curr.next;
            
        }
        
        return head;
        
        
    }
    
}

/*

The problem clearly say that we need to flatten level by level. 
The idea of solution is, we start from first level, process all nodes one by one, 
if a node has a child, then we append the child at the end of list, 
otherwise we don’t do anything. After the first level is processed, 
all next level nodes will be appended after first level. Same process is followed for the appended nodes.

1) Take "cur" pointer, which will point to head of the fist level of the list
2) Take "tail" pointer, which will point to end of the first level of the list
3) Repeat the below procedure while "curr" is not NULL.
    I) if current node has a child then
	a) append this new child list to the "tail"
		tail->next = cur->child
	b) find the last node of new child list and update "tail"
		tmp = cur->child;
		while (tmp->next != NULL)
			tmp = tmp->next;
		tail = tmp;
    II) move to the next node. i.e. cur = cur->next 

 */
