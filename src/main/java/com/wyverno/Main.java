package com.wyverno;

import com.wyverno.console.Console;
import com.wyverno.console.control.StopCommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Console console = new Console();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                console.readingLine(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof StopCommandException) {
                System.out.println("Останавливаем работу");
            } else {
                e.getTargetException().printStackTrace();
            }
        }
    }
}
