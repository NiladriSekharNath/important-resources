package com.adidas.lld.bookmyshow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookMyShow {
  MovieController movieController ;
  TheatreController theatreController;

  BookingsController bookingsController;

  BookMyShow(){
    movieController = new MovieController();
    theatreController = new TheatreController();
  }

  public static void main(String args[]) {

    BookMyShow bookMyShow = new BookMyShow();

    bookMyShow.initialize();

    //user1
    bookMyShow.createBooking(City.Bangalore, "BAAHUBALI", new ArrayList<>());
    //user2
    bookMyShow.createBooking(City.Bangalore, "BAAHUBALI", new ArrayList<>());

  }

  private void createBooking(City userCity, String movieName, List<Seat> seatsToBook){
    //1. search movie by my location
    List<Movie> movies = movieController.getMoviesByCity(userCity);

    //2. select the movie which you want to see. i want to see Baahubali
    Movie interestedMovie = null;
    for (Movie movie : movies) {

      if ((movie.getMovieName()).equals(movieName)) {
        interestedMovie = movie;
      }
    }

    //3. get all show of this movie in Bangalore location
    Map<Theatre, List<Show>> showsTheatreWise = theatreController.getAllShows(interestedMovie, userCity);

    //4. select the particular show user is interested in
    Map.Entry<Theatre,List<Show>> entry = showsTheatreWise.entrySet().iterator().next();
    List<Show> runningShows = entry.getValue();
    Show interestedShow = runningShows.get(0);

    /**
     * we could introduce a lock here to prevent multiple threads in concurrent environments to access the same seat that would solve our problem
     * but this would cause performance problems and would drag down our system, this kind of locking is called pessimistic locking which is not optimal
     * we should try to use optimistic locking
     *
     * in real world Java JPA way this would be used this way, as shown in this link
     *
     * https://hackernoon.com/optimistic-and-pessimistic-locking-in-jpa
     *
     * we are trying to use a lock-free kind of way to deal with this problem.
     *
     *
     */

    String userId = "user1";
    /**
     *
     * any random userId for a particular User
     *
     */

    /**
     * ideally we should send the list of Seats here just the Ids are self-sufficient
     */
    if(bookingsController.createAndSetBooking(userId, seatsToBook)){
      /**
       * process payment steps to confirm seat booking
       */
    }else{
      /**
       * release seat bookings for the particular user
       *
       */
      bookingsController.rollbackBooking(userId, seatsToBook);
    }

    /**
     * next steps in the process...
     */
  }


  private void initialize() {

    //create movies
    createMovies();

    //create theater with screens, seats and shows
    createTheatre();
  }

  //creating 2 theatre
  private void createTheatre() {

    Movie avengerMovie = movieController.getMovieByName("AVENGERS");
    Movie baahubali = movieController.getMovieByName("BAAHUBALI");

    Theatre inoxTheatre = new Theatre();
    inoxTheatre.setTheatreId(1);
    inoxTheatre.setscreens(createScreen());
    inoxTheatre.setCity(City.Bangalore);
    List<Show> inoxShows = new ArrayList<>();
    Show inoxMorningShow = createShows(1, inoxTheatre.getScreens().get(0), avengerMovie, 8);
    Show inoxEveningShow = createShows(2, inoxTheatre.getScreens().get(0), baahubali, 16);
    inoxShows.add(inoxMorningShow);
    inoxShows.add(inoxEveningShow);
    inoxTheatre.setShows(inoxShows);


    Theatre pvrTheatre = new Theatre();
    pvrTheatre.setTheatreId(2);
    pvrTheatre.setscreens(createScreen());
    pvrTheatre.setCity(City.Delhi);
    List<Show> pvrShows = new ArrayList<>();
    Show pvrMorningShow = createShows(3, pvrTheatre.getScreens().get(0), avengerMovie, 13);
    Show pvrEveningShow = createShows(4, pvrTheatre.getScreens().get(0), baahubali, 20);
    pvrShows.add(pvrMorningShow);
    pvrShows.add(pvrEveningShow);
    pvrTheatre.setShows(pvrShows);

    theatreController.addTheatre(inoxTheatre, City.Bangalore);
    theatreController.addTheatre(pvrTheatre, City.Delhi);

  }

  private List<Screen> createScreen() {

    List<Screen> screens = new ArrayList<>();
    Screen screen1 = new Screen();
    screen1.setScreenId(1);
    screen1.setSeats(createSeats());
    screens.add(screen1);

    return screens;
  }

  private Show createShows(int showId, Screen screen, Movie movie, int showStartTime) {

    Show show = new Show();
    show.setShowId(showId);
    show.setScreen(screen);
    show.setMovie(movie);
    show.setShowStartTime(showStartTime); //24 hrs time ex: 14 means 2pm and 8 means 8AM
    return show;
  }

  //creating 100 seats
  private List<Seat> createSeats() {

    //creating 100 seats for testing purpose, this can be generalised
    List<Seat> seats = new ArrayList<>();



    //1 to 40 : SILVER
    for (int i = 0; i < 40; i++) {
      Seat seat = new Seat();
      seat.setSeatId(String.valueOf(String.format("S_%s",i)));
      seat.setSeatCategory(SeatCategory.SILVER);
      seats.add(seat);
    }

    //41 to 70 : Gold
    for (int i = 40; i < 70; i++) {
      Seat seat = new Seat();
      seat.setSeatId(String.valueOf(String.format("G_%s",i)));
      seat.setSeatCategory(SeatCategory.GOLD);
      seats.add(seat);
    }

    //70 to 100 : Platinum
    for (int i = 70; i < 100; i++) {
      Seat seat = new Seat();
      seat.setSeatId(String.valueOf(String.format("P_%s",i)));
      seat.setSeatCategory(SeatCategory.PLATINUM);
      seats.add(seat);
    }

    return seats;
  }

  private void createMovies() {

    //create Movies1
    Movie avengers = new Movie();
    avengers.setMovieId(1);
    avengers.setMovieName("AVENGERS");
    avengers.setMovieDuration(128);

    //create Movies2
    Movie baahubali = new Movie();
    baahubali.setMovieId(2);
    baahubali.setMovieName("BAAHUBALI");
    baahubali.setMovieDuration(180);


    //add movies against the cities
    movieController.addMovie(avengers, City.Bangalore);
    movieController.addMovie(avengers, City.Delhi);
    movieController.addMovie(baahubali, City.Bangalore);
    movieController.addMovie(baahubali, City.Delhi);
  }

}
