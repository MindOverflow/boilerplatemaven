package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public class App {
    public static void main(String[] args) {
        String name;
        if (args.length > 0) {
            name = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Collection): ");
            name = in.next();
        }

        try {
            Class clazz = Class.forName(name);
            printClass(clazz);
            for (Method method : clazz.getDeclaredMethods()) {
                printMethod(method);
            }
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    public static void printClass(Class clazz) {
        System.out.print(clazz);
        printTypes(clazz.getTypeParameters(), "<", ", ", ">", true);
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass != null) {
            System.out.print(" extends ");
            printType(genericSuperclass, false);
        }
        printTypes(clazz.getGenericInterfaces(), " implements ", ", ", "", false);
        System.out.println();
    }

    public static void printMethod(Method method) {
        String name = method.getName();
        System.out.print(Modifier.toString(method.getModifiers()));
        System.out.print(" ");
        printTypes(method.getTypeParameters(), "<", ", ", ">", true);
        printType(method.getGenericReturnType(), false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(method.getGenericParameterTypes(), "", ", ", "", false);
        System.out.println(")");
    }

    public static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if (pre.equals(" extends ") && Arrays.equals(types, new Type[] { Object.class })) {
            return;
        }
        if (types.length > 0) {
            System.out.print(pre);
        }
        for (int i = 0; i < types.length; i++) {
            if (i > 0) {
                System.out.print(sep);
                printType(types[i], isDefinition);
            }
        }
        if (types.length > 0) {
            System.out.print(suf);
        }
    }

    public static void printType(Type type, boolean isDefinition) {
        if (type instanceof Class) {
            Class clazz = (Class) type;
            System.out.print(clazz.getName());
        } else if (type instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type;
            System.out.print(typeVariable.getName());
            if (isDefinition) {
                printTypes(typeVariable.getBounds(), " extends ", " & ", "", false);
            }
        } else if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            System.out.print("?");
            printTypes(wildcardType.getUpperBounds(), " extends ", " & ", "", false);
            printTypes(wildcardType.getLowerBounds(), " super ", " & ", "", false);
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type owner = parameterizedType.getOwnerType();
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(parameterizedType.getRawType(), false);
            printTypes(parameterizedType.getActualTypeArguments(), "<", ", ", ">", false);
        } else if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            System.out.print("");
            printType(genericArrayType.getGenericComponentType(), isDefinition);
            System.out.print("[]");
        }
    }
}
