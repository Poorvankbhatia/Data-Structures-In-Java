/*

MICROSOFT INTERVIEW QUESTION

Position of Knight is given on a chessboard.
Return me something (adjacency matrix or list or anything) which shows all
the positions the knight can reach upto from a given position.
I must be able to tell, from what is returned, if the position is reachable or not
and if reachable I must be able to trace the path from given position to target position

 */

package graphs.search;

import utility.Queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 16/08/16.
 */

class ChessCell {

    private int x;
    private int y;
    //Path to reach the cell from the start cell
    List<ChessCell> pathList;

    public ChessCell(int x, int y) {
        this.x = x;
        this.y = y;
        this.pathList = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "("+x+","+y+")";
    }

    public boolean compareAnotherChessCell(ChessCell cell) {

        return this.getX() == cell.getX() && this.getY() == cell.getY();

    }

}

public class KnightPositionChess {

    //Different moves of knight
    private static final int[] XMOVE = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] YMOVE = new int[]{1, 2, 2, 1, -1, -2, -2, -1};

    //The size of the chess board
    private static final int N = 3;
    private ChessCell startCell;
    private List<ChessCell> reachableFromStartCell;

    public KnightPositionChess(int x,int y) {
        startCell = new ChessCell(x,y);
        reachableFromStartCell = findAllReachable();
    }

    public Iterable<ChessCell> findPath(int x,int y) {

        ChessCell newChessCell = new ChessCell(x,y);

        if(reachableFromStartCell.isEmpty()) {
            findAllReachable();
        }

        for (ChessCell cell : reachableFromStartCell) {
            if(cell.compareAnotherChessCell(newChessCell)) {
                return cell.pathList;
            }
        }

        return null;

    }

    public Iterable<ChessCell> getReachableCells() {
        return reachableFromStartCell;
    }

    private List<ChessCell> findAllReachable() {

        startCell.pathList.add(startCell);

        Queue<ChessCell> queue = new Queue<>();
        queue.enqueue(startCell);
        List<ChessCell> reachableCells = new ArrayList<>();

        boolean[][] visited = new boolean[N][N];
        visited[startCell.getX()][startCell.getY()] = true;

        while (!queue.isEmpty()) {

            ChessCell current = queue.dequeue();
            int currentX = current.getX();
            int currentY = current.getY();

            for (int i=0;i<8;i++) {

                int newX = currentX + XMOVE[i];
                int newY = currentY + YMOVE[i];

                if(isValid(newX,newY,visited)) {
                    ChessCell newCell = new ChessCell(newX,newY);
                    //Visited true is marked as soon as the cell is visited, so that it is not visited while bfs of another cell
                    visited[newX][newY] = true;
                    newCell.pathList = new ArrayList<>(current.pathList);
                    newCell.pathList.add(newCell);
                    queue.enqueue(newCell);
                    reachableCells.add(newCell);
                }

            }

        }

        return reachableCells;


    }

    private boolean isValid(int x,int y,boolean[][] visited) {

        return (x>=0 && y>=0 && x<N && y<N && !visited[x][y]);

    }


    public static void main(String[] args) {

        KnightPositionChess kp = new KnightPositionChess(2,0);
        Iterable<ChessCell> iterable = kp.getReachableCells();

        System.out.println("All reachable vertices are : ");
        for (ChessCell coordinates : iterable) {
            System.out.print(coordinates.toString() +  " ");
        }


        System.out.println("path for element 0,2 :  " );
        for (ChessCell coordinates : kp.findPath(0,2)) {
            System.out.print(coordinates.toString() +  " ");
        }

    }

}

/*

Every cell has a path list member function, which contains the entire path from starting cell.
A simple BFS is done that would actually give shortest path to all the other cells.
As soon as a cell is visited, a visited[][] array is marked true.

 */
