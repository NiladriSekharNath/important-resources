package com.adidas.interviewexperience.amdocs.theatrebooking.model;

public class Auditorium {
  public int auditoriumNo;
  public int capacity;
  public int eventId;

  public Auditorium(){}

  public Auditorium(int auditoriumNo, int eventId, int capacity){
    this.auditoriumNo = auditoriumNo;
    this.eventId = eventId;
    this.capacity = capacity;
  }

}
