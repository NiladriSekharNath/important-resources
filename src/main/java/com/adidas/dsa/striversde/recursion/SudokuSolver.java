package com.adidas.dsa.striversde.recursion;

/**
 * approach check all solutions if correct place and then solve next step
 *
 * Time Complexity: O(9(n ^ 2)), in the worst case, for each cell in the n2 board, we have 9 possible numbers.
 *
 * Space Complexity: O(1), since we are refilling the given board itself, there is no extra space required, so constant space complexity.
 */
public class SudokuSolver {


  public void solveSudoku(char[][] board) {
    solveHelper(board);
    return;
  }

  private boolean solveHelper(char[][] board){
    for (int row = 0; row < 9; row++) {
      for (int column = 0; column < 9; column++) {
        if (board[row][column] != '.') continue;
        for (char can = '1'; can <= '9'; can++) {
          if (canPlace(board, row, column, can)) {
            board[row][column] = can;
            if (solveHelper(board)) return true;
            else board[row][column] = '.';
          }

        }
        return false;
      }
    }
    return true;
  }

  /**
   *
   * @param board
   * @param currentRow
   * @param currentColumn
   * @param candidate
   * @return if candidate can be placed at board[currentRow][currentColumn]
   *
   * the logic for the last loop is we are dividing the 9 x 9 into 3 x 3 so inorder to calculate the starting point we are placing
   *
   * 3 * ((currentRow or currentColumn)/3) here we make use of the Java integer class 2/3 not rounded to 0.667 but we use the 0 value
   *
   * for an index 2,3 it becomes -> 0, 3
   *
   * for the next part we are simply trying to place 0,1,2,3,4,5,6,7,8,9 into a 2-d matrix let's say for 2,3 iteration 4 would be box
   *
   * for(2,3)
   *
   * 3*(currentRow/3) + i/3 = 3*(2/3) + 4/3 = 1
   *
   * 3*(currentColumn/3) + i%3 = 3*(3/3) + 4%3 = 4
   *
   * https://takeuforward.org/data-structure/sudoku-solver/
   */

  private boolean canPlace(char[][] board, int currentRow, int currentColumn, char candidate) {
    for (int i = 0; i < 9; i++) {
      if (board[currentRow][i] == candidate) return false;
      if (board[i][currentColumn] == candidate) return false;
      if (board[3 * (currentRow / 3) + i / 3][3 * (currentColumn / 3) + i % 3] == candidate) return false;

    }

    return true;

  }
}
