package com.adidas.dsa.striversde.graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill:
 *
 * Begin with the starting pixel and change its color to color.
 * Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
 * Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
 * The process stops when there are no more adjacent pixels of the original color to update.
 * Return the modified image after performing the flood fill.
 *
 *
 *
 * Example 1:
 *
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 *
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 *
 * Explanation:
 *
 *
 *
 * From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 *
 * Note the bottom corner is not colored 2, because it is not horizontally or vertically connected to the starting pixel.
 *
 *
 * we are doing bfs or dfs from the source of the point given
 *
 * only important point that we learnt from here is when we flatten 2D -> 1D we need the numofColumns and not the rowSize
 *
 *
 *
 *
 * Converting Between 2D and 1D Indexing in a Matrix
 *
 * When working with a 2D matrix, it is often useful to map its elements to a 1D array for computational efficiency or compatibility with certain algorithms. To do this effectively, we need a clear understanding of how to translate between 2D and 1D indices.
 *
 * In this note, we will use the term numColumns to represent the number of columns in the matrix, which is essential for these calculations.
 *
 * 1. Matrix Dimensions
 *
 * Consider a matrix of dimensions m × n:
 *
 * m: Number of rows
 *
 * n: Number of columns (numColumns)
 *
 * For example, let the matrix be:
 *
 * Matrix (2 x 3):
 * [
 *     [a, b, c],
 *     [d, e, f]
 * ]
 *
 * m = 2 (2 rows)
 *
 * numColumns = n = 3 (3 columns)
 *
 * 2. Flattening the Matrix (2D to 1D)
 *
 * To convert an element from 2D indexing (row, column) to a 1D index:
 *
 * Formula:
 *
 * 1D_index = row * numColumns + column
 *
 * row: The 0-based index of the row
 *
 * numColumns: The number of columns in the matrix
 *
 * column: The 0-based index of the column
 *
 * Example:
 *
 * For the matrix:
 *
 * [
 *     [a, b, c],  // Row 0
 *     [d, e, f]   // Row 1
 * ]
 *
 * Flattened into a 1D array:
 *
 * [a, b, c, d, e, f]
 *
 * Element a (row = 0, column = 0):
 *
 * 1D_index = 0 * 3 + 0 = 0
 *
 * Element e (row = 1, column = 1):
 *
 * 1D_index = 1 * 3 + 1 = 4
 *
 * Element f (row = 1, column = 2):
 *
 * 1D_index = 1 * 3 + 2 = 5
 *
 * 3. Unflattening the Matrix (1D to 2D)
 *
 * To convert a 1D index back to 2D indices (row, column):
 *
 * Formula:
 *
 * row = 1D_index / numColumns (integer division)
 * column = 1D_index % numColumns
 *
 * 1D_index: The 0-based index in the flattened 1D array
 *
 * numColumns: The number of columns in the matrix
 *
 * Example:
 *
 * For the flattened 1D array:
 *
 * [a, b, c, d, e, f]
 *
 * Element at 1D_index = 4:
 *
 * row = 4 / 3 = 1
 *
 * column = 4 % 3 = 1
 *
 * Therefore, 1D_index = 4 corresponds to (row, column) = (1, 1).
 *
 * Element at 1D_index = 2:
 *
 * row = 2 / 3 = 0
 *
 * column = 2 % 3 = 2
 *
 * Therefore, 1D_index = 2 corresponds to (row, column) = (0, 2).
 *
 * 4. General Notes
 *
 * numColumns Naming: The term numColumns clearly represents the number of columns in the matrix, making it intuitive and consistent.
 *
 * Valid Index Range:
 *
 * For an m × n matrix:
 *
 * Valid row indices: 0 to m-1
 *
 * Valid column indices: 0 to n-1
 *
 * Valid 1D_index: 0 to (m * n) - 1
 *
 * Applications:
 *
 * Flattening matrices for computation (e.g., GPU/CPU memory operations).
 *
 * Accessing matrix elements using a single loop.
 *
 * Storing sparse matrices or linear algebra operations.
 *
 * 5. Example Code (Java)
 *
 * Below is an example implementation in Java to illustrate the concepts:
 *
 */
public class FloodFil {
  private static final int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int originalColor = image[sr][sc];
    Queue<Integer> queue = new LinkedList<>();
    int numRows = image.length, numColumns = image[0].length , startPos = sr * numColumns + sc;

    queue.add(startPos);

    Set<Integer> visited = new HashSet<>();
    visited.add(startPos);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int current = queue.poll();
        int currentRow = current / numColumns, currentColumn = current % numColumns;
        image[currentRow][currentColumn] = color;

        for (int[] dir : directions) {
          int nextRow = currentRow + dir[0];
          int nextColumn = currentColumn + dir[1];
          int  nextVertex =  nextRow * numColumns + nextColumn;
          if (nextRow >= 0 && nextRow < numRows && nextColumn >= 0 && nextColumn < numColumns && image[nextRow][nextColumn] == originalColor
              && image[nextRow][nextColumn] != color && !visited.contains(nextVertex)) {

            queue.add(nextVertex);
          }
        }
      }
    }

    return image;
  }


  public static void main(String args[]) {
    new FloodFil().floodFill(new int[][]{{0, 0, 0}, {0, 0, 0}}, 0, 0, 2);
  }
}
