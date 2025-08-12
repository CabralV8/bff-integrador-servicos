package com.cabral.bffgestaotarefas.business.dto.out;

public record ViaCepRecord(String cep,
                           String logradouro,
                           String complemento,
                           String unidade,
                           String bairro,
                           String localidade,
                           String uf,
                           String estado,
                           String regiao,
                           String ibge,
                           String gia,
                           String ddd,
                           String siafi) {
}
