package com.adidas.design.patterns.behavioural.command.code;

/**
 * Each command you want to issue will implement the Command interface
 */
public interface Command {

    public void execute();

    public void undo();
}
