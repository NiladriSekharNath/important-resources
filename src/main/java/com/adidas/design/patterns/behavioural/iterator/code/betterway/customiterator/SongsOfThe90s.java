package com.adidas.design.patterns.behavioural.iterator.code.betterway.customiterator;

import com.adidas.design.patterns.behavioural.iterator.code.SongInfo;

import java.util.HashMap;
import java.util.Map;

public class SongsOfThe90s implements CustomSongIterator, SongIterator {

    private Map<Integer, SongInfo> bestSongs;

    /**
     * this is for inserting elements to songsOfthe90s hashMap, though this way we are not supposed to do,
     * we should inject from the main method as a dependency
     */
    private int hashKey = 0;

    /**
     * this is for the iterator
     */
    private int currentIndex = 0;

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
    public boolean hasNext() {
        return this.currentIndex < this.bestSongs.size();
    }

    @Override
    public SongInfo getNext() {
        SongInfo songInfo = null;
        if (this.hasNext()) {
            songInfo = this.bestSongs.get(currentIndex);
            currentIndex++;
        }
        return songInfo;
    }

    @Override
    public void reset() {
        this.currentIndex = 0;
    }


    @Override
    public CustomSongIterator createCustomIterator() {
        return new SongsOfThe90s();
    }
}
