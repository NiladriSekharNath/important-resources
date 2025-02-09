package com.adidas.interviewexperience.amdocs.theatrebooking.repo;

import com.adidas.interviewexperience.amdocs.theatrebooking.model.Auditorium;

public interface IRepo {
  void registerEvent(int eventId, int audiNo, int totalSeats);
  Auditorium getAuditoriumForEvent(int eventId);
  int getBookedSeatsOrUnderBookingSeats(int eventId);
  void addBooking(int eventId, int userId);
  void removeUnderBooking(int eventId, int userId);
  void addSuccessfulBooking(int eventId, int userId);

}
