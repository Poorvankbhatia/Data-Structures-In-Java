package dyanamicprogramming.matrix;

import java.util.HashMap;

/**
 * Created by poorvank on 03/01/17.
 */
public class PalindromicPaths {

    private HashMap<Cells,Integer> map;

    public PalindromicPaths() {
        map = new HashMap<>();
    }

    private class Cells {

        int startX,startY;
        int endX,endY;

        public Cells(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if((obj == null) || (obj.getClass() != this.getClass()))
                return false;
            // object must be Test at this point
            Cells test = (Cells) obj;
            return startY == test.startY && startX == test.startX && endX == test.endX && endY==test.endY;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 7 * hash + this.startX;
            hash = 7 * hash + this.startY;
            hash = 7 * hash + this.endX;
            hash = 7 * hash + this.endY;
            return hash;
        }


    }

    public int count(char[][] matrix,int startX,int startY,int endX,int endY,int rows,int cols) {

        if(startX<0 || startY>=cols || startX>=rows || startY<0) {
            return 0;
        }

        if(endX<0 || endY>=cols || endX>=rows || endY<0) {
            return 0;
        }

        if(matrix[startX][startY]!=matrix[endX][endY]) {
            return 0;
        }

        Cells cells = new Cells(startX,startY,endX,endY);

        if(startX==endX && startY==endY) {
            return 1;
        }

        if((map.containsKey(cells))) {
            return map.get(cells);
        }

        int result = 0;

        result += count(matrix,startX+1,startY,endX,endY-1,rows,cols);

        result += count(matrix,startX,+startY,endX,endY-1,rows,cols);

        result += count(matrix,startX,startY+1,endX-1,endY,rows,cols);

        result += count(matrix,startX,startY+1,endX,endY-1,rows,cols);

        map.put(cells,result);

        return result;

    }

    public static void main(String[] args) {

        char[][] mat = new char[][] {
                {'a', 'b', 'b'},
                {'b', 'b', 'a'}
        };

        int row = mat.length;
        int col = mat[0].length;

        System.out.print(new PalindromicPaths().count(mat,0,0,row-1,col-1,row,col));

    }

}
