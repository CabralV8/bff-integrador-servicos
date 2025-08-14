package com.cabral.bffgestaotarefas.business.dto.in;

import com.cabral.bffgestaotarefas.business.dto.out.EnderecoRecordResponse;
import com.cabral.bffgestaotarefas.business.dto.out.TelefoneRecordResponse;

import java.util.List;

public record UsuarioRecordRequest(String nome,
                                   String email,
                                   String senha,
                                   List<EnderecoRecordResponse> enderecos,
                                   List<TelefoneRecordResponse> telefones) {
}
