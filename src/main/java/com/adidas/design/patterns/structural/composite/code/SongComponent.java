package com.adidas.design.patterns.structural.composite.code;

/**
 * for all these methods we are throwing new UnsupportedException because
 * if some classes would want to use these methods they can use them
 * by overriding
 */
public abstract class SongComponent {

    public void add(SongComponent songComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(SongComponent songComponent) {
        throw new UnsupportedOperationException();
    }

    public SongComponent getComponent(int componentIndex) {
        throw new UnsupportedOperationException();
    }

    public String getSongName() {
        throw new UnsupportedOperationException();
    }

    public String getBandName() {
        throw new UnsupportedOperationException();
    }

    public int getReleaseYear() {
        throw new UnsupportedOperationException();
    }

    public void displaySongInfo() {
        throw new UnsupportedOperationException();
    }
}
