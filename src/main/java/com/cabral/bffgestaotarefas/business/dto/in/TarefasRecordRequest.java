package com.cabral.bffgestaotarefas.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TarefasRecordRequest(String nomeTarefa,
                                   String descricao,
                                   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                                      LocalDateTime dataEvento) {
}
