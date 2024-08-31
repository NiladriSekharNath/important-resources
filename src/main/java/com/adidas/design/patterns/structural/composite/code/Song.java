package com.adidas.design.patterns.structural.composite.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Song extends SongComponent {

    private String songName;
    private String bandName;
    private int releaseYear;

    public Song(String songName, String bandName, int releaseYear){
        this.songName = songName;
        this.bandName = bandName;
        this.releaseYear = releaseYear;
    }

    @Override
    public String getSongName() { return this.songName ; }

    @Override
    public String getBandName() { return this.bandName ; }

    @Override
    public int getReleaseYear(){ return this.releaseYear; }

    @Override
    public void displaySongInfo(){
        log.info("This Song: {} was recorded by band: {} on year: {}, ", this.songName, this.bandName, this.releaseYear);
    }
}
