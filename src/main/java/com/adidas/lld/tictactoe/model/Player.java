package com.adidas.lld.tictactoe.model;

import com.adidas.lld.tictactoe.model.approach1.PlayingPiece;

public class Player {
  private String name;
  private PlayingPiece playingPiece;

  public Player(String name, PlayingPiece playingPiece){
    this.name = name;
    this.playingPiece = playingPiece;
  }

  public String getName(){
    return this.name;
  }

  public PlayingPiece playingPiece(){
    return this.playingPiece;
  }
}
