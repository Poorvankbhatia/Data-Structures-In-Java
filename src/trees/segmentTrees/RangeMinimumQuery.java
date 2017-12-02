/*

We have an array arr[0 . . . n-1]. We should be able to efficiently find the minimum value
 from index qs (query start) to qe (query end) where 0 <= qs <= qe <= n-1.

 */
package trees.segmentTrees;

/**
 * Created by poorvank on 06/08/17.
 */
public class RangeMinimumQuery {

    private static class SegmentTree {

        int inputSize;
        int[] tree;

        public SegmentTree(int[] nums) {
            inputSize = nums.length;
            int size = (int)(Math.ceil(Math.log(inputSize)/Math.log(2)));
            int maxSize = (int) (2*Math.pow(2,size))-1;
            tree = new int[maxSize];
            constructTree(0,inputSize-1,0,nums);
        }

        private int getMid(int start,int end) {
            return start + (end-start)/2;
        }

        private int constructTree(int start,int end,int pos,int[] nums) {

            if(start==end) {
                tree[pos] = nums[start];
                return tree[pos];
            }

            int mid = getMid(start,end);
            tree[pos] =  Math.min(constructTree(start,mid,(2*pos+1),nums),constructTree(mid+1,end,(2*pos+2),nums));
            return tree[pos];
        }

        public int getMin(int qStart,int qEnd,int[] nums) {

            if(qStart>=inputSize || qEnd<0) {
                System.out.println("Wrong input");
                return -1;
            }

            return getMinUtil(0,inputSize-1,qStart,qEnd,0);

        }

        private int getMinUtil(int start,int end,int qStart,int qEnd,int pos) {

            if(qEnd>=end && qStart<=start) {
                return tree[pos];
            }
            else if(qEnd<start || qStart>end) {
                return Integer.MAX_VALUE;
            }

            int mid = getMid(start,end);
            return Math.min(getMinUtil(start,mid,qStart,qEnd,(2*pos+1)),getMinUtil(mid+1,end,qStart,qEnd,(2*pos+2)));

        }

    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 2, 7, 9, 11};
        SegmentTree tree = new SegmentTree(arr);

        System.out.println("Minimum value - " + tree.getMin(1,5,arr));

    }


}

/*

Segment tree can be used to do preprocessing and query in moderate time. With segment tree, preprocessing time is O(n)
and time to for range minimum query is O(Logn). The extra space required is O(n) to store the segment tree.

Representation of Segment trees
1. Leaf Nodes are the elements of the input array.
2. Each internal node represents minimum of all leaves under it.

Time Complexity for tree construction is O(n). There are total 2n-1 nodes,
and value of every node is calculated only once in tree construction.

Time complexity to query is O(Logn). To query a range minimum, we process at most two nodes at every level and number of levels is O(Logn).

 */
