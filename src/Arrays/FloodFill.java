package arrays;

/**
 * Created by poorvank on 6/8/15.
 */
public class FloodFill {
    
    public static void main(String[] args) {
        
        int[][] screen = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1},
                                     {1, 1, 1, 1, 1, 1, 0, 0},
                                     {1, 0, 0, 1, 1, 0, 1, 1},
                                     {1, 2, 2, 2, 2, 0, 1, 0},
                                     {1, 1, 1, 2, 2, 0, 1, 0},
                                     {1, 1, 1, 2, 2, 2, 2, 0},
                                     {1, 1, 1, 1, 1, 2, 1, 1},
                                     {1, 1, 1, 1, 1, 2, 2, 1},
                                    };
        
        int newC = 3,prevC = 2;
        
        aboveAlgorithm(screen,newC,prevC,4,4);

        for (int i=0; i<screen.length; i++)
        {
            for (int j=0; j<screen[0].length; j++)
                System.out.print(screen[i][j] + " ");
            System.out.println();
        }
        
    }
    
    private static void aboveAlgorithm(int[][] screen,int newC,int prevC,int x,int y) {
        
        int row = screen.length;
        int col = screen[0].length;
        
        if(x<0 || x>=row || y<0 || y>=col) {
            return;
        }
        if(screen[x][y]!=prevC) {
            return;
        }
        
        
        screen[x][y] = newC;
        
        aboveAlgorithm(screen,newC,prevC,x+1,y);
        aboveAlgorithm(screen,newC,prevC,x-1,y);
        aboveAlgorithm(screen,newC,prevC,x,y+1);
        aboveAlgorithm(screen,newC,prevC,x,y-1);
        
        
        
        
    }
    
}
