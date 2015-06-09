package Trees;

/**
 * Created by poorvank on 6/9/15.
 */
public class IdenticalTrees2 {
    
    public static void main(String[] args) {
        
        int[] a = new int[]{8, 3, 6, 1, 4, 7, 10, 14, 13};
        int[] b = new int[]{8, 10, 14, 3, 6, 4, 1, 7, 13};
        
        if(a.length==b.length) {
            System.out.println(isIdentical(a,b,a.length,0,0,Integer.MIN_VALUE,Integer.MAX_VALUE));
        } 
        else {
            System.out.println(false);
        }
        
    }
    
    private static boolean isIdentical(int[] a,int[] b,int n,int i1,int i2,int min,int max) {
        
        int j,k;
        
        for (j=i1;j<n;j++) {
            if(a[j]<max && a[j]>min)
                break;
        }
        
        for (k=i2;k<n;k++) {
            if(b[k]<max && b[k]>min)
               break;
        }
       
        if(k==n && j==n) {
            return true;
        }
        
        if(((k==n)^(j==n)) || a[j]!=b[k]) {
            return false;
        }
       
        return ((isIdentical(a,b,n,j+1,k+1,a[j],max)) && (isIdentical(a,b,n,j+1,k+1,min,a[j])));
    }
    
}
