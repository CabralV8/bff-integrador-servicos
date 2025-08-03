package com.cabral.bffgestaotarefas.infrastructure.client;


import com.cabral.bffgestaotarefas.business.dto.in.TarefasDTORequest;
import com.cabral.bffgestaotarefas.business.dto.out.TarefasDTOResponse;
import com.cabral.bffgestaotarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "gestor-tarefas", url = "${gestor-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest tarefasDTO,
                                     @RequestHeader("Authorization") String token);


    @GetMapping("/events")
    List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);


    @GetMapping
    List<TarefasDTOResponse> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefaPorId(@RequestParam("id") String id,
                        @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alterarStaturNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                @RequestParam("id") String id,
                                                @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse atualizarTarefas(@RequestBody TarefasDTOResponse dto,
                                        @RequestParam("id") String id,
                                        @RequestHeader("Authorization") String token);
}
