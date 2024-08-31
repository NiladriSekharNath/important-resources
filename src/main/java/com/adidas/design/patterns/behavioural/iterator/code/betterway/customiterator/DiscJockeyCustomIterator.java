package com.adidas.design.patterns.behavioural.iterator.code.betterway.customiterator;

import com.adidas.design.patterns.behavioural.iterator.code.SongInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscJockeyCustomIterator {
    private SongIterator iter70sSongs;
    private SongIterator iter80Songs;
    private SongIterator iter90Songs;

    public DiscJockeyCustomIterator(SongIterator iter70sSongs, SongIterator iter80sSongs, SongIterator iter90Songs) {
        this.iter70sSongs = iter70sSongs;
        this.iter80Songs = iter80sSongs;
        this.iter90Songs = iter90Songs;
    }

    public void showTheSongs() {
        /**
         * this is a custom iterator implementation to show and use the iterator pattern
         */

        log.info("Printing the songs using the Custom Design Pattern");

        CustomSongIterator songs70s = this.iter70sSongs.createCustomIterator();
        CustomSongIterator songs80s = this.iter80Songs.createCustomIterator();
        CustomSongIterator songs90s = this.iter90Songs.createCustomIterator();

        log.info("Songs of the 70s");
        printTheSongs(songs70s);

        log.info("Songs of the 80s");
        printTheSongs(songs80s);

        log.info("Songs of the 90s");
        printTheSongs(songs90s);


    }

    private void printTheSongs(CustomSongIterator iterator) {
        while (iterator.hasNext()) {
            SongInfo eachBestSong = iterator.getNext();
            log.info("Song:-> Name: {}, BandName: {}, ReleaseYear: {}", eachBestSong.getSongName(), eachBestSong.getBandName(), eachBestSong.getYearReleased());
        }
    }
}
