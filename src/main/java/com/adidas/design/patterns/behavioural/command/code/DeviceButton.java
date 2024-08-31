package com.adidas.design.patterns.behavioural.command.code;

/**
 *
 * This is known as the invoker
 * It has a method press() that when called causes the execute method to be called
 *
 * The execute method for the Command interface then calls the method assigned in the Class that
 * implements the Command interface like TurnTVOn or TurnTVOff example
 *
 *
 */
public class DeviceButton {
    private Command theCommand;

    public DeviceButton(Command theCommand){
        this.theCommand = theCommand;
    }

    public void press(){
        theCommand.execute();
    }

    /**
     * here we give the Device the option to press the undo button
     */
    public void pressUndo(){
        theCommand.undo();
    }
}
