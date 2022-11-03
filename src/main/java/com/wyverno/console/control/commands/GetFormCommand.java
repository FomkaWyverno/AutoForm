package com.wyverno.console.control.commands;

import com.wyverno.console.Console;

public class GetFormCommand extends AbstractCommand {
    public GetFormCommand(Console console) {
        super(console);
    }

    @Override
    public void run(String args) {
        this.getConsole().getForm().printForm();
    }
}
