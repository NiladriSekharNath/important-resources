package com.adidas.design.patterns.structural.bridge.code;

public abstract class RemoteButton {
    private EntertainmentDevice theDevice ;

    public RemoteButton(EntertainmentDevice theDevice){

        this.theDevice = theDevice;
    }

    public void buttonFivePressed(){
        theDevice.buttonFivePressed();
    }

    public void buttonSixPressed(){
        theDevice.buttonSixPressed();
    }

    /**
     * the current Channel to be shown
     */
    public void deviceFeedback(){
        theDevice.deviceFeedback();
    }

    /**
     * as per the diagram we can make the button 9 as the mute in
     * one entertainment device for the TV or for one TV could have one
     * button and for another TV could have another purpose of this button
     * and for the DVDPlayer we would say
     * we could make for the DVD player as the  pause button
     */
    public abstract void buttonNinePressed();
}
