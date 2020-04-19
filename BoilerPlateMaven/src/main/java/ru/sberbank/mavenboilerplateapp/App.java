// Как я добавлял логирование в данной ветке
// 01) http://www.srccodes.com/p/article/21/logback-using-slf4j-hello-world-example
// Статья, которая поможет понять, как читается файл в массив байтов
// 02) http://tutorials.jenkov.com/java-io/fileinputstream.html

package ru.sberbank.mavenboilerplateapp;

import javax.swing.*;
import java.awt.*;

/**
 * Данная программа вычисляет цифровую подпись сообщения сообщения
 * для файла или собержимого текстовой области.
 */

public class App
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            JFrame jFrame = new MessageDigestFrame();
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
            }
        });
    }
}

