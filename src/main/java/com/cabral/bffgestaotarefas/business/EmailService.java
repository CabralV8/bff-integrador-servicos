package com.cabral.bffgestaotarefas.business;

import com.cabral.bffgestaotarefas.business.dto.out.TarefasDTOResponse;
import com.cabral.bffgestaotarefas.infrastructure.client.EmailClient;

public class EmailService {

    private final EmailClient emailClient;

    public EmailService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public void enviarEmail(TarefasDTOResponse dto) {
        emailClient.enviarEmail(dto);
    }
}