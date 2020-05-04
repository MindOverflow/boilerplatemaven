package ru.sberbank.mavenboilerplateapp;

import constants.IKafkaConstants;
import consumer.ConsumerCreator;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import producer.ProducerCreator;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

/**
 * Hello World my dear Slf4j!
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("psvm() Запущена!!!");
        //runProducer();
        runConsumer();
    }

    static void runConsumer() {
        KafkaConsumer<Long, String> consumer = ConsumerCreator.createConsumer();

        while (true) {
            ConsumerRecords<Long, String> records = consumer.poll(Duration.ofMillis(10000));
            for (ConsumerRecord<Long, String> record : records) {
                log.info("Топик записи: " + record.topic());
                log.info("Смещение записи: " + record.offset());
                log.info("Ключ сообщения: " + record.key());
                log.info("Сообщение: " + record.value());
            }
        }
    }

    static void runProducer() {
        Producer<Long, String> producer = ProducerCreator.createProducer();

        for (long index = 10; index < 20; index++) {
            ProducerRecord<Long, String> record = new ProducerRecord<Long, String>("test", index, "This is record I sent " + index);
            producer.send(record);
        }
        producer.close();
    }
}
