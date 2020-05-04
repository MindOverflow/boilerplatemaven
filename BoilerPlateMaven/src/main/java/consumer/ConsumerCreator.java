package consumer;

import constants.IKafkaConstants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class ConsumerCreator {
    public static KafkaConsumer<Long, String> createConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "test");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);
        // Для того чтобы потребитель утвержадл самостоятельно значение смещения в партиции топика
        properties.put("enable.auto.commit", "true");

        KafkaConsumer<Long, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("test"));

        return consumer;
    }
}
