package com.cabral.bffgestaotarefas.business.dto.out;

import java.util.List;

public record UsuarioRecordResponse(String nome,
                                    String email,
                                    String senha,
                                    List<EnderecoRecordResponse> enderecos,
                                    List<TelefoneRecordResponse> telefones) {
}
