package com.wyverno.console.control.commands;

import com.wyverno.commands.CommandsType;
import com.wyverno.console.Console;

public class SetTypeCommand extends AbstractCommand {

    public SetTypeCommand(Console console) {
        super(console);
    }

    @Override
    public void run(String args) {
        if (isHasType(args)) {
            CommandsType commandType = getCommandType(args);

            this.getConsole().getForm().setType(commandType);
        } else {
            System.out.println("Вы ввели не верный тип команды.");
            printNamesCommands();
        }
    }

    private boolean isHasType(String type) {
        CommandsType[] commands = CommandsType.values();

        for (CommandsType command : commands) {
            if (command.getNAME_COMMAND().equals(type)) {
                return true;
            }
        }
        return false;
    }

    private void printNamesCommands() {
        System.out.println("Список доступных команд:");

        CommandsType[] commands = CommandsType.values();

        for (CommandsType command : commands) {
            System.out.println(" - " + command.getNAME_COMMAND());
        }
    }

    private CommandsType getCommandType(String type) {
        CommandsType[] commands = CommandsType.values();

        for (CommandsType command : commands) {
            if (command.getNAME_COMMAND().equals(type)) {
                return command;
            }
        }
        return null;
    }
}
