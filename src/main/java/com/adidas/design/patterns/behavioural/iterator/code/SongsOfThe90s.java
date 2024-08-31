package com.adidas.design.patterns.behavioural.iterator.code;

import java.util.*;

public class SongsOfThe90s implements SongIterator{

    private Map<Integer, SongInfo> bestSongs ;

    private int hashKey = 0;

    public SongsOfThe90s() {
        bestSongs = new HashMap<>();

        addSong("Losing My Religion", "REM", 1991);
        addSong("Creep", "RadioHead", 1993);
        addSong("Walk on the Ocean", "Toad the Wet Sprocket", 1991);

    }

    public void addSong(String songName, String bandName, int yearReleased) {

        SongInfo songInfo = new SongInfo(songName, bandName, yearReleased);
        this.bestSongs.put(hashKey, songInfo);

        hashKey++;
    }

    public Map<Integer, SongInfo> getBestSongs() {
        return this.bestSongs;
    }

    @Override
    public Iterator createIterator() {
        /**
         * could have send a entrySet iterator as well
         */
        return this.bestSongs.values().iterator();
    }
}
