package com.wyverno.console.control.commands;

import com.wyverno.console.Console;

public abstract class AbstractCommand implements Command {

    protected Console console;

    public AbstractCommand(Console console) {
        this.console = console;
    }

    public Console getConsole() {
        return console;
    }
}
