/*

Segment Tree | Set 1 (Sum of given range)
Let us consider the following problem to understand Segment Trees.

We have an array arr[0 . . . n-1]. We should be able to
1. Find the sum of elements from index l to r where 0 <= l <= r <= n-1

2. Change value of a specified element of the array to a new value x. We need to do arr[i] = x where 0 <= i <= n-1.



 */
package trees.segmentTrees;

/**
 * Created by poorvank on 05/08/17.
 */
public class SumRange {

    private static class SegmentTree {

        int[] tree;
        int inputSize = 0;

        public SegmentTree(int[] arr) {
            inputSize = arr.length;
            int size = (int) Math.ceil(Math.log(inputSize)/Math.log(2));
            int maxSize = 2*((int) Math.pow(2,size))-1;
            tree = new int[maxSize];
            constructTree(arr,tree,0,0,inputSize-1);
        }


        private int getMid(int low,int high) {
            return (low) + (high-low)/2;
        }

        private int constructTree(int[] arr,int[] tree,int pos,int start,int end) {

            if(start==end) {
                tree[pos] = arr[start];
                return tree[pos];
            }

            int mid = getMid(start,end);
            int left = constructTree(arr,tree,(2*pos+2),mid+1,end);
            int right = constructTree(arr,tree,(2*pos+1),start,mid);
            tree[pos] = left+right;
            return tree[pos];
        }

        public int getSum(int qStart,int qEnd) {

            if(qStart<0 || qEnd>inputSize) {
                return -1;
            }
            return getSumUtil(0,inputSize-1,qStart,qEnd,0);

        }

        private int getSumUtil(int start,int end,int qStart,int qEnd,int pos) {

            if(qStart<=start && qEnd>=end) {
                return tree[pos];
            }

            if(qStart>end || qEnd<start) {
                return 0;
            }

            // If a part of this segment overlaps with the given range
            int mid = getMid(start,end);
            int left = getSumUtil(start,mid,qStart,qEnd,(2*pos+1));
            int right = getSumUtil(mid+1,end,qStart,qEnd,(2*pos+2));
            return left+right;
        }

        public void updateValue(int index,int val,int[] arr) {

            if (index < 0 || index > inputSize - 1) {
                System.out.println("Invalid Input");
                return;
            }

            int diff = val-arr[index];
            arr[index] = val;

            updateValueUtil(0,diff,0,inputSize-1,index);

        }

        private void updateValueUtil(int pos,int diff,int start,int end,int indexUpdated) {

            if(indexUpdated<start || indexUpdated>end) {
                return;
            }

            tree[pos] += diff;
            // If node is not a leaf node yet
            if(start!=end) {
                int mid = getMid(start,end);
                updateValueUtil((2*pos+1),diff,start,mid,indexUpdated);
                updateValueUtil((2*pos+2),diff,mid+1,end,indexUpdated);
            }

        }

    }

    public static void main(String[] args) {

        int arr[] = {1, 3, 5, 7, 9, 11};
        SegmentTree tree = new SegmentTree(arr);

        System.out.println("Sum of values in given range = " +
                tree.getSum(1,3));

        tree.updateValue(1, 10,arr);

        System.out.println("Updated sum of values in given range = " +
                tree.getSum(1,3));

    }

}


/*

We can use a Segment Tree to do both operations in O(Logn) time.

Representation of Segment trees
1. Leaf Nodes are the elements of the input array.
2. Each internal node represents some merging of the leaf nodes. The merging may be different for different problems.
For this problem, merging is sum of leaves under a node.


An array representation of tree is used to represent Segment Trees. For each node at index i,
the left child is at index 2*i+1, right child at 2*i+2 and the parent is at Math.ciel((i-1)/2)


Construction of Segment Tree from given array
We start with a segment arr[0 . . . n-1]. and every time we divide the current segment into two halves
(if it has not yet become a segment of length 1), and then call the same procedure on both halves, and
for each such segment we store the sum in corresponding node.
All levels of the constructed segment tree will be completely filled except the last level. Also, the tree will be a Full Binary Tree
because we always divide segments in two halves at every level. Since the constructed tree is always full binary tree with n leaves,
there will be n-1 internal nodes. So total number of nodes will be 2*n – 1.



Height of the segment tree will be logn base2
Since the tree is represented using array and relation between parent and child indexes must be maintained,
size of memory allocated for segment tree will be 2*2(logn)-1;


Query for Sum of given range
Once the tree is constructed, how to get the sum using the constructed segment tree. Following is algorithm to get the sum of elements.

int getSum(node, l, r)
{
   if range of node is within l and r
        return value in node
   else if range of node is completely outside l and r
        return 0
   else
    return getSum(node's left child, l, r) +
           getSum(node's right child, l, r)
}

Update a value
Like tree construction and query operations, update can also be done recursively.
We are given an index which needs to updated. Let diff be the value to be added. We start from root of the segment tree,
and add diff to all nodes which have given index in their range.
If a node does not have given index in its range, we don’t make any changes to that node.


Time Complexity for tree construction is O(n). There are total 2n-1 nodes, and value of every node is calculated only once in tree construction.
Time complexity to query is O(Logn). To query a sum, we process at most four nodes at every level and number of levels is O(Logn).
The time complexity of update is also O(Logn). To update a leaf value, we process one node at every level and number of levels is O(Logn).


https://stackoverflow.com/questions/28470692/how-is-the-memory-of-the-array-of-segment-tree-2-2-ceillogn-1

if you have an array of n elements, then the segment tree will have a leaf node for each of these n entries. Thus, we have (n)
leaf nodes, and also (n-1) internal nodes.

Total number of nodes= n + (n-1) = 2n-1 Now, we know its a full binary tree and thus the height is: ceil(Log2(n)) +1

HEIGHT OF A COMPLETE BT WITH N NODES - CEIL(log2(n+1))-1
1 node gives log2(2) = 1
3 nodes gives log2(4) = 2
7 nodes gives log2(8) = 3
15 nodes gives log2(16) = 4
...
EDIT: According to wikipedia, the root node (rather un-intuitively?) does not count in the height, so the formula would be CEIL(log2(n+1))-1.



Total no. of nodes = 2^0 + 2^1 + 2^2 + … + 2^ceil(Log2(n)) // which is a geometric progression where 2^i denotes, the number of nodes at level i.

Formula of summation G.P. = a * (r^size - 1)/(r-1) where a=2^0

Total no. of nodes = 1*(2^(ceil(Log2(n))+1) -1)/(2-1)

= 2* [2^ceil(Log2(n))] -1 (you need space in the array for each of the internal as well as leaf nodes which are this count in number),
thus it is the array of size.

= O(4 * n) approx..

You may also think this way, is the segment tree is this:

    10
   /  \
  3    7
 /\    /\
1  2  3  4
If the above is you segment tree, then you array of segment tree will be: 10,3,7,1,2,3,4 i.e. 0th element will store the sum of 1st and 2nd
entries, 1st entry will store the sum of 3 and 4th and 2nd will store the sum of 5th and 6th entry!!

Also, the better explanation is: if the array size n is a power of 2, then we have exactly n-1 internal nodes, summing up to 2n-1 total nodes.
But not always, we have n as the power of 2, so we basically need the smallest power of n which is greater then n. That means this,

int s=1;
for(; s<n; s<<=1);

 */