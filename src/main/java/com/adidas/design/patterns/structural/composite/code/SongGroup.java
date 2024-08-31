package com.adidas.design.patterns.structural.composite.code;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class SongGroup extends SongComponent {

    List<SongComponent> songComponents = new ArrayList<>();

    String groupName;
    String groupDescription;

    public SongGroup(String groupName, String groupDescription) {
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getGroupDescription() {
        return this.groupDescription;
    }

    @Override
    public void add(SongComponent songComponent) {
        songComponents.add(songComponent);
    }

    @Override
    public void remove(SongComponent songComponent) {
        songComponents.remove(songComponent);
    }

    @Override
    public SongComponent getComponent(int componentIndex) {
        return songComponents.get(componentIndex);
    }

    @Override
    public void displaySongInfo() {
        log.info("Song group: name : {}, description: {}", this.groupName, this.groupDescription);

        Iterator songIterator = songComponents.iterator();

        while (songIterator.hasNext()) {
            SongComponent songInfo = (SongComponent) songIterator.next();
            songInfo.displaySongInfo();
        }
    }

}
