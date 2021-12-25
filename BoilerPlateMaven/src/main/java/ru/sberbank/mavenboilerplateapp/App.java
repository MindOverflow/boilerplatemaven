package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;
import org.garret.perst.*;

import java.io.*;

/**
 * Hello World my dear Slf4j!
 */
@Slf4j
public class App extends Persistent {
    public static void main(String[] args) {
        log.info("Hello");
    }
}
