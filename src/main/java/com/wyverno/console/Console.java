package com.wyverno.console;

import com.wyverno.console.control.Commands;
import com.wyverno.console.control.ConsoleCommand;
import com.wyverno.console.control.form.Form;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class Console {

    private final HashMap<String, Method> commands = new HashMap<>();

    public final Form form = new Form(new ArrayList<>());

    public Console() {
        Method[] methods = Commands.class.getDeclaredMethods();

        for (Method value : methods) {
            if (value.isAnnotationPresent(ConsoleCommand.class)) {
                String command =
                        value.getAnnotation(ConsoleCommand.class)
                             .command();
                commands.put(command, value);
            }
        }
    }

    public void readingLine(String line) throws InvocationTargetException, IllegalAccessException {
        String userCommand = substringUserCommand(line);
        if (commands.containsKey(userCommand)) {
            Method method = commands.get(userCommand);

            if (method.getParameterCount() > 1) {
                method.invoke(null,this, substringUserArgs(line).trim());
            } else {
                method.invoke(null, this);
            }
        } else {
            System.out.println("Нету такой команды напишите: \"/help\"");
        }
    }

    private static String substringUserCommand(String line) {
        if (line.contains(" ")) return line.substring(0, line.indexOf(" "));
        return line;
    }

    private static String substringUserArgs(String line) {
        if (line.contains(" ")) return line.substring(line.indexOf(" "));
        return line;
    }

    public Form getForm() {
        return form;
    }
}
