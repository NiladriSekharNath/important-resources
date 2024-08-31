package com.adidas.design.patterns.behavioural.iterator.code.betterway.customiterator;

import com.adidas.design.patterns.behavioural.iterator.code.SongInfo;

public interface CustomSongIterator {

    public boolean hasNext();
    public SongInfo getNext();
    public void reset();
}
