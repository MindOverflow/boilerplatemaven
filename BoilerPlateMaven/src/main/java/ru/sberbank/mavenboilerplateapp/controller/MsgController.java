package ru.sberbank.mavenboilerplateapp.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import ru.sberbank.mavenboilerplateapp.model.User;

// Контроллер мапится на localhost:8080/msg

@Slf4j
@RestController
public class MsgController {
    // Чтобы отправлять сообщения, требуется объект KafkaTemplate<K, V>
    // V - в данном случае будет тип сообщения продюсера
    @Autowired
    private KafkaTemplate<Long, User> kafkaTemplate;

    /**
     * @method
     *     отправляет полученны параметры с клиента в сообщение с ключом и текстом сообещения
     // * @param msgId - ключ сообщения
     * @param msg - сообщение, которое публикует издатель
     *     {
     *         "age": 32,
     *         "name": "Anton",
     *         "address": {
     *             "country": "Russia",
     *             "city": "Obninsk",
     *             "street": "Gogol",
     *             "homeNumber": 4,
     *             "flatNumber": 11
     *         }
     *     }
     */
    @PostMapping(value="/msg/{messageId}")
    public void sendMessage(@PathVariable("messageId") Long messageId, @RequestBody User user) {
        log.info("Идентификатор сообщения: " + messageId);
        log.info("Сообщение с клиента: " + user);

        // "msg" - название топика, который может не существовать. Если не существует, создаётся автоматом.
        ListenableFuture<SendResult<Long, User>> future = kafkaTemplate.send("msg", messageId, user);

        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
