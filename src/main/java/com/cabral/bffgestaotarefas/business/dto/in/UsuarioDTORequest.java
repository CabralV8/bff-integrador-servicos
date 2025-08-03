package com.cabral.bffgestaotarefas.business.dto.in;


import com.cabral.bffgestaotarefas.business.dto.out.EnderecoDTOResponse;

import java.util.List;

public class UsuarioDTORequest {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOResponse> enderecos;
    private List<TelefoneDTORequest> telefones;

    public UsuarioDTORequest() {
    }

    public UsuarioDTORequest(String nome, String email, String senha, List<EnderecoDTOResponse> enderecos, List<TelefoneDTORequest> telefones) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.enderecos = enderecos;
        this.telefones = telefones;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public List<EnderecoDTOResponse> getEnderecos() {
        return enderecos;
    }

    public List<TelefoneDTORequest> getTelefones() {
        return telefones;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEnderecos(List<EnderecoDTOResponse> enderecos) {
        this.enderecos = enderecos;
    }

    public void setTelefones(List<TelefoneDTORequest> telefones) {
        this.telefones = telefones;
    }
}

