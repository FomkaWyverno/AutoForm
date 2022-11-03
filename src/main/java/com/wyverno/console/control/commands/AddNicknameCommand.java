package com.wyverno.console.control.commands;

import com.wyverno.commands.Command;
import com.wyverno.commands.annotations.FillableParameter;
import com.wyverno.commands.annotations.IndividualParameter;
import com.wyverno.console.Console;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddNicknameCommand extends AbstractCommand {
    public AddNicknameCommand(Console console) {
        super(console);
    }

    @Override
    public void run(String args) {
        if (this.getConsole().getForm().isHaveType()) {
            String[] names = args.split(" ");

            HashMap<String,Object> listField = this.getConsole().getForm().getParametersForCommand();

            List<Command> commandList = this.createListCommand(this.getConsole().getForm().getType().getCLAZZ(), names);

            commandList.forEach(e -> System.out.println(e.getCommand()));

        } else {
            System.out.println("Пожалуйта выберите тип команды /settype");
        }
    }

    private Field[] getFillableFields(Class<? extends Command> clazz) {
        List<Field> fieldList = new ArrayList<>();

        for (Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(FillableParameter.class)) {
                fieldList.add(field);
            }
        }

        return fieldList.toArray(new Field[0]);
    }

    private Field[] getIndividualFields(Class<? extends Command> clazz) {
        List<Field> fieldList = new ArrayList<>();

        for (Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(IndividualParameter.class)) {
                fieldList.add(field);
            }
        }

        return fieldList.toArray(new Field[0]);
    }

    private List<Command> createListCommand(Class<? extends Command> command, String[] nicknames) {

        List<Command> commandList = new ArrayList<>();

        for (String name : nicknames) {
            commandList.add(createCommand(command,name));
        }

        return commandList;
    }

    private Command createCommand(Class<? extends Command> command, String name) {
        Field[] fields = this.getFillableFields(command);

        Command cmd = null;
        try {
            cmd = command.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        assert cmd != null;

        HashMap<String, Object> defaultParameters = this.getConsole().getForm().getParametersForCommand();

        for (Field field : fields) {
            try {

                System.out.println("name = " + field.getName());

                String methodName = "set" + capitalized(field.getName());
                Method setMethod = command.getMethod(methodName,field.getType());

                if (defaultParameters.containsKey(field.getName())) {
                    setMethod.invoke(cmd, defaultParameters.get(field.getName()));
                }

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return cmd;
    }

    public static String capitalized(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }
}
