package com.br.panacademy.devcompilers.bluebank.controller;

import com.br.panacademy.devcompilers.bluebank.dto.NotificationDTO;
import com.br.panacademy.devcompilers.bluebank.service.AwsSNSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/api/notification")
@Validated
public class AwsSNSController {

    @Autowired
    private AwsSNSService awsSNSService;

    @PostMapping("/newSubscription")
    public String newSubscription(@RequestParam(name = "email")
                                      @Email(message = "Campo e-mail inválido!") String emailClient) {
        return awsSNSService.newSubscription(emailClient);
    }

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestBody @Valid NotificationDTO notificationDTO) {
        return awsSNSService.sendNotification(notificationDTO);
    }
}
