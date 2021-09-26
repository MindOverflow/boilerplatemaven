package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;

/**
 * Hello World my dear Slf4j!
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("Hello World!");
        int[] a = { 1, 2, 3 };
        a = (int[]) goodArrayGrow(a);
        arrayPrint(a);
        String[] b = { "Tom", "Dick", "Harry" };
        b = (String[]) goodArrayGrow(b);
        arrayPrint(b);
        System.out.println("Следующий вызов сгенерирует исключение");
        b = (String[]) badArrayGrow(b);
    }

    static Object[] badArrayGrow(Object[] a) {
        int newLength = a.length * 11 / 10 + 10;
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, a.length);
        return newArray;
    }

    static Object goodArrayGrow(Object a) {
        Class cl = a.getClass();
        if (!cl.isArray()) return null;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        int newLength = length * 11 / 10 + 10;
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, length);
        return newArray;
    }

    static void arrayPrint(Object a) {
        Class cl = a.getClass();
        if (!cl.isArray()) return;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        System.out.println(componentType.getName() + "[" + length + "] = {" );
        for (int i = 0; i < Array.getLength(a); i++) {
            System.out.println(Array.get(a, i) + " ");
        }
        System.out.println("}");
    }
}
