package com.adidas.lld.bookmyshow;

public class Movie {
  int movieId;
  String movieName;
  int movieDurationInMinutes;
  /**
   * other details like Genre, Language etc.
   */

  public int getMovieId(){
    return this.movieId;
  }

  public void setMovieId(int movieId){
    this.movieId = movieId;
  }

  public int getMovieDuration() {
    return movieDurationInMinutes;
  }

  public void setMovieDuration(int movieDuration) {
    this.movieDurationInMinutes = movieDuration;
  }

  public String getMovieName() {
    return movieName;
  }

  public void setMovieName(String movieName) {
    this.movieName = movieName;
  }


}
