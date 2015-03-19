package Trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class customNode1 {

    public int info;
    public customNode2 left;
    public customNode2 right;
    public int vd;

    public customNode1(int info, int vd) {
        this(info, null, null,vd);
    }

    public customNode1(int info, customNode2 left, customNode2 right, int vd) {
        this.info = info;
        this.left = left;
        this.right = right;
        this.vd = vd;
    }

}

class customTree1 {

    public customNode2 root;

    public customTree1() {
        root = null;
    }

    public static customNode2 search(int n, customNode2 root) {

        if (root.info == n) {
            return root;
        }

        if (root.info < n) {
            return search(n, root.right);
        }

        if (root.info > n) {
            return search(n, root.left);
        }

        return null;

    }

    public customNode2 customInsert(int info, customNode2 root) {
        if (root == null) {
            root = new customNode2(info,0);
        } else if (info < root.info) {
            root.left = customInsert(info, root.left);
        } else if (info > root.info) {
            root.right = customInsert(info, root.right);
        } else {
            System.out.println("Node already present");
        }
        return root;
    }
    
}

class customInput1 {

    public static customNode2 treeInput() {

        System.out.println("Creating binary tree\n");
        System.out.println("Enter nodes(6) : ");
        customNode2 root = null;
        customTree2 tree = new customTree2();

        for (int i = 0; i <= 5; i++) {
            root = tree.customInsert(Integer.parseInt((new Scanner(System.in)).nextLine()), root);
        }

        System.out.println("Root is = " + root.info);

        return root;

    }

}


public class DiagonalSum {
    
    private static HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
    
    public static void main(String[] args) {
        
        customNode2 root = customInput2.treeInput();
        fillDiagonalMap(root);
        System.out.println(map);
        
    }
    
    public static void fillDiagonalMap(customNode2 root) {

        Queue<customNode2> queue = new LinkedList<customNode2>();
        
        queue.add(root);
        
        while (!queue.isEmpty()) {
            
            customNode2 current =  queue.remove();
            
            int vd = current.hd;
            
            while (current!=null) {
                int prevSum = (map.get(vd)==null) ? 0 : map.get(vd);
                map.put(vd,prevSum + current.info);


                // If for any node the left child is not null add
                // it to the queue for future processing.
                
                if(current.left!=null) {
                    current.left.hd = vd + 1;
                    queue.add(current.left);
                }
                
                current = current.right; 
                
            }
            
        }
        
        
    }
    
}

/*
The idea is to keep track of vertical distance from top diagonal passing through root. 
We increment the vertical distance we go down to next diagonal.
1. Add root with vertical distance as 0 to the queue.
2. Process the sum of all right child and right of right child and so on.
3. Add left child current node into the queue for later processing. 
The vertical distance of left child is vertical distance of current node plus 1.
4. Keep doing 2nd, 3rd and 4th step till the queue is empty.

 */