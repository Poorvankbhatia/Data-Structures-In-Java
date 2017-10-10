/*
Given a tree, if query a leaf node, return the next leaf node.
If the queried node is an internal node, return whatever you want. You can define the node structure. (Not a binary tree)

Example:

            a
        z        x
   w  z  y      o   b
If I query 'z' return 'y'. If I query 'y', return 'o'. If I query 'b', return null.
 */
package trees.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 30/09/17.
 */
public class ReturnNextLeafNode {

    private static class Node {
        String value;
        Node parent;
        List<Node> children;

        public Node(String value, Node parent) {
            this.value = value;
            this.parent = parent;
            children = new ArrayList<>();
        }

        public Node(String value) {
            this.value = value;
            this.parent=null;
            this.children = new ArrayList<>();
        }

        public Node(String value, Node parent, List<Node> children) {
            this.value = value;
            this.parent = parent;
            this.children = children;
        }
    }

    public Node nextLeafNode(Node n) {

        if(n==null) {
            return null;
        }

        if(n.children!=null && n.children.size()>0) {
            //return the first leaf node.
            Node result = null;
            while (true) {
                result = n;
                if(n.children.size()>0) {
                    for (Node child : n.children) {
                        if (child != null) {
                            n = child;
                            break;
                        }
                    }
                }else{
                    return result;
                }
            }
        } else {
            // Leaf Node
            Node parent = n.parent;
            while (parent!=null) {
                Node next = getNextChild(parent,n);
                if(next!=null) {
                    Node result = null;
                    // In cas the next child has children we need to continue it.
                    while (true) {
                        result = next;
                        if(next.children!=null && next.children.size()>0) {
                            for (Node child: next.children) {
                                if(child!=null) {
                                    next = child;
                                    break;
                                }
                            }
                        } else {
                            return result;
                        }
                    }
                } else {
                    n = parent;
                    parent = parent.parent;
                }
            }
        }

        return null;

    }

    private Node getNextChild(Node parent,Node currentChild) {
        List<Node> children = parent.children;
        int index = children.indexOf(currentChild);
        if(index==children.size()-1) {
            return null;
        } else {
            return children.get(index+1);
        }
    }

    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeI = new Node("I");
        Node nodeK = new Node("K");
        Node nodeL = new Node("L");
        Node nodeM = new Node("M");
        Node nodeN = new Node("N");
        Node nodeO = new Node("O");

        nodeB.parent = nodeA;
        nodeC.parent = nodeA;
        nodeA.children.add(nodeB);
        nodeA.children.add(nodeC);

        nodeD.parent = nodeB;
        nodeE.parent = nodeB;
        nodeF.parent = nodeB;
        nodeB.children.add(nodeD);
        nodeB.children.add(nodeE);
        nodeB.children.add(nodeF);

        nodeD.parent = nodeC;
        nodeC.children.add(nodeD);

        nodeG.parent = nodeD;
        nodeH.parent = nodeD;
        nodeI.parent = nodeD;
        nodeD.children.add(nodeG);
        nodeD.children.add(nodeH);
        nodeD.children.add(nodeI);

        nodeK.parent = nodeE;
        nodeL.parent = nodeE;
        nodeM.parent = nodeE;
        nodeE.children.add(nodeK);
        nodeE.children.add(nodeL);
        nodeE.children.add(nodeM);

        nodeN.parent = nodeF;
        nodeO.parent = nodeF;
        nodeF.children.add(nodeN);
        nodeF.children.add(nodeO);

        ReturnNextLeafNode r = new ReturnNextLeafNode();
        System.out.println(r.nextLeafNode(nodeD).value);
        System.out.println(r.nextLeafNode(nodeK).value);
        System.out.println(r.nextLeafNode(nodeM).value);
    }

}
