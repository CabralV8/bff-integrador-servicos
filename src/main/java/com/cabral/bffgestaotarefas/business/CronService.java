package com.cabral.bffgestaotarefas.business;

import com.cabral.bffgestaotarefas.business.dto.in.LoginRecordRequest;
import com.cabral.bffgestaotarefas.business.dto.out.TarefasRecordResponse;
import com.cabral.bffgestaotarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;


    public CronService(TarefasService tarefasService, EmailService emailService, UsuarioService usuarioService) {
        this.tarefasService = tarefasService;
        this.emailService = emailService;
        this.usuarioService = usuarioService;
    }

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {
        String token = login(converterParaRequestRecord());
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCincoMinutos = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TarefasRecordResponse> listaTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCincoMinutos, token);

        listaTarefas.forEach(tarefa -> {emailService.enviarEmail(tarefa);
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.id(),
                    token);});

        }

    public String login(LoginRecordRequest record) {
        // Aqui, o UsuarioService deve aceitar LoginRecordRequest
        return usuarioService.loginUsuario(record);
    }

    public LoginRecordRequest converterParaRequestRecord() {
        return new LoginRecordRequest(this.email, this.senha);
    }
}