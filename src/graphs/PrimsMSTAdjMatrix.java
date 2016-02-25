package graphs;

/**
 * Created by poorvank on 2/25/16.
 */
public class PrimsMSTAdjMatrix {
    
    private static int INF = Integer.MIN_VALUE;
    
    private int vertexCount;
    
    public PrimsMSTAdjMatrix(int vertexCount) {
        this.vertexCount = vertexCount;
    }
    
    public int minKey(int[] keyArray,boolean[] presentInMst) {
        
        int minValue=Integer.MIN_VALUE,minIndex=-1;
        
        for (int v=0;v<vertexCount;v++) {
            
            if(!presentInMst[v] && keyArray[v]<minValue) {
                minValue = keyArray[v];
                minIndex = v;
            }
            
        }
        
        return minIndex;
    }
    
    public void mst(int[][] matrix) {
        
        // Used in printing MST
        int[] parent = new int[vertexCount];
        
        int[] keyArray = new int[vertexCount];
        
        boolean[] presentInMst = new boolean[vertexCount];
        
        for (int i=0;i<vertexCount;i++) {
            keyArray[i] = INF;
        }
        
        keyArray[0] = 0;
        parent[0] = -1;
        
        int e=0;
        
        //Number of edges is always equal to vertexCount-1 in MST
        while (e<vertexCount-1) {
            
            int minIndex = minKey(keyArray,presentInMst);
            
            
        }
        
    }
    
    public static void main(String[] args) {
        
        int[][] matrix = new int[][] {{0, 2, 0, 6, 0},
                                      {2, 0, 3, 8, 5},
                                      {0, 3, 0, 0, 7},
                                      {6, 8, 0, 0, 9},
                                      {0, 5, 7, 9, 0}};
        
        PrimsMSTAdjMatrix prims = new PrimsMSTAdjMatrix(matrix.length);
        
        
    }
}

