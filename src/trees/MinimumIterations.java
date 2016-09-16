/*

Minimum no. of iterations to pass information to all nodes in the tree
Given a very large n-ary tree. Where the root node has some information which it wants to pass to all of its
children down to the leaves with the constraint that it can only pass the information to one of its children 
at a time (take it as one iteration).

 */

package trees;

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
