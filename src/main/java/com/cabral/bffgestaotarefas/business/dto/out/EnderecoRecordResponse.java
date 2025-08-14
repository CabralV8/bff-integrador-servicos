package com.cabral.bffgestaotarefas.business.dto.out;

public record EnderecoRecordResponse(Long id,
                                     String rua,
                                     String numero,
                                     String complemento,
                                     String cidade,
                                     String estado,
                                     String cep) {
}
