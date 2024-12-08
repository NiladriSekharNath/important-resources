package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class RatInAMaze {

 /* private static final Map<String, int[]> directions = Map.of(
      "R", new int[]{0, 1},
      "D", new int[]{1, 0},
      "L", new int[]{0, -1},
      "U", new int[]{-1, 0});*/

  /**
   * Consider a rat placed at position (0, 0) in an n x n square matrix mat. The rat's goal is to reach the destination at position (n-1, n-1). The rat can move in four possible directions: 'U'(up), 'D'(down), 'L' (left), 'R' (right).
   *
   * The matrix contains only two possible values:
   *
   * 0: A blocked cell through which the rat cannot travel.
   * 1: A free cell that the rat can pass through.
   * Note: In a path, no cell can be visited more than one time. If the source cell is 0, the rat cannot move to any other cell. In case of no path, return an empty list.+
   *
   * The task is to find all possible paths the rat can take to reach the destination, starting from (0, 0) and ending at (n-1, n-1), under the condition that the rat cannot revisit any cell along the same path. Furthermore, the rat can only move to adjacent cells that are within the bounds of the matrix and not blocked.
   *
   * Examples:
   *
   * Input: mat[][] = [[1, 0, 0, 0], [1, 1, 0, 1], [1, 1, 0, 0], [0, 1, 1, 1]]
   * Output: ["DDRDRR", "DRDDRR"]
   * Explanation: The rat can reach the destination at (3, 3) from (0, 0) by two paths - DRDDRR and DDRDRR, when printed in sorted order we get DDRDRR DRDDRR.
   * Input: mat[][] = [[1, 0], [1, 0]]
   * Output: []
   * Explanation: No path exists and the destination cell is blocked.
   * Input: mat = [[1, 1, 1], [1, 0, 1], [1, 1, 1]]
   * Output: ["DDRR", "RRDD"]
   * Explanation: The rat has two possible paths to reach the destination: 1. "DDRR" 2. "RRDD", These are returned in lexicographically sorted order.
   *
   *
   * Approach : The Approach for this is since we don't know which direction we should be moving so we keep moving all 4 directions which are valid and in the
   * matrix,
   *
   * Now a idea is using recursion we need to keep a visited array for tracking the vertices already visited :
   *
   * let's say for a certain vertex (5, 5) we have four options let's say one of that option is (6,5) downward direction
   *
   * now when we moved to (6,5) we again have four directions we can't move to upwards (5,5) since that path is already visited otherwise we
   * would be stuck in a loop, so we have mark that as visited, so we can either take a (n X n) array to mark the paths but that would increase
   * the complexity for the problem which is not desired, so try a better approach which will save the extra memory
   *
   * mark the current node(5,5) during visiting as 0 (already visited here in this problem) then when we arrive at (6,6) we mark that as visited
   * again now let's there is another path from (5,4) that is going to visit so during the backtracking part we mark that node as 1 ( can be visited again)
   *
   * thereby solving the problem for visiting.
   *
   * Now for the lexicographical order we can do this approach while adding the vertex we can do this in the stringbuilder that holds the currentPath
   * during adding the value in the resultList we can sort this but that adds a complexity of let's say 'p' be the longest path in the matrix
   * that would be O(nlogn) which is again not desirable instead we would do this while moving in the matrix direction we store the value in a
   * lexicographical order which is ensured by storing the value in the LinkedHashMap that keeps the order in which keys are present in the matrix
   *
   *
   * Time Complexity: O(4^(m*n)), because on every cell we need to try 4 different directions.
   *
   * Space Complexity:  O(m*n), Maximum Depth of the recursion tree(auxiliary space). if this is ignored we will be not requiring the visited
   * array space for every time
   *
   *
   *
   */
  private static final Map<String, int[]> directions;

  static {
    directions = new LinkedHashMap<>();
    directions.put("D", new int[]{1, 0});
    directions.put("L", new int[]{0, -1});
    directions.put("R", new int[]{0, 1});
    directions.put("U", new int[]{-1, 0});
  }

  // Function to find all possible paths
  public ArrayList<String> findPath(ArrayList<ArrayList<Integer>> mat) {
    return helper(mat, 0, new StringBuilder());
  }

  private ArrayList<String> helper(ArrayList<ArrayList<Integer>> board, int currentVertex, StringBuilder currentPath) {
    ArrayList<String> resultList = new ArrayList<>();
    int size = board.size();

    int currentRow = currentVertex / size;
    int currentColumn = currentVertex % size;

    if (currentRow == size - 1 && currentColumn == size - 1) {
      resultList.add(new String(currentPath.toString()));
      return resultList;
    }

    board.get(currentRow).set(currentColumn, 0);

    for (var direction : directions.entrySet()) {
      int nextRow = currentRow + direction.getValue()[0];
      int nextColumn = currentColumn + direction.getValue()[1];
      if (nextRow >= 0 && nextRow < size && nextColumn >= 0 && nextColumn < size && board.get(nextRow).get(nextColumn) == 1) {
        int nextVertex = nextRow * size + nextColumn;
        currentPath.append(direction.getKey());
        resultList.addAll(helper(board, nextVertex, currentPath));
        currentPath.deleteCharAt(currentPath.length() - 1);
      }
    }

    board.get(currentRow).set(currentColumn, 1);

    return resultList;
  }

  public static void main(String[] args) {
    int[][] board = new int[][]{{1, 0, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 0}, {0, 1, 1, 1}};
    ArrayList<ArrayList<Integer>> boardList = new ArrayList<>();
    ArrayList<Integer> tempList = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {

        tempList.add(board[i][j]);
      }
      boardList.add(tempList);
    }

    new RatInAMaze().findPath(boardList);
  }
}
