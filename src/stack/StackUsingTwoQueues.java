/*

Implement Stack using Queues
The problem is opposite of this post. We are given a Queue data structure that supports standard operations like
enqueue() and dequeue().
We need to implement a Stack data structure using only instances of Queue.

 */

package stack;

import java.util.LinkedList;

/**
 * Created by poorvank on 8/10/15.
 */

class StackQueue {

    public LinkedList<Integer> q1 = new LinkedList<>();
    public LinkedList<Integer> q2 = new LinkedList<>();

    public void push(int x) {

        q2.addLast(x);

        while (!q1.isEmpty()) {
            int a = q1.removeFirst();
            q2.addLast(a);
        }

        LinkedList<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

    }

    public int pop() {

        if (!q1.isEmpty()) {
            return q1.removeFirst();
        }

        return -1;
    }
}

public class StackUsingTwoQueues {

    public static void main(String[] args) {

        StackQueue sq = new StackQueue();
        sq.push(3);
        sq.push(4);
        sq.push(5);
        sq.push(6);

        System.out.println(sq.pop());
        System.out.println(sq.pop());


    }

}

/*

A stack can be implemented using two queues. Let stack to be implemented be ‘s’ and queues 
used to implement be ‘q1′ and ‘q2′. Stack ‘s’ can be implemented in two ways:

Method 1 (By making push operation costly)
This method makes sure that newly entered element is always at the front of ‘q1′, so that pop operation 
just dequeues from ‘q1′. ‘q2′ is used to put every new element at front of ‘q1′.

push(s, x) // x is the element to be pushed and s is stack
  1) Enqueue x to q2
  2) One by one dequeue everything from q1 and enqueue to q2.
  3) Swap the names of q1 and q2 
// Swapping of names is done to avoid one more movement of all elements 
// from q2 to q1. 

pop(s)
  1) Dequeue an item from q1 and return it.
Method 2 (By making pop operation costly)
In push operation, the new element is always enqueued to q1. In pop() operation, if q2 is empty then all 
the elements except the last, are moved to q2. Finally the last element is dequeued from q1 and returned.

push(s,  x)
  1) Enqueue x to q1 (assuming size of q1 is unlimited).

pop(s)  
  1) One by one dequeue everything except the last element from q1 and enqueue to q2.
  2) Dequeue the last item of q1, the dequeued item is result, store it.
  3) Swap the names of q1 and q2
  4) Return the item stored in step 2.
// Swapping of names is done to avoid one more movement of all elements 
// from q2 to q1.

 */