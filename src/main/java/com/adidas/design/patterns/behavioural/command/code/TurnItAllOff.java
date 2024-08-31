package com.adidas.design.patterns.behavioural.command.code;

import java.util.List;

/**
 * "Master" off switch that would switch off all devices all at once
 *
 */
public class TurnItAllOff implements Command{

    private List<ElectronicDevice> electronicDevices;

    public TurnItAllOff(List<ElectronicDevice> electronicDevices){
        this.electronicDevices = electronicDevices;
    }

    @Override
    public void execute() {
        for(ElectronicDevice eachElectronicDevice : electronicDevices)
            eachElectronicDevice.off();
    }

    @Override
    public void undo() {
        for(ElectronicDevice eachElectronicDevice : electronicDevices)
            eachElectronicDevice.on();
    }
}
