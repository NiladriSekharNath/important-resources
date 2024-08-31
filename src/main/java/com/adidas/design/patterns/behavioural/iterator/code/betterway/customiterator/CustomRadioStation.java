package com.adidas.design.patterns.behavioural.iterator.code.betterway.customiterator;

public class CustomRadioStation {

    public static void main(String args[]){

        SongsOfThe70s songsOfThe70s = new SongsOfThe70s();
        SongsOfThe80s songsOfThe80s = new SongsOfThe80s();
        SongsOfThe90s songsOfThe90s = new SongsOfThe90s();

        DiscJockeyCustomIterator madTerry = new DiscJockeyCustomIterator(songsOfThe70s, songsOfThe80s, songsOfThe90s);

        madTerry.showTheSongs();

       /* DiscJockeyCustomIterator madPeter = new DiscJockeyCustomIterator(songsOfThe70s, songsOfThe80s, songsOfThe90s);

        madPeter.showTheSongs();*/
    }
}
