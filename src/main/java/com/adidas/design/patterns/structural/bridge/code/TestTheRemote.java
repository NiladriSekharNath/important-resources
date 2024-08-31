package com.adidas.design.patterns.structural.bridge.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestTheRemote {

    public static void main(String args[]) {

        RemoteButton remoteForTheTV = new TVRemoveMute(new TVDevice(1, 200));
        RemoteButton remoteForTheTV2 = new TVRemotePause(new TVDevice(1, 500));

        RemoteButton remoteForTheDVD = new DVDRemotePause(new DVDDevice(1, 20));

        log.info("Test TV with Mute");

        remoteForTheTV.buttonFivePressed();
        remoteForTheTV.buttonSixPressed();
        remoteForTheTV.buttonNinePressed();

        log.info(" Test TV with Pause ");

        remoteForTheTV2.buttonFivePressed();
        remoteForTheTV2.buttonSixPressed();
        remoteForTheTV2.buttonSixPressed();
        remoteForTheTV2.buttonSixPressed();
        remoteForTheTV2.buttonSixPressed();
        remoteForTheTV2.buttonNinePressed();

        remoteForTheTV2.deviceFeedback();

        log.info("DVD with Pause");

        remoteForTheDVD.buttonFivePressed();
        remoteForTheDVD.buttonSixPressed();
        remoteForTheDVD.buttonSixPressed();
        remoteForTheDVD.buttonSixPressed();
        remoteForTheDVD.buttonSixPressed();
        remoteForTheDVD.buttonNinePressed();

     }
}
