package com.cabral.bffgestaotarefas.infrastructure.client;


import com.cabral.bffgestaotarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servico-notificacao", url = "${servico.notificacao.url}")
public interface EmailClient {

    @PostMapping("/email")
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}
