package com.cabral.bffgestaotarefas.infrastructure.client;


import com.cabral.bffgestaotarefas.business.dto.in.EnderecoDTORequest;
import com.cabral.bffgestaotarefas.business.dto.in.LoginRequestDTO;
import com.cabral.bffgestaotarefas.business.dto.in.TelefoneDTORequest;
import com.cabral.bffgestaotarefas.business.dto.in.UsuarioDTORequest;
import com.cabral.bffgestaotarefas.business.dto.out.EnderecoDTOResponse;
import com.cabral.bffgestaotarefas.business.dto.out.TelefoneDTOResponse;
import com.cabral.bffgestaotarefas.business.dto.out.UsuarioDTOResponse;
import com.cabral.bffgestaotarefas.business.dto.out.ViaCepRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);


    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);


    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);


    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                           @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepRecord buscarDadosCep(@PathVariable("cep")String cep);
}
