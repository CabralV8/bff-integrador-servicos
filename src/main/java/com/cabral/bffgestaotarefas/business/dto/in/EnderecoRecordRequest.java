package com.cabral.bffgestaotarefas.business.dto.in;

public record EnderecoRecordRequest(String rua,
                                    String numero,
                                    String complemento,
                                    String cidade,
                                    String estado,
                                    String cep) {
}
