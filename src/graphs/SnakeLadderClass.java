/*

Snake and Ladder Problem
Given a snake and ladder board, find the minimum number of dice throws required to reach the 
destination or last cell from source or 1st cell.
 Basically, the player has total control over outcome of dice throw and wants to find out minimum 
 number of throws required to reach last cell.

If the player reaches a cell which is base of a ladder, the player has to climb up that ladder and if 
reaches a cell is mouth of the snake, has to go down to the tail of snake without a dice throw.

 */

package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 1/21/16.
 */

class Cell {

    int vertexToConsider;
    int moves;

    public Cell() {
    }

    public Cell(int vertex, int moves) {
        this.vertexToConsider = vertex;
        this.moves = moves;
    }
}

public class SnakeLadderClass {


    private static int getMinimumDiceThrows(int[] Moves, int n) {

        Queue<Cell> queue = new LinkedList<>();

        queue.add(new Cell(0, 0));
        boolean[] visited = new boolean[n];
        visited[0] = true;

        while (!queue.isEmpty()) {

            int v = queue.peek().vertexToConsider;

            if (v == n - 1) {
                break;
            }

            Cell startCell = queue.remove();

            for (int j = (v + 1); j <= (v + 6) && j < n; j++) {

                if (!visited[j]) {

                    Cell cell = new Cell();
                    cell.moves = startCell.moves + 1;
                    visited[j] = true;

                    if (Moves[j] != -1) {
                        cell.vertexToConsider = Moves[j];
                    } else {
                        cell.vertexToConsider = j;
                    }

                    queue.add(cell);

                }

            }

        }

        return queue.peek().moves;


    }

    public static void main(String[] args) {

        int N = 30;
        int[] moves = new int[N];
        for (int i = 0; i < N; i++)
            moves[i] = -1;

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        System.out.println(getMinimumDiceThrows(moves, N));

    }


}

/*

Time complexity of the above solution is O(N) as every cell is added and removed only once from queue.
And a typical enqueue or dequeue operation takes O(1) time.

The idea is to consider the given snake and ladder board as a directed graph with number of vertices equal to the 
number of cells in the board. The problem reduces to finding the shortest path in a graph. Every vertex of the graph 
has an edge to next six vertices if next 6 vertices do not have a snake or ladder. If any of the next six vertices 
has a snake or ladder, then the edge from current vertex goes to the top of the ladder or tail of the snake. Since 
all edges are of equal weight, we can efficiently find shortest path using Breadth First Search of the graph.

The input is represented by two things, first is ‘N’ which is number of cells in the given board, second is an 
array ‘move[0…N-1]’ of size N. An entry move[i] is -1 if there is no snake and no ladder from i, otherwise move[i] 
contains index of destination cell for the snake or the ladder at i.

 */