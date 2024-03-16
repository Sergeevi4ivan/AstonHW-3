package ru.panas.APizzanotificationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.panas.APizzanotificationService.entity.Order;
import ru.panas.APizzanotificationService.service.EmailNotificationService;

import java.util.UUID;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final EmailNotificationService emailNotificationService;

    @Autowired
    public NotificationController(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> sendEmail(@RequestBody Order order) {
        order.getId(); // использовать для запроса к сервису пользователей
        String emailForSend = emailNotificationService.getEmailById(order.getId());
                        // вызвать метод отправки почты (почту взять из ответа от запроса выше)
        emailNotificationService.sendNotification(emailForSend, "Some body for user");




        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getEmailById(@PathVariable UUID id) {
        return emailNotificationService.getEmailById(id);
    }




}
