package com.cabral.bffgestaotarefas.business.dto.out;


import java.util.List;

public class UsuarioDTOResponse {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOResponse> enderecos;
    private List<TelefoneDTOResponse> telefones;

    public UsuarioDTOResponse() {
    }

    public UsuarioDTOResponse(String nome, String email, String senha, List<EnderecoDTOResponse> enderecos, List<TelefoneDTOResponse> telefones) {
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

    public List<TelefoneDTOResponse> getTelefones() {
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

    public void setTelefones(List<TelefoneDTOResponse> telefones) {
        this.telefones = telefones;
    }
}

