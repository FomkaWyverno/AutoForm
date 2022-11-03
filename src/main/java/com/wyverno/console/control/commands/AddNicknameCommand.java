package com.wyverno.console.control.commands;

import com.wyverno.console.Console;

import java.lang.reflect.Field;
import java.util.HashMap;

public class AddNicknameCommand extends AbstractCommand {
    public AddNicknameCommand(Console console) {
        super(console);
    }

    @Override
    public void run(String args) {
        if (this.getConsole().getForm().isHaveType()) {
            String[] names = args.split(" ");

            HashMap<Field, Object> map = this.getConsole().getForm().getParametersForCommand();

            for (Field field : map.keySet()) {
                System.out.println(field.getName());
            }

        } else {
            System.out.println("Пожалуйта выберите тип команды /settype");
        }
    }
}
