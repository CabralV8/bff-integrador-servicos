package com.cabral.bffgestaotarefas.business.dto.in;

public class TelefoneDTORequest {

    private String numero;
    private String ddd;

    public TelefoneDTORequest() {}

    public TelefoneDTORequest(String numero, String ddd) {
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
