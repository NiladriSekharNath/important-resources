package com.adidas.design.patterns.structural.composite.code;

public class DiscJockey {
    private SongComponent songList;

    public DiscJockey(SongComponent songList){
        this.songList = songList;
    }

    public void getSongList(){
        songList.displaySongInfo();
    }
}
