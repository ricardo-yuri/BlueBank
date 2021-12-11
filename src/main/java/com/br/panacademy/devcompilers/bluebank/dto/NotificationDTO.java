package com.br.panacademy.devcompilers.bluebank.dto;

import javax.validation.constraints.NotEmpty;

public class NotificationDTO {

    @NotEmpty(message = "O campo subject não pode ser vazio nulo.")
    private String subject;

    @NotEmpty(message = "O campo subject não pode ser vazio nulo.")
    private String message;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
