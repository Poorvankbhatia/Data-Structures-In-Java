/*

Every node of Interval Tree stores following information.
a) i: An interval which is represented as a pair [low, high]
b) max: Maximum high value in subtree rooted with this node.

 */

package Trees;

/**
 * Created by poorvank on 3/31/15.
 */

class Interval {
    
    public int high;
    public int low;
    
    public Interval(int low,int high) {
    
        this.high = high;
        this.low =low;
    
    }
    
    @Override
    public String toString() {
        return ("Interval overlap found is - {" + this.low + "," + this.high + "}");
    }
    
}

class IntervalNode {
    
    public Interval interval;
    public int max;
    public IntervalNode left;
    public IntervalNode right;
    
    public IntervalNode(Interval i) {
        
        this.interval = i;
        max = i.high;
        left = null;
        right = null;
        
    }
    
}

class IntervalTree {
    
    public IntervalNode root;
    
    public IntervalTree() {
        root = null;
    }
    
    public IntervalNode insert (IntervalNode node , Interval i) {
        
        if(node == null) {
            return new IntervalNode(i);
        }
        
        else if(i.low < node.interval.low) {
            node.left = insert(node.left,i);
        }
        
        else if(i.low > node.interval.low) {
            node.right = insert(node.right,i);
        }
        
        if(node.max < i.high) {
            node.max = i.high;
        }
        
        return node;
    }
    
    private boolean doOverlap(Interval i1,Interval i2) {
        
        if(i1.low <= i2.high && i1.high >= i2.low) {
            return true;
        }
        
        return false;
        
    }
    
    public Interval search(IntervalNode node,Interval i) {
        
        if(node==null) {
            return null;
        }
        
        if(doOverlap(i,node.interval)) {
            return node.interval;
        }

        // If left child of root is present and max of left child is
        // greater than or equal to given interval, then i may
        // overlap with an interval is left subtree
        
        else if (node.left!=null && i.high <= node.left.max) {
            return search(node.left,i);
        }
        
        else {
            return search(node.right,i);
        }

        
    }
    
}
 

public class IntervalTreeImplementation {
    
    public static void main(String[] args) {

        Interval intervals[] = new Interval[]{new Interval(15,20), new Interval(10, 30), new Interval(17, 19), new Interval(5, 20),
                new Interval(12, 15), new Interval(30, 40)};
        
        IntervalTree tree = new IntervalTree();
        
        for (Interval i : intervals) {
            tree.root = tree.insert(tree.root,i);
        }
        
        Interval find = new Interval(6,7);
        System.out.println(tree.search(tree.root,find).toString());
        
    }
    
}


/*

ALGO : 
The main operation is to search for an overlapping interval. 
Following is algorithm for searching an overlapping interval x in an Interval tree rooted with root.

Interval overlappingIntervalSearch(root, x)
1) If x overlaps with root's interval, return the root's interval.

2) If left child of root is not empty and the max  in left child 
is greater than x's low value, recur for left child

3) Else recur for right child.

Case 1: When we go to right subtree, one of the following must be true.
a) There is an overlap in right subtree: This is fine as we need to return one overlapping interval.
b) There is no overlap in either subtree: We go to right subtree only when either left is NULL or maximum 
value in left is smaller than x.low. So the interval cannot be present in left subtree.

Case 2: When we go to left subtree, one of the following must be true.
a) There is an overlap in left subtree: This is fine as we need to return one overlapping interval.
b) There is no overlap in either subtree: This is the most important part. We need to consider following facts.
… We went to left subtree because x.low <= max in left subtree
…. max in left subtree is a high of one of the intervals let us say [a, max] in left subtree.
…. Since x doesn’t overlap with any node in left subtree x.low must be smaller than ‘a‘.
…. All nodes in BST are ordered by low value, so all nodes in right subtree must have low value greater than ‘a‘.
…. From above two facts, we can say all intervals in right subtree have low value greater than x.low. 
So x cannot overlap with any interval in right subtree.

Do see robert Sedwick intervel trees



 */