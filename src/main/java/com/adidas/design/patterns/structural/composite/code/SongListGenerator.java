package com.adidas.design.patterns.structural.composite.code;

public class SongListGenerator {

    public static void main(String args[]) {
        /**
         * assuming like a folder structure that we are doing here below as per the overview.png
         *
         * like below if we see industrialMusic is a type of folder here, heavyMetalMusic is another type of folder
         * dubStepMusic is another type of folder
         */
        SongComponent industrialMusic = new SongGroup("Industrial", "is a style of experimental music that draws on transgressive and provocative themes");
        SongComponent heavyMetalMusic = new SongGroup("HeavyMetal", "is a genre of rock that developed in the lates 1960s, largely in the UK and in the US");
        SongComponent dubStepMusic = new SongGroup("DubStep", "is a genre of electronic dance music that originated in South London, England");

        /**
         * top level folder/group called the "everySong" here that has all the song folders/groups
         */

        SongComponent everySong = new SongGroup("Song List", "Every Song Available");

        /**
         * we are adding here the industrialMusic folder to this main folder
         */

        everySong.add(industrialMusic);

        /**
         * here we are adding songs to the industrialMusic folder
         */
        industrialMusic.add(new Song("Industrial Music 1: Head Like a Hole", "NIN", 1990));
        industrialMusic.add(new Song("Industrial Music 2: Headhunter", "Front 242", 1988));

        /**
         * here we are adding the entire dubStepMusic folder here,
         * to simply understand we are doing this adding
         *
         * let's say dubStepMusic is kind of like a sub-type of industrialMusic
         */
        industrialMusic.add(dubStepMusic);

        dubStepMusic.add(new Song("DubStep Music 1: Centipede", "KnifeParty", 2012));
        dubStepMusic.add(new Song("DubStep Music 2: Tetris", "Doctor P", 2011));

        everySong.add(heavyMetalMusic);

        heavyMetalMusic.add(new Song("HeavyMeteal Music 1: War Pigs", "Black Sabath", 1970));
        heavyMetalMusic.add(new Song("HeavyMetal Music 2: Ace of Spades", "Motorhead", 1980));

        DiscJockey crazyLarry = new DiscJockey(everySong);

        crazyLarry.getSongList();
    }
}
