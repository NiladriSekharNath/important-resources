package com.adidas.design.patterns.behavioural.iterator.code.betterway.customiterator;

import com.adidas.design.patterns.behavioural.iterator.code.SongInfo;

public class SongsOfThe80s implements CustomSongIterator, SongIterator {

    private SongInfo[] bestSongs;

    /**
     * this is for inserting elements to songsOfthe80s array, though this way we are not supposed to do,
     * we should inject from the main method as a dependency
     */
    private int arrayIndex = 0;

    /**
     * this is for the iterator
     */
    private int currentIndex = 0;

    public SongsOfThe80s() {
        bestSongs = new SongInfo[3];

        addSong("Roam", "B52s", 1989);
        addSong("Cruel Summer", "Bananarama", 1984);
        addSong("Head Over Heels", "Tears for Fears", 1985);

    }

    public void addSong(String songName, String bandName, int yearReleased) {

        SongInfo songInfo = new SongInfo(songName, bandName, yearReleased);
        this.bestSongs[arrayIndex] = songInfo;

        this.arrayIndex++;
    }

    @Override
    public boolean hasNext() {
        return this.currentIndex < this.bestSongs.length;
    }

    @Override
    public SongInfo getNext() {
        SongInfo songInfo = null;
        if(this.hasNext()){
            songInfo = this.bestSongs[currentIndex];
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
        return new SongsOfThe80s();
    }
}
