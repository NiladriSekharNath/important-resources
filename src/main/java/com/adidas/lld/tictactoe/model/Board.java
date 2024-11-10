/*
package com.adidas.lld.tictactoe.model;

import com.adidas.lld.tictactoe.model.approach1.PlayingPiece;
import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private int size ;

  private PlayingPiece[][] playingBoard ;

  public Board(int size){
    this.size = size;
    this.playingBoard = new PlayingPiece[size][size];
  }

  public int getSize(){
    return this.size;
  }

  public PlayingPiece[][] getPlayingBoard(){
    return this.playingBoard;
  }

  public boolean addPiece(int inputRow, int inputColumn, PlayingPiece playingPiece){
    if(this.playingBoard[inputRow][inputColumn] != null){
      return false;
    }

    this.playingBoard[inputRow][inputColumn] = playingPiece;
    return true;
  }

  public List<Pair<Integer, Integer>> getFreeCells(){
    List<Pair<Integer, Integer>> freeCells = new ArrayList<>();

    for(int i = 0 ; i < this.size; i++){
      for(int j = 0 ; j < this.size ; j++){
        if(this.playingBoard[i][j] == null){
          Pair<Integer, Integer> rowColumn = new Pair<>(i, j);
          freeCells.add(rowColumn);
        }
      }
    }

    return freeCells;
  }

  public void printBoard() {

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (this.playingBoard[i][j] != null) {
          System.out.print(this.playingBoard[i][j].getPieceType().getPieceTypeValue() + "   ");
        } else {
          System.out.print("    ");

        }
        System.out.print(" | ");
      }
      System.out.println();

    }
  }

}
*/
