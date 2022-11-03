package com.wyverno.console.control.commands;

import com.wyverno.commands.annotations.FillableParameter;
import com.wyverno.console.Console;

import java.lang.reflect.Field;
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

            HashMap<Field,Object> listField = this.getConsole().getForm().getParametersForCommand();

            Field[] fields = this.getFillableField(this.getConsole()
                                                  .getForm()
                                                  .getType()
                                                  .getCLAZZ());


        } else {
            System.out.println("Пожалуйта выберите тип команды /settype");
        }
    }

    private Field[] getFillableField(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();

        for (Field field : clazz.getFields()) {
            if (field.isAnnotationPresent(FillableParameter.class)) {
                fieldList.add(field);
            }
        }

        return fieldList.toArray(new Field[0]);
    }
}
