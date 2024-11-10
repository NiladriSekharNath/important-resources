package com.adidas.lld.bookmyshow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
  Map<City, List<Theatre>> cityVsTheatre;
  List<Theatre> allTheatre;

  TheatreController() {
    cityVsTheatre = new HashMap<>();
    allTheatre = new ArrayList<>();
  }

  public void addTheatre(Theatre theatre, City city) {

    allTheatre.add(theatre);

    List<Theatre> theatres = cityVsTheatre.getOrDefault(city, new ArrayList<>());
    theatres.add(theatre);
    cityVsTheatre.put(city, theatres);
  }

  public Map<Theatre, List<Show>> getAllShows(Movie movie, City city) {
    Map<Theatre, List<Show>> theatreVsShows = new HashMap<>();
    List<Theatre> theatres = cityVsTheatre.get(city);

    for (Theatre theatre : theatres) {
      List<Show> givenMovieShows = new ArrayList<>();
      List<Show> shows = theatre.getShows();

      for (Show show : shows) {
        if (show.movie.getMovieId() == movie.getMovieId()) {
          givenMovieShows.add(show);
        }

      }
      if (!givenMovieShows.isEmpty()) {
        theatreVsShows.put(theatre, givenMovieShows);
      }
    }

    return theatreVsShows;
  }

}
