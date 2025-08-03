package com.cabral.bffgestaotarefas.business;

import com.cabral.bffgestaotarefas.business.dto.in.EnderecoDTORequest;
import com.cabral.bffgestaotarefas.business.dto.in.LoginRequestDTO;
import com.cabral.bffgestaotarefas.business.dto.in.TelefoneDTORequest;
import com.cabral.bffgestaotarefas.business.dto.in.UsuarioDTORequest;
import com.cabral.bffgestaotarefas.business.dto.out.EnderecoDTOResponse;
import com.cabral.bffgestaotarefas.business.dto.out.TelefoneDTOResponse;
import com.cabral.bffgestaotarefas.business.dto.out.UsuarioDTOResponse;
import com.cabral.bffgestaotarefas.infrastructure.client.UsuarioClient;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioService(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }


    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTORequest){

        return usuarioClient.salvaUsuario(usuarioDTORequest);
    }

    public String loginUsuario(LoginRequestDTO usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }


    public void deletarUsuarioPorEmail(String email, String token){
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest usuarioDTO){
        return usuarioClient.atualizaDadoUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizaEndereco(String idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return usuarioClient.atualizaEndereco(enderecoDTO, Long.valueOf(idEndereco), token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token){
        return usuarioClient.atualizaTelefone(dto, idTelefone, token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {
        return usuarioClient.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto){
        return usuarioClient.cadastraTelefone(dto, token);
    }
}
