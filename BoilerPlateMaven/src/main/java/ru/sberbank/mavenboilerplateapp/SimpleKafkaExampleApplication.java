package ru.sberbank.mavenboilerplateapp;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import ru.sberbank.mavenboilerplateapp.model.User;

@Slf4j
@EnableKafka // Так помечается класс в котором будет создаваться слушатель топика (консьюмер)
public class SimpleKafkaExampleApplication {
    /**
     * @method
     *     Слушатель топика "msg"
     * @param record
     *     Сообщение, поступающее из топика "msg"
     */
    @KafkaListener(topics="msg")
    public void msgListener(ConsumerRecord<Long, User> record) {
        log.info("Название топика: " + record.topic());
        log.info("Партиция сообщения в топике: " + record.partition());
        log.info("Ключ сообщения в топике: " + record.key());
        log.info("Значение сообщения из словаря: " + record.value());
    }
}
