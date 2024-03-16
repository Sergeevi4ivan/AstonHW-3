package ru.panas.APizzanotificationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.panas.APizzanotificationService.entity.User;

import java.util.UUID;

@Service
public class EmailNotificationService {

    private final WebClient webClient;
    private final String NOTIFICATION_FROM = "APizza";
    private final String NOTIFICATION_SUBJECT = "Notification";
    private final String BASE_URL = "http://localhost:8080/api";

    @Autowired
    private JavaMailSender mailSender;

    public EmailNotificationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public void sendNotification(String toEmail, String body) {

//        (String toEmail, String subject, String body)  аргументы метода (возможно верну)

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(NOTIFICATION_FROM);
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(NOTIFICATION_SUBJECT);
        mailMessage.setText(body);

        mailSender.send(mailMessage);

        System.out.println("Email sent successfully!");
    }

    public String getEmailById(UUID id) {

//        UUID id = order.getId();

        Mono<User> monoUser = this.webClient.get().uri("user/{id}", id)
                .retrieve()
                .bodyToMono(User.class);


        return monoUser.map(User::getEmail).toString();
    }
}
