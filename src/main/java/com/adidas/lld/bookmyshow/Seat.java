package com.adidas.lld.bookmyshow;

public class Seat {
  String seatId;
  int row;
  SeatCategory seatCategory;

  SeatStatus seatStatus;

  public String getSeatId() {
    return seatId;
  }

  public void setSeatId(String seatId) {
    this.seatId = seatId;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public SeatCategory getSeatCategory() {
    return seatCategory;
  }

  public void setSeatCategory(SeatCategory seatCategory) {
    this.seatCategory = seatCategory;
  }

}
