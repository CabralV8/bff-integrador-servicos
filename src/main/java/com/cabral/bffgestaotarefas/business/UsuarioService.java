package com.cabral.bffgestaotarefas.business;

import com.cabral.bffgestaotarefas.business.dto.in.*;
import com.cabral.bffgestaotarefas.business.dto.out.*;
import com.cabral.bffgestaotarefas.infrastructure.client.UsuarioClient;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioService(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }


    public UsuarioRecordResponse salvaUsuario(UsuarioRecordRequest usuarioDTORequest){
        return usuarioClient.salvaUsuario(usuarioDTORequest);
    }

    public String loginUsuario(LoginRecordRequest usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioRecordResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }


    public void deletarUsuarioPorEmail(String email, String token){
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioRecordResponse atualizarDadosUsuario(String token, UsuarioRecordRequest usuarioDTO){
        return usuarioClient.atualizaDadoUsuario(usuarioDTO, token);
    }

    public EnderecoRecordResponse atualizaEndereco(String idEndereco, EnderecoRecordRequest enderecoDTO, String token){
        return usuarioClient.atualizaEndereco(enderecoDTO, Long.valueOf(idEndereco), token);
    }

    public TelefoneRecordResponse atualizaTelefone(Long idTelefone, TelefoneRecordRequest dto, String token){
        return usuarioClient.atualizaTelefone(dto, idTelefone, token);
    }

    public EnderecoRecordResponse cadastraEndereco(String token, EnderecoRecordRequest dto) {
        return usuarioClient.cadastraEndereco(dto, token);
    }

    public TelefoneRecordResponse cadastraTelefone(String token, TelefoneRecordRequest dto){
        return usuarioClient.cadastraTelefone(dto, token);
    }

    public ViaCepRecord buscaEnderecoPorCep(String cep){
        return usuarioClient.buscarDadosCep(cep);
    }
}
