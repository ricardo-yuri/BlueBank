package com.br.panacademy.devcompilers.bluebank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public class ContactDTO {

    private Long id;
    private String telefone;

    @NotEmpty
    private List<String> celular;
    @NotEmpty(message = "O campmo e-mail não pode ser vazio ou nulo!")
    @Email
    private String email;
    private LocalDateTime createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> getCelular() {
        return celular;
    }

    public void setCelular(List<String> celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
