package com.adidas.lld.carrentalsystem;

import java.util.Date;
import java.util.Random;

public class Reservation {
  int reservationId;
  User user;
  Vehicle vehicle;
  Date bookingDate;
  Date dateBookedFrom;
  Date dateBookedTo;
  Long fromTimeStamp;
  Long toTimeStamp;
  Location pickUpLocation;
  Location dropLocation;
  ReservationType reservationType;
  ReservationStatus reservationStatus;
  Location location;

  double reservationCharge ;

  public int createReservation(User user, Vehicle vehicle, String typeOfReservation){
    Random random = new Random();
    reservationId = random.nextInt(1, 1000000);
    this.user = user;
    this.vehicle = vehicle ;
    reservationType = "daily".equalsIgnoreCase(typeOfReservation) ? ReservationType.DAILY : ReservationType.HOURLY;

    /**
     * reservationCharge = calculateReservationCharge();
     */
    return reservationId;
  }

  public double getReservationCharge(){
    return this.reservationCharge;
  }

}
