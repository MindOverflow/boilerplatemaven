// TODO: Как я добавлял логирование в данной ветке
// http://www.srccodes.com/p/article/21/logback-using-slf4j-hello-world-example

package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Данная программа вычисляет дайджест сообщения
 * для файла или собержимого текстовой области.
 */

public class MessageDigestTest
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // TODO: Присвоить класс
                JFrame jFrame = null;
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);
            }
        });
    }



}

/**
 * Фрейм, собержащий меню, посредством которого запускается процесс вычисления профиля сообщения для текстового файла
 * или содержимого текстовой области, переключатели для выбора алгоритма SHA-1 или MD5, текстовую область, а также поле
 * редактирования для отображения профиля сообщения.
 */
@Slf4j
class MessageDigestFrame extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(MessageDigestFrame.class);
    // Параметры окна:
    // Ширина окна
    private static final int DEFAULT_WIDTH = 400;
    // Длина окна
    private static final int DEFAULT_HEIGHT = 300;

    // Сообщение, по которому потом будет вычисляться цифровая подпись
    private JTextArea message = new JTextArea();
    // Текстовое поле
    private JTextField digest = new JTextField();
    // Текущий алгоритм вычисления цифровой подписи
    private MessageDigest currentAlgorithm;

    /**
     * ctor
     */
    public MessageDigestFrame() {
        log.info("ctor MessageDigestFrame(); Hello Lombok Log! :-)");
        setTitle("MessageDigestFrame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JPanel jPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        addRadioButton(jPanel, "SHA-1", buttonGroup);
        addRadioButton(jPanel, "MD5", buttonGroup);
    }

    /**
     * Добавление переключателей, предназначенных для выбора алгоритма
     * @param container контейнер для размещения переключателей
     * @param name имя алгоритма
     * @param buttonGroup группа переключателей
     */
    public void addRadioButton(Container container, final String name, ButtonGroup buttonGroup) {
        logger.info("MessageDigestFrame.()");

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setAlgorithm(name);
            }
        };

        JRadioButton jRadioButton = new JRadioButton(name, buttonGroup.getButtonCount() == 0);
        container.add(jRadioButton);
        buttonGroup.add(jRadioButton);
        jRadioButton.addActionListener(listener);
    }

    /**
     * Установка алгоритма, используемого для вычисления отпечатка сообщения
     * @param alg имя используемого алгоритма для шифрования сообщения
     */
    public void setAlgorithm(String alg) {
        logger.info("MessageDigestFrame.setAlgorithm(); alg = " + alg);
        try {
            currentAlgorithm = MessageDigest.getInstance(alg);
            digest.setText("");
        }
        catch (NoSuchAlgorithmException e) {
            logger.error("Ошибка в MessageDigestFrame.setAlgorithm() ", e);
            digest.setText("" + e);
        }
    }
}

