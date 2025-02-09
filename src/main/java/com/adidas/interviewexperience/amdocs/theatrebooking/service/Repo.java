package com.adidas.interviewexperience.amdocs.theatrebooking.service;

import com.adidas.interviewexperience.amdocs.theatrebooking.model.Auditorium;
import com.adidas.interviewexperience.amdocs.theatrebooking.repo.IRepo;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Repo implements IRepo {

  private final Map<Integer, Auditorium> eventMap = new ConcurrentHashMap<>();
  private final Map<Integer, Set<Integer>> underBooking = new ConcurrentHashMap<>();
  private final Map<Integer, Set<Integer>> successfulBookings = new ConcurrentHashMap<>();

  @Override
  public void registerEvent(int eventId, int audiNo, int totalSeats) {
    Auditorium auditorium = new Auditorium(audiNo, eventId, totalSeats);
    eventMap.put(eventId, auditorium);
    underBooking.put(eventId, ConcurrentHashMap.newKeySet());
    successfulBookings.put(eventId, ConcurrentHashMap.newKeySet());
  }

  @Override
  public Auditorium getAuditoriumForEvent(int eventId) {
    return eventMap.getOrDefault(eventId, null);
  }

  @Override
  public int getBookedSeatsOrUnderBookingSeats(int eventId) {
    return underBooking.getOrDefault(eventId, ConcurrentHashMap.newKeySet()).size() +
        successfulBookings.getOrDefault(eventId, ConcurrentHashMap.newKeySet()).size();
  }

  @Override
  public void addBooking(int eventId, int userId) {
    underBooking.computeIfAbsent(eventId, k -> ConcurrentHashMap.newKeySet()).add(userId);
  }

  @Override
  public void removeUnderBooking(int eventId, int userId) {
    if (underBooking.containsKey(eventId)) {
      underBooking.get(eventId).remove(userId);
    }
  }

  @Override
  public void addSuccessfulBooking(int eventId, int userId) {
    successfulBookings.computeIfAbsent(eventId, k -> ConcurrentHashMap.newKeySet()).add(userId);
  }
}
