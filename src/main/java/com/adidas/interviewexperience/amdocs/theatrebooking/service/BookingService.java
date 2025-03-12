package com.adidas.interviewexperience.amdocs.theatrebooking.service;

import com.adidas.interviewexperience.amdocs.theatrebooking.model.Auditorium;
import com.adidas.interviewexperience.amdocs.theatrebooking.model.BookingSuccessful;
import com.adidas.interviewexperience.amdocs.theatrebooking.repo.IBookingService;
import com.adidas.interviewexperience.amdocs.theatrebooking.repo.IRepo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BookingService implements IBookingService {
  private final IRepo repo;

  Map<Integer, ReentrantLock> bookingsMap;

  public BookingService(Repo repo) {
    this.repo = repo;
    bookingsMap = new ConcurrentHashMap<>();
  }

  /**
   *
   * the main idea where I got stuck here is
   *
   * I thought we should decrement the capacity of the auditorium seats for each successful booking
   *
   * event 7 -> auditorium 2, capacity = 2
   *
   * 7, 1 -> (eventId, userId) -> decrementing by 1
   * 7, 2 -> (eventId, userId) -> decrementing by 1
   * 7, 3 -> (eventId, userId) -> 0 then we cannot add
   *
   * however this approach is wrong:
   *  as we would lose track of the count
   *  we would have to increment the count for an  unsuccessful booking
   *
   *
   * so the correct approach is :
   *
   * underbooking we add(7, {1, 2})
   * each time we check and acquire lock for eventId
   *
   * every event has it's individual lock
   *
   * so every time we are counting the capacity by total successful bookings and total under bookings
   *
   *
   * now for a successful booking ->
   *  we remove it from underbooking 7, 1
   *  and add it to successful bookings 7,1
   */
  @Override
  public void startBooking(int eventId, int userId) {
    Lock lock = getLockForEvent(eventId);
    try {
      lock.lock();
      if (repo.getBookedSeatsOrUnderBookingSeats(eventId) < repo.getAuditoriumForEvent(eventId).capacity)
        repo.addBooking(eventId, userId);
    } finally {
      lock.unlock();
    }

  }

  @Override
  public BookingSuccessful confirmBooking(int eventId, int userId) {
    Lock lock = getLockForEvent(eventId);

    BookingSuccessful bookingSuccessful = null;

    try {

      lock.lock();

      Auditorium auditorium = repo.getAuditoriumForEvent(eventId);
      if (auditorium.capacity > repo.getBookedSeatsOrUnderBookingSeats(eventId)) {

        repo.addSuccessfulBooking(eventId, userId);
        repo.removeUnderBooking(eventId, userId);
        bookingSuccessful = new BookingSuccessful(eventId, userId);
        return bookingSuccessful;

      }
      return new BookingSuccessful();
    } finally {

      lock.unlock();
    }

  }

  private Lock getLockForEvent(int eventId) {
    return bookingsMap.computeIfAbsent(eventId, k -> new ReentrantLock());
  }
}
