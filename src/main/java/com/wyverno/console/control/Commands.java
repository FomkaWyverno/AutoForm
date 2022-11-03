package com.wyverno.console.control;

import com.wyverno.console.Console;
import com.wyverno.console.control.commands.AddNicknameCommand;
import com.wyverno.console.control.commands.HelpCommand;
import com.wyverno.console.control.commands.SetTypeCommand;

public class Commands {

    @ConsoleCommand(command = "/help")
    public static void commandHelp(Console console) {
        new HelpCommand(console).run(null);
    }

    @ConsoleCommand(command = "/settype")
    public static void commandSetType(Console console,String args) {
        new SetTypeCommand(console).run(args);
    }

    @ConsoleCommand(command = "/addnicks")
    public static void commandAddNick(Console console,String args) {
        new AddNicknameCommand(console).run(args);
    }

    @ConsoleCommand(command = "/getform")
    public static void commandGetForm(Console console,String args) {
        System.out.println("GETFORM");
    }

    @ConsoleCommand(command = "/stop")
    public static void commandStop(Console console) throws StopCommandException {
        throw new StopCommandException();
    }
}
