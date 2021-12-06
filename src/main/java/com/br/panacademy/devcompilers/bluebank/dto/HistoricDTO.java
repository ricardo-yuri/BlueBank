package com.br.panacademy.devcompilers.bluebank.dto;

import com.br.panacademy.devcompilers.bluebank.enums.OperationType;

import java.time.LocalDateTime;

public class HistoricDTO {

    private Long id;
    private String log;
    private OperationType operationType;
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

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
