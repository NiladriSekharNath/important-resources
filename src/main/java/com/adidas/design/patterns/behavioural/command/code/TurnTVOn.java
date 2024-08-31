package com.adidas.design.patterns.behavioural.command.code;

/**
 * we are implementing specific type of command here which is Turning the TV on
 */
public class TurnTVOn implements Command {

    /**
     * also we need to pass the which electronic device we need to turn on (or other words execute the command)
     */
    ElectronicDevice theDevice ;

    public TurnTVOn(ElectronicDevice theDevice){
        this.theDevice = theDevice;
    }
    @Override
    public void execute() {
        theDevice.on();
    }


    @Override
    public void undo() {
        theDevice.off();
    }
}
