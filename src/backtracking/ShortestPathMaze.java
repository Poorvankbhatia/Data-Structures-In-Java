package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

class PathStep {
    int i, j;
    PathStep prev;

    public PathStep(int i, int j, PathStep prev) {
        this.i = i;
        this.j = j;
        this.prev = prev;
    }

    public String toString() {
        return "[" + i + ", " + j + "]";
    }
}

class ShortestPathMaze {
    // BFS
    public static void shortestPath(int[][] matrix) {
        int N = matrix.length;
        // initial
        PathStep step = new PathStep(0, 0, null);
        LinkedList<PathStep> queue = new LinkedList<PathStep>();
        queue.add(step);
        // using set to check if already traversed
        HashSet<Integer> set = new HashSet<Integer>();
        boolean findDest = false;
        while (!queue.isEmpty() && !findDest) {
            LinkedList<PathStep> tmpQueue = new LinkedList<PathStep>();
            while (!queue.isEmpty()) {
                step = queue.remove();
                int i = step.i, j = step.j, id;
                if (matrix[i][j] == 3) {    // find dest
                    findDest = true;
                    break;
                }
                PathStep next;
                // move left
                if (j > 0 && matrix[i][j - 1] != 0) {
                    id = N * i + (j - 1);
                    if (!set.contains(id)) {
                        set.add(id);
                        //Adding previous at every step
                        next = new PathStep(i, j - 1, step);
                        tmpQueue.add(next);
                    }
                }
                // move right
                if (j < N - 1 && matrix[i][j + 1] != 0) {
                    id = N * i + (j + 1);
                    if (!set.contains(id)) {
                        set.add(id);
                        next = new PathStep(i, j + 1, step);
                        tmpQueue.add(next);
                    }
                }
                // move up
                if (i > 0 && matrix[i - 1][j] != 0) {
                    id = N * (i - 1) + j;
                    if (!set.contains(id)) {
                        set.add(id);
                        next = new PathStep(i - 1, j, step);
                        tmpQueue.add(next);
                    }
                }
                // move down
                if (i < N - 1 && matrix[i + 1][j] != 0) {
                    id = N * (i + 1) + j;
                    if (!set.contains(id)) {
                        set.add(id);
                        next = new PathStep(i + 1, j, step);
                        tmpQueue.add(next);
                    }
                }
            }
            queue = tmpQueue;
        }
        if (findDest) {
            // build path
            ArrayList<PathStep> path = new ArrayList<PathStep>();
            while (step != null) {
                path.add(step);
                step = step.prev;
            }
            Collections.reverse(path);
            // print path
            for (int i = 0; i < path.size(); i++) {
                if (i == path.size() - 1) {
                    System.out.println(path.get(i));
                } else {
                    System.out.print(path.get(i) + " -> ");
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 1, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {3, 1, 1, 1, 1, 1, 1, 1}
        };
        shortestPath(matrix);
    }
}