package com.quinbay.messaging.Service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.annotation.*;
import org.springframework.mail.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageService {

    @Autowired
    private JavaMailSender mailSender;


//    @GetMapping("/email")
    @KafkaListener(topics = "confirm-message",groupId = "order-group",containerFactory = "kafkaListenerContainerFactory")
    public String sentEmail(String email)
    {
        System.out.println(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("confirm order");
        message.setText("your order is placed successfully");
        message.setFrom("naresh21kumar12@gmail.com");
        mailSender.send(message);
        return "done";
    }
}
