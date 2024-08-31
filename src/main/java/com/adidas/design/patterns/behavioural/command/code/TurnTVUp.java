package com.adidas.design.patterns.behavioural.command.code;

public class TurnTVUp implements Command {
    private ElectronicDevice electronicDevice;

    public TurnTVUp(ElectronicDevice electronicDevice) {
        this.electronicDevice = electronicDevice;
    }

    @Override
    public void execute() {
        electronicDevice.volumeUp();
    }

    @Override
    public void undo() {
        electronicDevice.volumeDown();
    }
}
