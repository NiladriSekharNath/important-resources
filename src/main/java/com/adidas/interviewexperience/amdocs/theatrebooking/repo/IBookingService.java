package com.adidas.interviewexperience.amdocs.theatrebooking.repo;

import com.adidas.interviewexperience.amdocs.theatrebooking.model.BookingSuccessful;

public interface IBookingService {

  void startBooking(int eventId, int userId);
  BookingSuccessful confirmBooking(int eventId, int userId);
}
