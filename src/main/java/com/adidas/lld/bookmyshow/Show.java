package com.adidas.lld.bookmyshow;

import java.util.ArrayList;
import java.util.List;

public class Show {
  int showId;
  Movie movie;
  Screen screen;
  int showStartTime;
  /**
   * could have been implemented with a row wise seat implementation like this Map<Integer, List<Integer>>
   *   seatsByRows
   *
   *   row wise: 0 , 1, 2, 3 and with that list of shows
   *
   *
   */
  List<Integer> bookedSeatIds = new ArrayList<>();



  public int getShowId() {
    return showId;
  }

  public void setShowId(int showId) {
    this.showId = showId;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public Screen getScreen() {
    return screen;
  }

  public void setScreen(Screen screen) {
    this.screen = screen;
  }

  public int getShowStartTime() {
    return showStartTime;
  }

  public void setShowStartTime(int showStartTime) {
    this.showStartTime = showStartTime;
  }

  public List<Integer> getBookedSeatIds() {
    return bookedSeatIds;
  }

  public void setBookedSeatIds(List<Integer> bookedSeatIds) {
    this.bookedSeatIds = bookedSeatIds;
  }

}
