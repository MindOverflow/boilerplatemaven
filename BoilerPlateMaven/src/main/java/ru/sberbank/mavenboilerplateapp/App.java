package ru.sberbank.mavenboilerplateapp;


import lessons.LessonsConfiguration;
import lessons.services.GreetingService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Полное описание примера:
 * https://github.com/wertklop/spring-lessons.git
 * https://spring-projects.ru/guides/lessons/lesson-2/
 */
public class App 
{
    public static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) {
        logger.info("Старт конфигурации");

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(LessonsConfiguration.class);
        GreetingService greetingService = context.getBean(GreetingService.class);
        logger.info(greetingService.sayGreeting());
        context.registerShutdownHook();
    }
}
