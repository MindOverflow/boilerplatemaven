package ru.sberbank.mavenboilerplateapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Контроллер мапится на localhost:8080/msg

@Slf4j
@RestController
@RequestMapping("msg")
public class MsgController {
    // Чтобы отправлять сообщения, требуется объект KafkaTemplate<K, V>
    // V - в данном случае будет тип сообщения продюсера
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * @method
     * @param msgId - ключ сообщения
     * @param msg - сообщение, которое публикует издатель
     */
    @PostMapping
    public void sendOrder(String msgId, String msg) {
        log.info("Идентификатор сообщения: " + msgId);
        log.info("Сообщение с клиента: " + msg);
        // "msg" - название топика, который может не существовать. Если не существует, создаётся автоматом.
        kafkaTemplate.send("msg", msgId, msg);
    }
}
