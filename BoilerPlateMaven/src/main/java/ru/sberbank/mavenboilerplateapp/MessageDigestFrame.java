package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Фрейм, собержащий меню, посредством которого запускается процесс вычисления профиля сообщения для текстового файла
 * или содержимого текстовой области, переключатели для выбора алгоритма SHA-1 или MD5, текстовую область, а также поле
 * редактирования для отображения профиля сообщения.
 */
@Slf4j
class MessageDigestFrame extends JFrame {
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
        log.info("ctor MessageDigestFrame();");

        setTitle("Вычисление цифрового отпечатака сообщения");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Панель, содержащая кнопки выбора метода шифрования
        JPanel jPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        addRadioButton(jPanel, "SHA-1", buttonGroup);
        addRadioButton(jPanel, "MD5", buttonGroup);

        // Добавление элементов управления на окно
        add(jPanel, BorderLayout.NORTH);
        add(new JScrollPane(message), BorderLayout.CENTER);
        add(digest, BorderLayout.SOUTH);
        digest.setFont(new Font("Monospaced", Font.PLAIN, 12));

        setAlgorithm("SHA-1");

        // Панель управления окном приложения:
        JMenuBar jMenuBar = new JMenuBar();
        // Элемент "Меню" окна "File"
        JMenu jMenuFile = new JMenu("File");
        // Элемент "File Digest" в составе элемента "Меню" окна "File"
        JMenuItem jMenuItemFileDigest = new JMenuItem("File Digest");
        // Элемент "Техт Area Digest" в составе элемента "Меню" окна "File"
        JMenuItem jMenuItemTextAreaDigest = new JMenuItem("Техт Area Digest");

        // Обработчики нажатия на элемент меню "File" -> "File Digest" и "File" -> "Техт Area Digest"
        jMenuItemFileDigest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadFile();
            }
        });
        jMenuItemTextAreaDigest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String messageText = message.getText();
                byte[] byteArrayFromMessageText = messageText.getBytes();
                computeDigest(byteArrayFromMessageText);
            }
        });

        //
        jMenuFile.add(jMenuItemFileDigest);
        jMenuFile.add(jMenuItemTextAreaDigest);

        jMenuBar.add(jMenuFile);

        setJMenuBar(jMenuBar);
    }

    /**
     * Добавление переключателей, предназначенных для выбора алгоритма
     * @param container контейнер для размещения переключателей
     * @param name имя алгоритма
     * @param buttonGroup группа переключателей
     */
    private void addRadioButton(Container container, final String name, ButtonGroup buttonGroup) {
        log.info("MessageDigestFrame.addRadioButton()");

        ActionListener listener = new ActionListener() {
            @Override
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
    private void setAlgorithm(String alg) {
        log.info("MessageDigestFrame.setAlgorithm(); alg = " + alg);
        try {
            currentAlgorithm = MessageDigest.getInstance(alg);
            digest.setText("");
        }
        catch (NoSuchAlgorithmException e) {
            log.error("Ошибка в MessageDigestFrame.setAlgorithm() ", e);
            digest.setText("" + e);
        }
    }

    /**
     * Метод-обработчик загрузки файла. Одновременно вычисляет цифровой отпечаток сообщения
     */
    private void loadFile() {
        log.info("MessageDigestFrame.loadFile()");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("."));

        int selectedOption = jFileChooser.showOpenDialog(this);

        if (selectedOption == JFileChooser.APPROVE_OPTION) {
            log.info("MessageDigestFrame.loadFile(); JFileChooser.APPROVE_OPTION");
            try {
                String absoluteFilePath = jFileChooser.getSelectedFile().getAbsolutePath();
                log.info("MessageDigestFrame.loadFile(); Абсолютный путь файла absoluteFilePath = " + absoluteFilePath);
                computeDigest(loadBites(absoluteFilePath));
            } catch (IOException e) {
                log.error("Ошибка в MessageDigestFrame.loadFile(): ", e);
                JOptionPane.showMessageDialog(null, e);
            }
        }
        if (selectedOption == JFileChooser.CANCEL_OPTION) {
            log.info("MessageDigestFrame.loadFile(); JFileChooser.APPROVE_OPTION");
        }
    }

    /**
     * @param absoluteFilePath  Абсолютный путь до загружаемого файла
     * @throws IOException Выбрасывает исключение, если не удалось найти файл по указнанному абсолютному пути
     * @return Байтовый массив
     */
    private byte[] loadBites(String absoluteFilePath) throws IOException {
        log.info("MessageDigestFrame.loadBites()");
        FileInputStream fileInputStream = new FileInputStream(absoluteFilePath);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            /*
             * The read() method of a FileInputStream returns an int which contains the byte value of the byte read.
             * If the read() method returns -1, there is no more data to read in the FileInputStream, and it can be
             * closed. That is, -1 as int value, not -1 as byte value. There is a difference here!
             */
            // Считываем начальный байт файла.
            int streamByte = fileInputStream.read();
            log.info("MessageDigestFrame.loadBites(); streamByte = " + streamByte);

            while (streamByte != -1) {
                byteArrayOutputStream.write(streamByte);
                streamByte = fileInputStream.read(); // Считать следующий байт из файла
                log.info("MessageDigestFrame.loadBites(); streamByte = " + streamByte);
            }

            // Кнвертировать байт поток в массив.
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            log.info("MessageDigestFrame.loadBites(); Число байт в массиве = " + byteArray.length);

            return byteArray;
        }
        finally {
            log.info("MessageDigestFrame.loadBites(); Выполнение блока finally");
            fileInputStream.close();
        }
    }

    /**
     * Вычисление цифровой подписи
     * @param bytes байтовый массив, для которого вычисляется цифровая подпись
     */
    private void computeDigest(byte[] bytes) {
        log.info("MessageDigestFrame.computeDigest(); Переданный массив байтов bytes = " + Arrays.toString(bytes));
        // Сброс цифрового отпечатка в исходное состояние
        currentAlgorithm.reset();
        // Обновление исходными данными для расчёта ноовй цифровой подписи
        currentAlgorithm.update(bytes);
        // Вычисление новой цифровой подписи с применением указаного алгоритма
        byte[] hash = currentAlgorithm.digest();
        log.info("MessageDigestFrame.computeDigest(); Длина hash массива байтов = " + hash.length);
        // Строковое представление цифровой подписи, которое необходимо вывести на экран в окно нашей программы
        String hexadecimalNumberStringRepresentation = "";
        for (byte b : hash) {
            log.info("MessageDigestFrame.computeDigest(); байто = " + b);
            int value = b & 0xFF;
            log.info("MessageDigestFrame.computeDigest(); Битовое \"И\": " + b);
            // Если в результате битового "И" величины hash[i] и числа 0xFF (255) -- это по сути 16 бит, занятых
            // единицами. Если вычисленное значение в целом десятичном представлении меньше 16, то тогда необходимо
            // добавить в строковом представлении нолик вперёд полченного значения. В шестнадцатеричных F то же,
            // что и 15
            if (value < 16) {
                hexadecimalNumberStringRepresentation += "0";
            }
            // 16 - основание системы счисления, к которому нужно привести переданное число, прежде чем оно будет
            // конвертировано в строковое представление
            hexadecimalNumberStringRepresentation += Integer.toString(value, 16).toUpperCase() + " ";

            log.info("MessageDigestFrame.computeDigest();\n hexadecimalNumberStringRepresentation = " +
                    hexadecimalNumberStringRepresentation);

            digest.setText(hexadecimalNumberStringRepresentation);
        }
    }
}
