package com.adidas.design.patterns.behavioural.iterator.code;

/*import com.adidas.design.patterns.behavioural.iterator.code.badway.DiscJockey;*/
import com.adidas.design.patterns.behavioural.iterator.code.badway.DiscJockey;
import com.adidas.design.patterns.behavioural.iterator.code.betterway.javaiterator.DiscJockeyIterator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RadioStation {

    public static void main(String args[]){

        SongsOfThe70s songs70s = new SongsOfThe70s();
        SongsOfThe80s songs80s = new SongsOfThe80s();
        SongsOfThe90s songs90s = new SongsOfThe90s();

        DiscJockey madMike = new DiscJockey(songs70s, songs80s, songs90s);

        madMike.showTheSongs();

        DiscJockeyIterator madJessica = new DiscJockeyIterator(songs70s, songs80s, songs90s);

        madJessica.showTheSongs();
    }
}
