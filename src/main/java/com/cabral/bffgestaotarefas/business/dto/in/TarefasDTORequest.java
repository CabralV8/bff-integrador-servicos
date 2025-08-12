package com.cabral.bffgestaotarefas.business.dto.in;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TarefasDTORequest {

    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;

    public TarefasDTORequest() {
    }

    public TarefasDTORequest(String nomeTarefa, String descricao, LocalDateTime dataEvento) {
        this.nomeTarefa = nomeTarefa;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }
}
