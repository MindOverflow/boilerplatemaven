package ru.sberbank.mavenboilerplateapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
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
    private KafkaTemplate<Long, String> kafkaTemplate;

    /**
     * @method
     *     отправляет полученны параметры с клиента в сообщение с ключом и текстом сообещения
     * @param msgId - ключ сообщения
     * @param msg - сообщение, которое публикует издатель
     */
    @PostMapping
    public void sendMessage(Long msgId, String msg) {
        log.info("Идентификатор сообщения: " + msgId);
        log.info("Сообщение с клиента: " + msg);

        // "msg" - название топика, который может не существовать. Если не существует, создаётся автоматом.
        ListenableFuture<SendResult<Long, String>> future = kafkaTemplate.send("msg", msgId, msg);

        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
