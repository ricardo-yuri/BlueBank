package com.br.panacademy.devcompilers.bluebank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public class ContactDTO {

    private Long id;

    private String telephone;

    @NotEmpty
    private List<String> cell;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<String> getCell() {
        return cell;
    }

    public void setCell(List<String> cell) {
        this.cell = cell;
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
