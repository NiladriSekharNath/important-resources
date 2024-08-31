package com.adidas.design.patterns.behavioural.iterator.code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SongsOfThe70s implements SongIterator {

    private List<SongInfo> bestSongs;

    public SongsOfThe70s() {
        bestSongs = new ArrayList<>();

        addSong("Imagine", "John Lennon", 1971);
        addSong("American Pie", "Don Melean", 1971);
        addSong("I will Survive", "Gloria Gaynor", 1979);

    }

    public void addSong(String songName, String bandName, int yearReleased){
        SongInfo songInfo = new SongInfo(songName, bandName, yearReleased);
        this.bestSongs.add(songInfo);
    }

    public List<SongInfo> getBestSongs(){
        return this.bestSongs;
    }

    @Override
    public Iterator createIterator() {
        return this.bestSongs.iterator();
    }
}
