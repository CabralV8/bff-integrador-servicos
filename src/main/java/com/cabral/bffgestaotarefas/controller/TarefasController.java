package com.cabral.bffgestaotarefas.controller;


import com.cabral.bffgestaotarefas.business.TarefasService;
import com.cabral.bffgestaotarefas.business.dto.in.TarefasRecordRequest;
import com.cabral.bffgestaotarefas.business.dto.out.TarefasRecordResponse;
import com.cabral.bffgestaotarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.cabral.bffgestaotarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @PostMapping
    @Operation(summary = "Criar tarefa para usuário.", description = "Cria e registra uma nova tarefa para o usuário.")
    @ApiResponse(responseCode = "200", description = "Taarefa registrada com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    public ResponseEntity<TarefasRecordResponse> gravarTarefas(@RequestBody TarefasRecordRequest dto,
                                                               @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasRecordResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar tarefas por intervalo de datas", description = "Recupera tarefas agendadas dentro do intervalo de datas especificado.")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<List<TarefasRecordResponse>> buscaTarefasDTO(@RequestHeader(name = "Authorization", required = false) String token){
        List<TarefasRecordResponse> tarefas = tarefasService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deletar tarefa por ID", description = "Exclui uma tarefa utilizando seu ID.")
    @ApiResponse(responseCode = "200", description = "Tarefa excluída com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    @ApiResponse(responseCode = "403", description = "ID da tarefa não encontrado.")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado.")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id, String token){
        tarefasService.deletaTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Atualizar status da tarefa", description = "Altera o status de uma tarefa.")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    @ApiResponse(responseCode = "403", description = "ID da tarefa não encontrado.")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado.")
    public ResponseEntity<TarefasRecordResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualizar dados da tarefa", description = "Atualiza os dados de uma tarefa específica.")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    @ApiResponse(responseCode = "403", description = "ID da tarefa não encontrado.")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado.")
    public ResponseEntity<TarefasRecordResponse> atualizaTarefas(@RequestBody TarefasRecordResponse dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.atualizarTarefas(dto, id, token));
    }
}
