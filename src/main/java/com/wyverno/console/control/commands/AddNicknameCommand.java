package com.wyverno.console.control.commands;

import com.wyverno.commands.annotations.ConstructorForCreateCommand;
import com.wyverno.console.Console;

import java.lang.reflect.Constructor;

public class AddNicknameCommand extends AbstractCommand {
    public AddNicknameCommand(Console console) {
        super(console);
    }

    @Override
    public void run(String args) {
        if (this.getConsole().getForm().isHaveType()) {
            String[] names = args.split(" ");

            Constructor<?> constructor = getConstructorForCommand();

            if (constructor == null) {
                System.out.println("У этого типа команды нету способа создание команды.\n" +
                        "Смените тип команды - /settype");
                return;
            }



        } else {
            System.out.println("Пожалуйта выберите тип команды /settype");
        }
    }

    private Constructor<?> getConstructorForCommand() {
        Constructor<?>[] constructors = this.getConsole().getForm().getType().getCLAZZ().getConstructors();

        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(ConstructorForCreateCommand.class)) {
                return constructor;
            }
        }

        return null;
    }
}
