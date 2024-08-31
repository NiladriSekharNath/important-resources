package com.adidas.design.patterns.behavioural.command.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayWithRemoteMain {

    public static void main(String[] args){

        ElectronicDevice newDevice = TVRemote.getDevice();

        TurnTVOn onCommand = new TurnTVOn(newDevice);

        DeviceButton onPressed = new DeviceButton(onCommand);

        /**
         * pressing the button kind of thing
         */
        onPressed.press();

        //--------------------------------------------------------------------

        /**
         * pressing the off button to show in action
         */
        TurnTVOff offCommand = new TurnTVOff(newDevice);

        onPressed = new DeviceButton(offCommand);

        onPressed.press();

        //--------------------------------------------------------------------

        TurnTVUp volUpCommand = new TurnTVUp(newDevice);

        onPressed = new DeviceButton(volUpCommand);

        onPressed.press();
        onPressed.press();
        onPressed.press();

        //------------------------------------------------------------------------

        Television theTV = new Television();

        Radio theRadio = new Radio();

        List<ElectronicDevice> allDevices = Arrays.asList(theTV, theRadio);

        TurnItAllOff turnOffAllDevices = new TurnItAllOff(allDevices);

        DeviceButton turnThemOff = new DeviceButton(turnOffAllDevices);

        turnThemOff.press();

        //------------------------------------------------

        turnThemOff.pressUndo();

    }
}
