package com.cabral.bffgestaotarefas.business;


import com.cabral.bffgestaotarefas.business.dto.in.TarefasRecordRequest;
import com.cabral.bffgestaotarefas.business.dto.out.TarefasRecordResponse;
import com.cabral.bffgestaotarefas.infrastructure.client.TarefasClient;
import com.cabral.bffgestaotarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasService(TarefasClient tarefasClient) {
        this.tarefasClient = tarefasClient;
    }


    public TarefasRecordResponse gravarTarefa(String token, TarefasRecordRequest dto) {
        return tarefasClient.gravarTarefas(dto, token);
    }

    public List<TarefasRecordResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                       LocalDateTime dataFinal,
                                                                       String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasRecordResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.buscarTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletarTarefaPorId(id, token);
    }

    public TarefasRecordResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alterarStaturNotificacao(status, id, token);
    }

    public TarefasRecordResponse atualizarTarefas(TarefasRecordResponse dto, String id, String token) {
        return tarefasClient.atualizarTarefas(dto, id, token);
    }
}

