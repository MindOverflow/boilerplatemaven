package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * Hello World my dear Slf4j!
 */
@Slf4j
public class ShowMethods {
    private static String usage = "usage:\nShowMethods qualified.class.name\nTo show all methods in class or:\nShowMethods qualified.class.name word\nTo search for methods involving 'word'";
    // Здесь ищется такой паттерн: \w+\., что означает: \w - буквы и цифры или подчёркивание; + - плюс означает любое количество повторений правила; \. - означает символ точки
    private static Pattern p = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.print(usage);
            System.exit(0);
        }
        int lines = 0;

        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();

            if (args.length == 1) {
                for (Method method : methods) {
                    System.out.println(p.matcher(method.toString()).replaceAll(""));
                }
                for (Constructor ctor : ctors) {
                    System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                }
                lines = methods.length + ctors.length;
            } else {
                for (Method method : methods) {
                    if (method.toString().indexOf(args[1]) != -1) {
                        System.out.println(p.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }
                }
                for (Constructor ctor : ctors) {
                    if (ctor.toString().indexOf(args[1]) != -1) {
                        System.out.println(p.matcher(ctor.toString()).replaceAll(""));
                        lines++;
                    }
                }
            }
        } catch(ClassNotFoundException e) {
            System.out.println("No such class: " + e);
        }
    }
}
