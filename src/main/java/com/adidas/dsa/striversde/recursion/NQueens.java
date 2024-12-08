package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
  public List<List<String>> solveNQueens(int n) {
    return helper(new char[n][n], n, 0, new int[n], new int[2*n - 1], new int[2*n -1]);
  }

  private List<List<String>> helper(char[][] board,int size, int currentRow, int[] columnHash, int[] leftDiagonal, int[] rightDiagonal){
    List<List<String>> resultList = new ArrayList<>();
    if(currentRow == board.length){
      resultList.add(prepareBoard(board));
      return resultList;
    }

    for(int column = 0; column < board.length; column++){
      if(columnHash[column] != 1 && leftDiagonal[currentRow + column] != 1 && rightDiagonal[(size - 1) + (column - currentRow)] != 1){
        columnHash[column] = 1 ;
        leftDiagonal[currentRow + column] = 1;
        rightDiagonal[(size - 1) + (column - currentRow)] = 1;
        board[currentRow][column] = 'Q';
        resultList.addAll(helper(board, size, currentRow + 1, columnHash, leftDiagonal, rightDiagonal));
        board[currentRow][column] = '.';
        columnHash[column] = 0;
        leftDiagonal[currentRow + column] = 0;
        rightDiagonal[(size - 1) + (column - currentRow)] = 0;
      }
    }
    return resultList;

  }

  public List<String> prepareBoard(char[][] board){
    List<String> eachResultBoard = new ArrayList<>();
    for(int row = 0 ; row < board.length; row++){
      StringBuilder sb = new StringBuilder();

      for(int column = 0; column < board.length; column++){

        sb.append(board[row][column] == 'Q' ? 'Q' :'.');
      }

      eachResultBoard.add(sb.toString());

    }

    return eachResultBoard;
  }

  public static void main(String[] args){
    new NQueens().solveNQueens(4);
  }
}
