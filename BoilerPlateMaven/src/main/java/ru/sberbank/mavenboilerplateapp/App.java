package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * Hello World my dear Slf4j!
 */
@Slf4j
public class App {
    public static void main(String[] args) throws Exception {
        log.info("Hello World!");
        Method square = App.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) {
        // Вывод названия метода в качестве заголовка таблицы:
        System.out.println(f);
        double dx = (to - from) / (n - 1);
        for (double x = from; x <= to ; x += dx) {
            try {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
