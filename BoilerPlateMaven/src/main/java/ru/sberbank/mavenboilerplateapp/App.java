// Пример взят из статьи:
// https://m.habr.com/ru/post/496182/
// https://habr.com/ru/post/496182/
package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello World my dear Kafka!
 */
@Slf4j
@SpringBootApplication()
public class App {
    public static void main(String[] args) {
        log.info("Инициализация в методе main приложения");
        SpringApplication.run(App.class, args);
    }
}
