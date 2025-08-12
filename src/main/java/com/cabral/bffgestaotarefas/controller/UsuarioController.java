package com.cabral.bffgestaotarefas.controller;


import com.cabral.bffgestaotarefas.business.UsuarioService;
import com.cabral.bffgestaotarefas.business.dto.in.EnderecoDTORequest;
import com.cabral.bffgestaotarefas.business.dto.in.LoginRequestDTO;
import com.cabral.bffgestaotarefas.business.dto.in.TelefoneDTORequest;
import com.cabral.bffgestaotarefas.business.dto.in.UsuarioDTORequest;
import com.cabral.bffgestaotarefas.business.dto.out.EnderecoDTOResponse;
import com.cabral.bffgestaotarefas.business.dto.out.TelefoneDTOResponse;
import com.cabral.bffgestaotarefas.business.dto.out.UsuarioDTOResponse;
import com.cabral.bffgestaotarefas.business.dto.out.ViaCepRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "User cadastrado com sucesso.")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login usuários.", description = "Autentica o usuário e retorna um token.")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados do usuário por email.", description = "Busca dados do usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuário por email", description = "Deleta o usuário pelo email.")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso.")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuário.", description = "Atualiza as informações básicas do usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso.")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço de usuário.", description = "Atualiza o endereço do usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso.")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(String.valueOf(id), dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone de usuário", description = "Atualiza o telefone do usuário.")
    @ApiResponse(responseCode = "200", description = "Telefone do usuário atualizado com sucesso.")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastrar endereço do usuário.", description = "Cadastra um novo endereço pro usuário.")
    @ApiResponse(responseCode = "200", description = "Address successfully registered.")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }
    @PostMapping("/telefone")
    @Operation(summary = "Cadastrar telefone de usuário", description = "Registra um novo endereço de telefone pro usuário.")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso.")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Buscar endereço pelo cep",
            description = "Realiza a busca do endereço pelo cep.")
    @ApiResponse(responseCode = "200", description = "Dados de endereço retornados com sucesso.")
    @ApiResponse(responseCode = "400", description = "Cep inválido.")
    @ApiResponse(responseCode = "500", description = "Erro interno de servidor.")
    public ResponseEntity<ViaCepRecord> buscarEndereco(@PathVariable("cep") String cep){
        return ResponseEntity.ok(usuarioService.buscaEnderecoPorCep(cep ));
    }

}
