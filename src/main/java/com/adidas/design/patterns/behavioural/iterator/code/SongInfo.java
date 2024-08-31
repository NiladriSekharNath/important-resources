package com.adidas.design.patterns.behavioural.iterator.code;

/**
 * in order to use the iterator pattern we are given by three different sources
 * different collections of SongInfo
 * like let's say one Arraylist, one source is sending an array
 * and the last one is sending a HashTable/HashMap
 */
public class SongInfo {

    private String songName;
    private String bandName;
    private int yearReleased;

    public SongInfo(String songName, String bandName, int yearReleased){
        this.songName = songName;
        this.bandName = bandName;
        this.yearReleased = yearReleased;
    }

    public String getSongName(){ return this.songName; }

    public String getBandName() { return this.bandName; }

    public int getYearReleased() { return this.yearReleased; }
}
