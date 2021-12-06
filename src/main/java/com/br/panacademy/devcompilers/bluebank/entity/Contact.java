package com.br.panacademy.devcompilers.bluebank.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Long id;
    @Column
    private String telephone;
    @Column(nullable = false)
    @ElementCollection
    private List<String> cell;
    private String email;
    @CreationTimestamp
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
