package com.adidas.lld.carrentalsystem;

public class Bill {
  Reservation reservation ;
  double totalBillAmount ;

  public Bill(Reservation reservation){
    this.reservation = reservation;
    this.totalBillAmount = getTotalBillAmount(reservation);
  }

  public double getTotalBillAmount(Reservation reservation){
    /**
     * calculate bill based on the reservation
     */
    return reservation.getReservationCharge();
  }

}
