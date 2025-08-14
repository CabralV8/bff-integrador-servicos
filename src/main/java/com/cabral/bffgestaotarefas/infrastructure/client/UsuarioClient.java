package com.cabral.bffgestaotarefas.infrastructure.client;


import com.cabral.bffgestaotarefas.business.dto.in.*;
import com.cabral.bffgestaotarefas.business.dto.out.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioRecordResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);


    @PostMapping
    UsuarioRecordResponse salvaUsuario(@RequestBody UsuarioRecordRequest usuarioDTO);


    @PostMapping("/login")
    String login(@RequestBody LoginRecordRequest usuarioDTO);


    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioRecordResponse atualizaDadoUsuario(@RequestBody UsuarioRecordRequest dto,
                                           @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoRecordResponse atualizaEndereco(@RequestBody EnderecoRecordRequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneRecordResponse atualizaTelefone(@RequestBody TelefoneRecordRequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoRecordResponse cadastraEndereco(@RequestBody EnderecoRecordRequest dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneRecordResponse cadastraTelefone(@RequestBody TelefoneRecordRequest dto,
                                         @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepRecord buscarDadosCep(@PathVariable("cep")String cep);
}
