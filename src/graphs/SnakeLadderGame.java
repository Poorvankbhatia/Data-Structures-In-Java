/*
 * 
 * Snake and Ladder Problem Given a snake and ladder board, find the minimum number of dice throws
 * required to reach the destination or last cell from source or 1st cell. Basically, the player has
 * total control over outcome of dice throw and wants to find out minimum number of throws required
 * to reach last cell.
 * 
 * If the player reaches a cell which is base of a ladder, the player has to climb up that ladder
 * and if reaches a cell is mouth of the snake, has to go down to the tail of snake without a dice
 * throw.
 */

package graphs;

import java.util.HashMap;
import java.util.Map;

import utility.Queue;


/**
 * Created by poorvank on 1/21/16.
 */

class Cell {

  // Current Vertex/ Vertex to go on to when a Snake/Ladder is visited
  private int id;

  // Number of moves to reach the current Cell
  private int minMovesToReachFromStart;

  public Cell() {}

  public Cell(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMinMovesToReachFromStart() {
    return minMovesToReachFromStart;
  }

  public void setMinMovesToReachFromStart(int minMovesToReachFromStart) {
    this.minMovesToReachFromStart = minMovesToReachFromStart;
  }
}


public class SnakeLadderGame {
  int cellCount;
  Map<Integer, Integer> snakeLadderStartEndMap;

  public SnakeLadderGame(int cellCount, Map<Integer, Integer> snakeLadderStartEndMap) {
    this.cellCount = cellCount;
    this.snakeLadderStartEndMap = snakeLadderStartEndMap;
  }

  private int getMinimumDiceThrows() {
    Queue<Cell> queue = new Queue();
    queue.enqueue(new Cell(0));
    boolean[] visited = new boolean[cellCount];
    visited[0] = true;


    while (!queue.isEmpty()) {

      int v = queue.peek().getId();

      if (v == cellCount - 1) {
        break;
      }


      Cell startCell = queue.dequeue();

      for (int j = (v + 1); j <= (v + 6) && j < cellCount; j++) {
        // in case of ladder or snake, instead of processing 'j' cell we process,
        // snakeLadderStartEndMap.get(j)
        int id = j;
        if (snakeLadderStartEndMap.containsKey(j)) {
          id = snakeLadderStartEndMap.get(j);
        }
        if (!visited[id]) {
          visited[id] = true;
          Cell cell = new Cell();
          cell.setMinMovesToReachFromStart(startCell.getMinMovesToReachFromStart() + 1);
          cell.setId(id);
          queue.enqueue(cell);
        }

      }

    }

    return queue.peek().getMinMovesToReachFromStart();


  }

  public static void main(String[] args) {
    int N = 30;

    Map<Integer, Integer> snakeLadderStartEndMap = new HashMap<Integer, Integer>();

    snakeLadderStartEndMap.put(2, 21);
    snakeLadderStartEndMap.put(4, 7);
    snakeLadderStartEndMap.put(10, 25);
    snakeLadderStartEndMap.put(19, 28);
    snakeLadderStartEndMap.put(26, 0);
    snakeLadderStartEndMap.put(20, 8);
    snakeLadderStartEndMap.put(16, 3);
    snakeLadderStartEndMap.put(18, 6);

    SnakeLadderGame snakeLadderGame = new SnakeLadderGame(N, snakeLadderStartEndMap);

    System.out.println(snakeLadderGame.getMinimumDiceThrows());


  }
}

/*
 * 
 * Time complexity of the above solution is O(N) as every cell is added and removed only once from
 * queue. And a typical enqueue or dequeue operation takes O(1) time.
 * 
 * The idea is to consider the given snake and ladder board as a directed graph with number of
 * vertices equal to the number of cells in the board. The problem reduces to finding the shortest
 * path in a graph. Every vertex of the graph has an edge to next six vertices if next 6 vertices do
 * not have a snake or ladder. If any of the next six vertices has a snake or ladder, then the edge
 * from current vertex goes to the top of the ladder or tail of the snake. Since all edges are of
 * equal weight, we can efficiently find shortest path using Breadth First Search of the graph.
 * 
 * The input is represented by two things, first is ‘N’ which is number of cells in the given board,
 * second is an array ‘move[0…N-1]’ of size N. An entry move[i] is -1 if there is no snake and no
 * ladder from i, otherwise move[i] contains index of destination cell for the snake or the ladder
 * at i.
 */
