package com.adidas.design.patterns.behavioural.iterator.code;

import java.util.Arrays;
import java.util.Iterator;

public class SongsOfThe80s implements SongIterator {

    private SongInfo[] bestSongs ;

    private int currentIndex = 0;

    public SongsOfThe80s() {
        bestSongs = new SongInfo[3];

        addSong("Roam", "B52s", 1989);
        addSong("Cruel Summer", "Bananarama", 1984);
        addSong("Head Over Heels", "Tears for Fears", 1985);

    }

    public void addSong(String songName, String bandName, int yearReleased) {

        SongInfo songInfo = new SongInfo(songName, bandName, yearReleased);
        this.bestSongs[currentIndex] = songInfo;

        this.currentIndex++;
    }

    public SongInfo[] getBestSongs() {
        return this.bestSongs;
    }

    @Override
    public Iterator createIterator() {
        return Arrays.asList(this.bestSongs).iterator();
    }
}
