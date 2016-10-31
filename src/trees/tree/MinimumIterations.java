/*

Minimum no. of iterations to pass information to all nodes in the tree
Given a very large n-ary tree. Where the root node has some information which it wants to pass to all of its
children down to the leaves with the constraint that it can only pass the information to one of its children 
at a time (take it as one iteration).

 */

package trees.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank on 4/19/15.
 */



class MinIterationTree {

    private class MinIterationNode {

        public int info;
        public List<Integer> childList;

        public MinIterationNode(int info) {
            this.info = info;
            childList = new ArrayList<>();
        }

    }

    public MinIterationNode root;
    public List<MinIterationNode> nodeList;

    public MinIterationTree(int n, int count) {

        root = new MinIterationNode(n);
        nodeList = new ArrayList<>();
        nodeList.add(root);
        for (int i = 1; i < count; i++) {
            nodeList.add(new MinIterationNode(i));
        }

    }

    public MinIterationNode addChild(int parent, int child) {
        // System.out.println(parent + " " + child);
        nodeList.get(parent).childList.add(child);
        return root;
    }


    public int minIterations(MinIterationNode node) {

        //This is the base case , if node has no children its iterations are 0;
        if (node == null || node.childList == null) {
            return 0;
        }

        int result = 0;
        //Array of minimum iterations
        int[] minIterationsRequired = new int[node.childList.size()];

        //loop over all children of node
        for (int i = 0; i < node.childList.size() - 1; i++) {
            minIterationsRequired[i] = minIterations(nodeList.get(node.childList.get(i)));
        }

        //To make sure maximum no of nodes participate in info passing in any iteration, parent should 1st pass info to that 
        //child who will take maximum iteration to pass info further down in subsequent iterations. i.e. in any iteration, 
        //parent should choose the child who takes maximum iteration later on
        Arrays.sort(minIterationsRequired);
        //if a node has children minimum iterations would be equivalent to the count of children
        int minSteps = node.childList.size();

        //starting with max iteration
        for (int i = minIterationsRequired.length - 1; i >= 0; i--) {

            if (minSteps < minIterationsRequired[i]) {
                //If a child node i takes ci iterations to pass info below its subtree, 
                //then its parent will take (ci + 1) iterations to pass info to subtree rooted at that child i.
                minSteps = minIterationsRequired[i] + 1;
            }
        }

        result = minSteps;

        return result;

    }

}

public class MinimumIterations {

    public static void main(String[] args) {

        MinIterationTree tree = new MinIterationTree(0, 5);
        tree.addChild(0, 1);
        tree.addChild(0, 2);
        tree.addChild(1, 3);
        tree.addChild(1, 4);

        System.out.println("Minimum number of iterations = " + tree.minIterations(tree.root));


        MinIterationTree tree1 = new MinIterationTree(0, 17);
        tree1.addChild(0, 1);
        tree1.addChild(0, 2);
        tree1.addChild(0, 3);
        tree1.addChild(0, 4);
        tree1.addChild(0, 5);
        tree1.addChild(0, 6);

        tree1.addChild(1, 7);
        tree1.addChild(1, 8);
        tree1.addChild(1, 9);

        tree1.addChild(4, 10);
        tree1.addChild(4, 11);

        tree1.addChild(6, 12);

        tree1.addChild(7, 13);
        tree1.addChild(7, 14);
        tree1.addChild(10, 15);
        tree1.addChild(11, 16);


        System.out.println("Minimum number of iterations = " + tree1.minIterations(tree1.root));

    }

}

/*

This can be done using Post Order Traversal. The idea is to consider height and children count on each and every node.
If a child node i takes ci iterations to pass info below its subtree, then its parent will take (ci + 1) iterations to pass info to subtree
 rooted at that child i.
If parent has more children, it will pass info to them in subsequent iterations. Let’s say children of a parent takes c1, c2, c3, c4, …, cn
iterations to pass info in their own subtree, Now parent has to pass info to these n children one by one in n iterations. If parent picks child
 i in ith iteration, then parent will take (i + ci) iterations to pass info to child i and all it’s subtree.
In any iteration, when parent passes info a child i+1, children (1 to i) which got info from parent already in previous iterations, will pass
 info to further down in subsequent iterations, if any child (1 to i) has its own child further down.
To pass info to whole tree in minimum iterations, it needs to be made sure that bandwidth is utilized as efficiently as possible (i.e. maximum
 passable no of nodes should pass info further down in any iteration)
The best possible scenario would be that in nth iteration, n different nodes pass info to their child.
Nodes with height = 0: (Trivial case) Leaf node has no children (no information passing needed), so no of iterations would be ZERO.
Nodes with height = 1: Here node has to pass info to all the children one by one (all children are leaf node, so no more information passing
 further down). Since all children are leaf, node can pass info to any child in any order (pick any child who didn’t receive the info yet).
 One iteration needed for each child and so no of iterations would be no of children.So node with height 1 with n children will take n iterations.
Take a counter initialized with ZERO, loop through all children and keep incrementing counter.
Nodes with height > 1: Let’s assume that there are n children (1 to n) of a node and minimum no iterations for all n children are c1, c2, …., cn.
To make sure maximum no of nodes participate in info passing in any iteration, parent should 1st pass info to that child who will take maximum
iteration to pass info further down in subsequent iterations. i.e. in any iteration, parent should choose the child who takes maximum iteration
 later on. It can be thought of as a greedy approach where parent choose that child 1st, who needs maximum no of iterations so that all subsequent
  iterations can be utilized efficiently.
If parent goes in any other fashion, then in the end, there could be some nodes which are done quite early, sitting idle and so bandwidth is not
 utilized efficiently in further iterations.
If there are two children i and j with minimum iterations ci and cj where ci > cj, then If parent picks child j 1st then no of iterations needed
by parent to pass info to both children and their subtree would be:max (1 + cj, 2 + ci) = 2 + ci
If parent picks child i 1st then no of iterations needed by parent to pass info to both children and their subtree would be: max(1 + ci, 2 + cj) = 1 + ci (So picking ci gives better result than picking cj)

This tells that parent should always choose child i with max ci value in any iteration.
SO here greedy approach is:
sort all ci values decreasing order,
let’s say after sorting, values are c1 > c2 > c3 > …. > cn
take a counter c, set c = 1 + c1 (for child with maximum no of iterations)
for all children i from 2 to n, c = c + 1 + ci
then total no of iterations needed by parent is max(n, c)

Let minItr(A) be the minimum iteration needed to pass info from node A to it’s all the sub-tree. Let child(A) be the count of all children for
node A. So recursive relation would be:

1. Get minItr(B) of all children (B) of a node (A)
2. Sort all minItr(B) in descending order
3. Get minItr of A based on all minItr(B)
    minItr(A) = child(A)
    For children B from i = 0 to child(A)
             minItr(A) = max ( minItr(A), minItr(B) + i + 1)

Base cases would be:
If node is leaf, minItr = 0
If node's height is 1, minItr = children count

 */