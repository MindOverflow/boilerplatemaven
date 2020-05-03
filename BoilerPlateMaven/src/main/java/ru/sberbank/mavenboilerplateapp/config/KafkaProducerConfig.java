package ru.sberbank.mavenboilerplateapp.config;

import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.sberbank.mavenboilerplateapp.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация производителя сообщений в топик Kafka
 */
@Configuration
public class KafkaProducerConfig {
    private String kafkaServer = "localhost:9092";

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        // Сериализовать ключ сообщения как Long тип
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        // Сериализовать сообщение, как тип String
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<Long, User> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean(name="kafkaTemplate")
    public KafkaTemplate<Long, User> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
