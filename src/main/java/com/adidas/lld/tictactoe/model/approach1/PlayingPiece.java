package com.adidas.lld.tictactoe.model.approach1;

public class PlayingPiece {

  private PieceType pieceType ;

  public PlayingPiece(PieceType pieceType){
    this.pieceType = pieceType ;
  }

  public PieceType getPieceType(){
    return this.pieceType;
  }
}
