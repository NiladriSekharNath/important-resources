package com.adidas.dsa.striversde.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Number Of Enclaves
 * Difficulty: MediumAccuracy: 50.93%Submissions: 79K+Points: 4Average Time: 20m
 * You are given an n x m binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 *
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 *
 * Find the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 *
 * Example 1:
 *
 * Input:
 * grid[][] = {{0, 0, 0, 0},
 *             {1, 0, 1, 0},
 *             {0, 1, 1, 0},
 *             {0, 0, 0, 0}}
 * Output:
 * 3
 * Explanation:
 * 0 0 0 0
 * 1 0 1 0
 * 0 1 1 0
 * 0 0 0 0
 * The highlighted cells represents the land cells.
 * Example 2:
 *
 * Input:
 * grid[][] = {{0, 0, 0, 1},
 *             {0, 1, 1, 0},
 *             {0, 1, 1, 0},
 *             {0, 0, 0, 1},
 *             {0, 1, 1, 0}}
 * Output:
 * 4
 * Explanation:
 * 0 0 0 1
 * 0 1 1 0
 * 0 1 1 0
 * 0 0 0 1
 * 0 1 1 0
 * The highlighted cells represents the land cells.
 *
 *
 *
 * Approach behind the problem is performing Multisource BFS, could also be done using DFS however BFS is used
 *
 * idea is :
 *
 * Any element which is '1' and on the border can exit the boundary which is allowed
 *
 * Now following the above idea if we move in the border elements if there is a 1, we add it in the queue idea meaning
 * we find the border elements and perform BFS from those elements till we absorb all the connected 1s
 *       A
 *       |
 *   0 0 1 1
 *   0 1 1 0
 *   0 1 1 0 - B
 *   0 0 0 1
 *   0 1 1 0
 *
 *   if you see the arrows A, B all the 1's are possible to exit
 *
 *         A
 *         |
 *     0 0 0 1
 *     0 1 1 0
 *     0 1 1 0 - B
 *     0 0 0 1
 *     0 1 1 0
 *
 *   Here above the middle 4 elements are the enclaves which will never move out of the boundary
 *
 *
 *   Also in my approach we counted all the 1's in the matrix
 *   and we decremented that when pop from the queue and they are visited for the first time
 *
 *
 *   Note: Now one point to note in the BFS whatever approach we follow
 *
 *   First Approach (Self)
 *
 *   while polling from the queue we check if the currentNode is already visited, if visited we continue, otherwise
 *   we mark that particular node as visited, and while evaluating the neighbouring nodes we check
 *   if the neighbouring nodes(next nodes) are visited or not if not visited we add them to the queue
 *
 *   we mark visited while polling source and then don't mark while evaluating neighbours
 *
 *   Second (Striver's Approach)
 *
 *   while adding the source elements in queue we mark sources nodes as visited
 *   and later while traversing it's neighbour we check if the neighbours(next nodes) are not visited,
 *   then we mark the nextNode as visited and add it in the queue
 *
 *   we simply poll the results and but mark visited while evaluating neighbours
 *
 *   That's the main idea we don't do both
 *
 */
public class NumberOfEnclaves {
  private static final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

  public int numberOfEnclaves(int[][] grid) {

    Queue<int[]> queue = new LinkedList<>();

    int countEnclaves = 0, rS = grid.length, cS = grid[rS - 1].length;

    for (int row = 0; row < rS; row++) {
      for (int col = 0; col < cS; col++) {
        if (grid[row][col] == 1) {
          countEnclaves++;
          if (row == 0 || row == rS - 1 || col == 0 || col == cS - 1)
            queue.add(new int[]{row, col});
        }
      }
    }

    boolean[][] visited = new boolean[rS][cS];

    while (!queue.isEmpty()) {
      int[] currentNode = queue.poll();

      int cR = currentNode[0], cC = currentNode[1];

      if(visited[cR][cC]) continue;

      visited[cR][cC] = true;

      countEnclaves--;

      for (int[] dir : directions) {
        int nR = cR + dir[0], nC = cC + dir[1];
        if (nR < 0 || nR >= rS || nC < 0 || nC >= cS || grid[nR][nC] == 0) continue;

        if (grid[nR][nC] == 1 && !visited[nR][nC]) {
          queue.add(new int[]{nR, nC});
        }
      }

    }

    return countEnclaves;
  }


  public int numberOfEnclavesStriversApproach(int[][] grid) {

    Queue<int[]> queue = new LinkedList<>();

    int countEnclaves = 0, rS = grid.length, cS = grid[rS - 1].length;

    boolean[][] visited = new boolean[rS][cS];

    for (int row = 0; row < rS; row++) {
      for (int col = 0; col < cS; col++) {
        if (grid[row][col] == 1) {
          if (row == 0 || row == rS - 1 || col == 0 || col == cS - 1) {
            queue.add(new int[]{row, col});
            visited[row][col] = true;
          }
        }
      }
    }



    while (!queue.isEmpty()) {
      int[] currentNode = queue.poll();

      int cR = currentNode[0], cC = currentNode[1];

      for (int[] dir : directions) {
        int nR = cR + dir[0], nC = cC + dir[1];
        if (nR < 0 || nR >= rS || nC < 0 || nC >= cS || grid[nR][nC] == 0) continue;

        if (grid[nR][nC] == 1 && !visited[nR][nC]) {

          visited[nR][nC] = true;
          queue.add(new int[]{nR, nC});
        }
      }

    }


    for (int row = 0; row < rS; row++) {
      for (int col = 0; col < cS; col++) {
        if(grid[row][col] == 1 && !visited[row][col]) countEnclaves++;

      }
    }
    return countEnclaves;
  }





  public static void main(String[] args) {
    new NumberOfEnclaves().numberOfEnclaves(new int[][]{
        {0, 0, 0, 0},
        {1, 0, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}}
    );



    new NumberOfEnclaves().numberOfEnclaves(new int[][]
        {{0, 0, 0, 1},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 1},
        {0, 1, 1, 0}}
    );
  }
}
