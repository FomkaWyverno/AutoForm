package com.wyverno.console.control.commands;

import com.wyverno.console.Console;

public class HelpCommand extends AbstractCommand {

    private static final String helpInfo =
            "1. /help - Помощь по командам.\n" +
            "2. /settype 'тип команды' - Установить тип команды.\n" +
            "3. /addnicks 'ник-неймы' - Добавить ник-неймы.\n" +
            "4. /getform - Получить форму.\n" +
            "5. /stop - Остановить программу.";

    public HelpCommand(Console console) {
        super(console);
    }

    @Override
    public void run(String args) {
        System.out.println(helpInfo);
    }
}
