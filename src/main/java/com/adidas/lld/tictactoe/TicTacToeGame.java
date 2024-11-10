/*
package com.adidas.lld.tictactoe;

import com.adidas.lld.tictactoe.model.Board;
import com.adidas.lld.tictactoe.model.Player;
import com.adidas.lld.tictactoe.model.approach1.PieceType;
import com.adidas.lld.tictactoe.model.approach1.PieceTypeO;
import com.adidas.lld.tictactoe.model.approach1.PieceTypeX;
import com.adidas.lld.tictactoe.model.approach1.PlayingPiece;
import com.sun.tools.javac.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

  private Deque<Player> players;

  private Board gameboard;

  public TicTacToeGame() {
    initializeGame();
  }

  private void initializeGame() {
    players = new LinkedList<>();
    */
/**
     *     PlayingPiece playingPieceX = new PlayingPieceTypeX();
     *     Player Play);
     *
     *     One way of initializing
     *
     *//*


    PieceType pieceTypeX = new PieceTypeX();

    Player player1 = new Player("Player1", new PlayingPiece(pieceTypeX));
    Player player2 = new Player("Player2", new PlayingPiece(new PieceTypeO()));

    this.players.add(player1);
    this.players.add(player2);

    this.gameboard = new Board(3);

  }

  public String startGame(){
    boolean noWinner = true;

    while(noWinner){
      Player playerTurn = players.getFirst();

      gameboard.printBoard();

      List<Pair<Integer, Integer>> freeSpaces = gameboard.getFreeCells();

      if(freeSpaces.isEmpty()){
        noWinner = false ;
        continue;
      }

      System.out.print("Player:" + playerTurn + " Enter row,column: ");
      Scanner inputScanner = new Scanner(System.in);
      String s = inputScanner.nextLine();
      String[] values = s.split(",");
      int inputRow = Integer.valueOf(values[0]);
      int inputColumn = Integer.valueOf(values[1]);

      boolean pieceAddedSuccessfully = gameboard.addPiece(inputRow, inputColumn, playerTurn.playingPiece());

      if(!pieceAddedSuccessfully) {
        //player can not insert the piece into this cell, player has to choose another cell
        System.out.println("Incorrect position chosen, try again");
        players.addFirst(playerTurn);
        continue;
      }

      players.addLast(playerTurn);

      boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.playingPiece().getPieceType());

      if(winner){
        return playerTurn.getName();
      }

    }
    return "It's a Tie";
  }

  public boolean isThereWinner(int row, int column, PieceType pieceType){
    
    boolean rowMatch = true;
    boolean columnMatch = true;
    boolean diagonalMatch = true;
    boolean antiDiagonalMatch = true;

    //need to check in row
    for(int i=0;i<gameboard.getSize();i++) {

      if(gameboard.getPlayingBoard()[row][i] == null || gameboard.getPlayingBoard()[row][i].getPieceType() != pieceType) {
        rowMatch = false;
      }
    }

    //need to check in column
    for(int i=0;i<gameboard.getSize();i++) {

      if(gameboard.getPlayingBoard()[i][column] == null || gameboard.getPlayingBoard()[i][column].getPieceType() != pieceType) {
        columnMatch = false;
      }
    }

    //need to check diagonals
    for(int i=0, j=0; i<gameboard.getSize();i++,j++) {
      if (gameboard.getPlayingBoard()[i][j] == null || gameboard.getPlayingBoard()[i][j].getPieceType() != pieceType) {
        diagonalMatch = false;
      }
    }

    //need to check anti-diagonals
    for(int i=0, j= gameboard.getSize()-1; i < gameboard.getSize();i++,j--) {
      if (gameboard.getPlayingBoard()[i][j] == null || gameboard.getPlayingBoard()[i][j].getPieceType() != pieceType) {
        antiDiagonalMatch = false;
      }
    }

    return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
  }
  
}
*/
