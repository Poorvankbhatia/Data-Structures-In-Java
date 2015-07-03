package treesPrograms;

/**
 * Created by poorvank on 3/25/15.
 */
public class LowestCommonAncestor1 {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("lca of 6 and 11 is - " + LCA1(root, 6, 11).info);

    }

    private static Node LCA1(Node root, int k1, int k2) {

        if (root == null) {
            return null;
        }

        if (root.info == k1 || root.info == k2) {
            return root;
        }

        root.left = LCA1(root.left, k1, k2);
        root.right = LCA1(root.right, k1, k2);

        if (root.right != null && root.left != null) {
            return root;
        }

        return root.left != null ? root.left : root.right;

    }


}


/*

If we assume that the keys n1 and n2 are present in Binary Tree, we can find LCA using single traversal
 of Binary Tree and without extra storage for path arrays.
The idea is to traverse the tree starting from root. If any of the given keys (n1 and n2) matches with
 root, then root is LCA (assuming that both keys are present). If root doesn’t match with any of the keys,
  we recur for left and right subtree. The node which has one key present in its left subtree and the 
  other key present in right subtree is the LCA. If both keys lie in left subtree, then left subtree has LCA also,
  otherwise LCA lies in right subtree.

 */