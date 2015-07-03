package treesPrograms;

/**
 * Created by poorvank on 6/9/15.
 */
public class IdenticalTrees2 {

    public static void main(String[] args) {

        int[] a = new int[]{8, 3, 6, 1, 4, 7, 10, 14, 13};
        int[] b = new int[]{8, 10, 14, 3, 6, 4, 1, 7, 13};

        if (a.length == b.length) {
            System.out.println(isIdentical(a, b, a.length, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE));
        } else {
            System.out.println(false);
        }

    }


    private static boolean isIdentical(int[] a, int[] b, int n, int i1, int i2, int min, int max) {

        int j, k;

        for (j = i1; j < n; j++) {
            if (a[j] < max && a[j] > min)
                break;
        }

        for (k = i2; k < n; k++) {
            if (b[k] < max && b[k] > min)
                break;
        }

        if (k == n && j == n) {
            return true;
        }
        
        
        /* Return false if any of the following is true
      a) If the parent element is leaf in one array, but non-leaf in other.
      b) The elements satisfying constraints are not same. We either search
         for left child or right child of the parent element (decinded by min
         and max values). The child found must be same in both arrays */
        if (((k == n) ^ (j == n)) || a[j] != b[k]) {
            return false;
        }

        return ((isIdentical(a, b, n, j + 1, k + 1, a[j], max)) && (isIdentical(a, b, n, j + 1, k + 1, min, a[j])));
    }

}


/*

According to BST property, elements of left subtree must be smaller and 
elements of right subtree must be greater than root.
Two arrays represent same BST if for every element x, the elements in left and right subtrees of x appear after it
in both arrays. 
And same is true for roots of left and right subtrees.
The idea is to check of if next smaller and greater elements are same in both arrays. Same properties are 
recursively checked for left and right subtrees. The idea looks simple, but implementation requires checking all 
conditions for all elements. Following is an interesting recursive implementation of the idea.

 */