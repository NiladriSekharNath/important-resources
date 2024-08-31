package com.adidas.design.patterns.behavioural.iterator.code.betterway.customiterator;


import com.adidas.design.patterns.behavioural.iterator.code.SongInfo;

import java.util.ArrayList;
import java.util.List;

public class SongsOfThe70s implements CustomSongIterator, SongIterator {
    private List<SongInfo> bestSongs;

    int currentIndex = 0;

    public SongsOfThe70s() {
        bestSongs = new ArrayList<>();

        addSong("Imagine", "John Lennon", 1971);
        addSong("American Pie", "Don Melean", 1971);
        addSong("I will Survive", "Gloria Gaynor", 1979);

    }

    public void addSong(String songName, String bandName, int yearReleased) {
        SongInfo songInfo = new SongInfo(songName, bandName, yearReleased);
        this.bestSongs.add(songInfo);
    }

    public List<SongInfo> getBestSongs() {
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
            songInfo = bestSongs.get(currentIndex);
            currentIndex++;
        }
        return songInfo;
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }


    @Override
    public CustomSongIterator createCustomIterator() {
        return new SongsOfThe70s();
    }
}
