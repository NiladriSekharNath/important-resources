package com.adidas.design.patterns.behavioural.iterator.code.badway;

import com.adidas.design.patterns.behavioural.iterator.code.SongInfo;
import com.adidas.design.patterns.behavioural.iterator.code.SongsOfThe70s;
import com.adidas.design.patterns.behavioural.iterator.code.SongsOfThe80s;
import com.adidas.design.patterns.behavioural.iterator.code.SongsOfThe90s;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class DiscJockey {

    private SongsOfThe70s songsOfThe70s;
    private SongsOfThe80s songsOfThe80s;
    private SongsOfThe90s songsOfThe90s;

    public DiscJockey(SongsOfThe70s songsOfThe70s, SongsOfThe80s songsOfThe80s, SongsOfThe90s songsOfThe90s) {

        this.songsOfThe70s = songsOfThe70s;
        this.songsOfThe80s = songsOfThe80s;
        this.songsOfThe90s = songsOfThe90s;

    }

    public void showTheSongs() {
        /**
         * since the types of songs are different for all we would require to handle each case separately as this
         * is not the most optimal way
         *
         * as we have duplicate code here as we are seeing below
         */

        log.info("Printing the songs using the normal iterator ");

        List<SongInfo> al70Songs = this.songsOfThe70s.getBestSongs();

        log.info("Songs of the 70s ");

        for (int i = 0; i < al70Songs.size(); i++) {
            SongInfo eachBestSong = al70Songs.get(i);
            log.info("Song:-> Name: {}, BandName: {}, ReleaseYear: {}", eachBestSong.getSongName(), eachBestSong.getBandName(), eachBestSong.getYearReleased());

        }

        SongInfo[] array80sSongs = this.songsOfThe80s.getBestSongs();

        log.info("Songs of the 80s ");

        for (int i = 0; i < al70Songs.size(); i++) {
            SongInfo eachBestSong = array80sSongs[i];
            log.info("Song:-> Name: {}, BandName: {}, ReleaseYear: {}", eachBestSong.getSongName(), eachBestSong.getBandName(), eachBestSong.getYearReleased());

        }

        Map<Integer, SongInfo> hashMap90sSongs = this.songsOfThe90s.getBestSongs();

        log.info("Songs of the 90s ");

        for (Integer eachKey : hashMap90sSongs.keySet()) {
            SongInfo eachBestSong = hashMap90sSongs.get(eachKey);
            log.info("Song:-> Name: {}, BandName: {}, ReleaseYear: {}", eachBestSong.getSongName(), eachBestSong.getBandName(), eachBestSong.getYearReleased());

        }

    }
}
