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

            for (Command command : commandList) {
                this.getConsole().getForm().addCommand(command);
            }

            System.out.println("Игроки добавлены в форму! Количество игроков: " + commandList.size());

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

    private Field getIndividualField(Class<? extends Command> clazz) {

        for (Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(IndividualParameter.class)) {
                return field;
            }
        }

        return null;
    }

    private List<Command> createListCommand(Class<? extends Command> command, String[] nicknames) {

        List<Command> commandList = new ArrayList<>();

        for (String name : nicknames) {
            commandList.add(createCommand(command,name));
        }

        return commandList;
    }

    private Command createCommand(Class<? extends Command> command, String name) {
        Field[] fillableFields = this.getFillableFields(command);
        Field individualField = this.getIndividualField(command);

        if (individualField == null) {
            throw new NullPointerException("individualField is null");
        }

        Command cmd = null;
        try {
            cmd = command.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        assert cmd == null;

        HashMap<String, Object> defaultParameters = this.getConsole().getForm().getParametersForCommand();

        for (Field field : fillableFields) {
            if (defaultParameters.containsKey(field.getName())) {
                setterParameterForCommand(command,cmd, field, defaultParameters.get(field.getName()));
            }
        }

        setterParameterForCommand(command, cmd, individualField, name);

        return cmd;
    }

    private void setterParameterForCommand(Class<? extends Command> command, Command cmd, Field field, Object parameter) {
        try {
            String methodName = "set" + capitalized(field.getName());
            Method setMethod = command.getMethod(methodName, field.getType());
            setMethod.invoke(cmd, parameter);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static String capitalized(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1);
    }
}
