package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@EnableKafka // Так помечается класс в котором будет создаваться слушатель топика (консьюмер)
public class SimpleKafkaExampleApplication {
    /**
     * @method
     *     Слушатель топика "msg"
     * @param msg
     *     Сообщение, поступающее из топика "msg"
     */
    @KafkaListener(topics="msg")
    public void msgListener(String msg) {
        log.info("Сообщение, которое прочитал подписчик топика msg: " + msg);
    }
}
