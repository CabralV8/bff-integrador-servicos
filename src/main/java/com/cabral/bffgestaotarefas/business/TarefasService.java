package com.cabral.bffgestaotarefas.business;


import com.cabral.bffgestaotarefas.business.dto.in.TarefasDTORequest;
import com.cabral.bffgestaotarefas.business.dto.out.TarefasDTOResponse;
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


    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
        return tarefasClient.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                    LocalDateTime dataFinal,
                                                                    String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.buscarTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        tarefasClient.deletarTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alterarStaturNotificacao(status, id, token);
    }

    public TarefasDTOResponse atualizarTarefas(TarefasDTOResponse dto, String id, String token) {
        return tarefasClient.atualizarTarefas(dto, id, token);
    }
}

