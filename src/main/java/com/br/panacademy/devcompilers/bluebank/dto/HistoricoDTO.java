package com.br.panacademy.devcompilers.bluebank.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class HistoricoDTO {

    private Long id;

    @NotEmpty
    private String log;

    @NotEmpty
    private String tipo;

    private LocalDateTime createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
