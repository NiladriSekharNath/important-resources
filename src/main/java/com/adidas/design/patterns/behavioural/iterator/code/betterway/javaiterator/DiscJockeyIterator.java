package com.adidas.design.patterns.behavioural.iterator.code.betterway.javaiterator;

import com.adidas.design.patterns.behavioural.iterator.code.SongInfo;
import com.adidas.design.patterns.behavioural.iterator.code.SongIterator;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

@Slf4j
public class DiscJockeyIterator {
    private SongIterator iter70sSongs;
    private SongIterator iter80sSongs;
    private SongIterator iter90sSongs;

    public DiscJockeyIterator(SongIterator songsOfThe70s, SongIterator songsOfThe80s, SongIterator songsOfThe90s) {

        this.iter70sSongs = songsOfThe70s;
        this.iter80sSongs = songsOfThe80s;
        this.iter90sSongs = songsOfThe90s;

    }

    public void showTheSongs() {
        /**
         * this is the more optimal way to using the song iterator moving in the collection
         * we did not have to write individually different implementations to iterate for all different
         * types of iterator
         *
         */

        log.info("Printing the songs using the Iterator Design Pattern ");


        Iterator songs70s = this.iter70sSongs.createIterator();
        Iterator songs80s = this.iter80sSongs.createIterator();
        Iterator songs90s = this.iter90sSongs.createIterator();

        log.info("Songs of the 70s");
        printTheSongs(songs70s);

        log.info("Songs of the 80s");
        printTheSongs(songs80s);

        log.info("Songs of the 90s");
        printTheSongs(songs90s);


    }

    private void printTheSongs(Iterator iterator) {
        while (iterator.hasNext()) {
            SongInfo eachBestSong = (SongInfo) iterator.next();
            log.info("Song:-> Name: {}, BandName: {}, ReleaseYear: {}", eachBestSong.getSongName(), eachBestSong.getBandName(), eachBestSong.getYearReleased());
        }
    }
}
