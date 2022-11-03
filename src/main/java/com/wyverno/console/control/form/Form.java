package com.wyverno.console.control.form;

import com.wyverno.commands.Command;
import com.wyverno.commands.annotations.FillableParameter;
import com.wyverno.commands.CommandsType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;

public class Form {

    private CommandsType type;
    private HashMap<Field,Object> parametersForCommand;

    private List<Command> commands;

    public Form(List<Command> commands) {
        this.commands = commands;
    }

    public void setType(CommandsType type) {
        if (this.type != null && this.type.equals(type)) {
            System.out.println("Вы пытаетесь установить тип который сейчас установлен.");
        } else {
            this.type = type;
            System.out.println("Новый тип наказания установлен - " + type.getNAME_COMMAND());
            changeParameters();
        }
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public CommandsType getType() {
        return type;
    }

    public boolean isHaveType() {
        return this.type != null;
    }

    public HashMap<Field, Object> getParametersForCommand() {
        return this.parametersForCommand;
    }

    private void changeParameters() {
        Field[] fields = this.type.getCLAZZ().getFields();
        HashMap<Field,Object> fieldObjectHashMap = new HashMap<>();
        if (hasFillableParameters(fields)) {
            System.out.println("Новый тип который вы выбрали нужно ввести параметры для наказания.");

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                for (Field field : getFillableParameters(fields)) {
                    System.out.print("Введите значения - '" + ShortNameParameter
                            .getTranslateNameTypeParameter(field.getType().getSimpleName())
                            + "' за что отвечает - '" +
                            ShortNameParameter.getTranslateNameTypeParameter(field.getName()) + "': ");
                    String line = reader.readLine();

                    if (field.getType().equals(int.class)) {
                        int number = Integer.parseInt(line);
                        fieldObjectHashMap.put(field,number);
                        continue;
                    }
                    fieldObjectHashMap.put(field,line);
                }

                System.out.println("Новые параметры установлены.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.parametersForCommand = fieldObjectHashMap;
    }

    private static boolean hasFillableParameters(Field[] fields) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(FillableParameter.class)) {
                return true;
            }
        }
        return false;
    }

    private static Field[] getFillableParameters(Field[] fields) {
        List<Field> fieldList = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(FillableParameter.class)) {
                fieldList.add(field);
            }
        }

        return fieldList.toArray(new Field[0]);
    }
}
