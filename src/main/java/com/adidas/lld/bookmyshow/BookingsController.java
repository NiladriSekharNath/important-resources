package com.adidas.lld.bookmyshow;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * this logic is working on the principle that we would have to consider each seat booking atomically to prevent thread problems
 * (booking of the same seat by multiple users) which would cause inconsistency issues now the theory part is we are assigning
 * each seat with initialValue as null, which means the seats are unreserved and is available for booking
 *
 * So since we have a list of bookings we are using a concurrentHashMap(Thread-safe) with values in it as seatId, AtomicReference<String>
 * wherein we are doing, initially the seatId is empty and if the seat is booked by a particular user we would be setting the seatId value
 * with the particularId of the user, which means the particular user booked the seat any other user would not be able to book seat.
 *
 * For more details about the implementation we could see this generated link :
 *
 * https://chatgpt.com/c/67239582-ff88-8011-85cf-b93021abc810
 *
 *
 * Also it's important to understand and see the how it's done using JPA
 *
 * https://hackernoon.com/optimistic-and-pessimistic-locking-in-jpa
 *
 *
 * */
public class BookingsController {
  private final Map<String, AtomicReference<String>> seatIdsBookings;

  public BookingsController(List<Seat> seats) {
    this.seatIdsBookings = new ConcurrentHashMap<>();
    for (Seat seat : seats) {
      seatIdsBookings.put(seat.getSeatId(), new AtomicReference<>(null));

    }
  }


  public boolean createAndSetBooking(String userId, List<Seat> seatsToBook) {
    for(Seat seat : seatsToBook){
      AtomicReference<String> currentSeatReference = this.seatIdsBookings.get(seat.getSeatId());
      if(currentSeatReference != null && !currentSeatReference.compareAndSet(null, userId)){

        System.out.println(String.format("Seat with Id '%' is already booked, please try again", seat.getSeatId()));
        rollbackBooking(userId, seatsToBook);
        return false;
      }
    }
    System.out.println("Booking successful");
    return true;
  }


  /**
   *
   * @param userId
   * @param seatsToUnBook
   *
   * The userId parameter might look reduntant(duplicate or extra) at first but the userId is very important in the context of multi-threaded programming
   * to ensure consistency
   *
   * let's look at this example there are seats available for us 5,6,7,8 initially now
   * two users are trying to book the seats like this user1 -> 5,6,7 and user2 -> 6,7,8
   *
   * two users means two threads based on a particular instant in the map
   *
   * 5 -> user1
   * 6 -> user2
   * ...
   *
   * now this scenario would cause us problems so if we roll back without any userId and start rolling back user1
   * or the thread responsible for user1's booking would roll back user2's successful booking which would cause
   * consistency issues
   *
   * 5 -> user1
   * 6 -> user2
   * ...
   * rollback initiated by user1's thread
   *
   * 5 -> null
   * 6 -> null (this is wrong as we cancelled user2 thread's successful booking)
   * ...
   *
   */
  public void rollbackBooking(String userId, List<Seat> seatsToUnBook) {
    for (Seat seat : seatsToUnBook) {
      AtomicReference<String> currentSeatReference = seatIdsBookings.get(seat.getSeatId());
      if (currentSeatReference != null && currentSeatReference.get().equalsIgnoreCase(userId))
        currentSeatReference.set(null);
    }
  }
}
