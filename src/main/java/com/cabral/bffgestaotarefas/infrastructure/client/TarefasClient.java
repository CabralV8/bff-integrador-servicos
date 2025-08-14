package com.cabral.bffgestaotarefas.infrastructure.client;


import com.cabral.bffgestaotarefas.business.dto.in.TarefasRecordRequest;
import com.cabral.bffgestaotarefas.business.dto.out.TarefasRecordResponse;
import com.cabral.bffgestaotarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "gestor.tarefas", url = "${gestor.tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasRecordResponse gravarTarefas(@RequestBody TarefasRecordRequest tarefasDTO,
                                     @RequestHeader("Authorization") String token);


    @GetMapping("/eventos")
    List<TarefasRecordResponse> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);


    @GetMapping
    List<TarefasRecordResponse> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefaPorId(@RequestParam("id") String id,
                        @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasRecordResponse alterarStaturNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                @RequestParam("id") String id,
                                                @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasRecordResponse atualizarTarefas(@RequestBody TarefasRecordResponse dto,
                                        @RequestParam("id") String id,
                                        @RequestHeader("Authorization") String token);
}
