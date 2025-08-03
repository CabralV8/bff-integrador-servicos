package com.cabral.bffgestaotarefas.business.dto.out;

public class TelefoneDTOResponse {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String numero;
    private String ddd;

    public TelefoneDTOResponse() {
    }

    public TelefoneDTOResponse(String numero, String ddd) {
        this.numero = numero;
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
}
