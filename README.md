# Projeto BFF - Gestão de Tarefas

O BFF Gestão de Tarefas é uma aplicação Backend-for-Frontend (BFF) em Java + Spring Boot que integra três
microsserviços: Usuário, Gestor de Tarefas e Serviço de Notificação.
Ele expõe endpoints consumíveis pelo front-end e orquestra chamadas entre os microsserviços (via Feign), além de possuir
tarefas agendadas (cron) que verificam eventos futuros e disparam notificações por e-mail.

## Variáveis de Ambiente

Para rodar este projeto, você precisará adicionar as seguintes variáveis de ambiente no seu application properties:

| Variável                                        | Descrição                                                         | Valor Padrão / Observação             |
|:------------------------------------------------|:------------------------------------------------------------------|:--------------------------------------|
| `spring.application.name=`                      | Nome da aplicação                                                 | bff-gestao-tarefas                    |
| `usuario.url=`                                  | URL base do serviço de Usuário (serviço alvo para /usuario)       | localhost:8080/usuario                |
| `servico.notificacao.url=`                      | URL base do serviço de Notificação (serviço alvo para /email)     | http://localhost:8082                 |
| `cron.horario=`                                 | Expressão CRON para o agendamento de verificação de tarefas       | 0 0/5 * * * ? (a cada 5 minutos)      |
| `server.port=`                                  | Porta onde o BFF será executado                                   | 8083                                  |
| `usuario.email=`                                | E-mail usado pelo CronService para efetuar login e buscar tarefas | **Obrigatório** (definir no ambiente) |
| `usuario.senha=`                                | Senha do usuário usado pelo CronService                           | **Obrigatório** (definir no ambiente) |
| `logging.level.org.springframework.scheduling=` | Nível de log para agendamento (opcional)                          | **DEBUG**                             |

**Observação:** Configure usuario.email e usuario.senha como variáveis de ambiente (ou no secret manager) — não commite
credenciais no repositório.

## Documentação da API

A documentação interativa (Swagger / OpenAPI) está disponível localmente em:
http://localhost:8083/swagger-ui.html

## Usuário

#### Retorna o usuário cadastrado

```http
  POST /usuario
```

| Parâmetro           | Tipo    | Descrição                                                  |
|:--------------------|:--------|:-----------------------------------------------------------|
| `UsuarioDTORecord	` | `JSON	` | **Obrigatório**: Dados do usuário (nome, email, senha etc) |

#### Login de usuário

```http
  POST /usuario/login
```

| Parâmetro          | Tipo   | Descrição                                 |
|:-------------------|:-------|:------------------------------------------|
| `UsuarioDTORecord` | `JSON` | **Obrigatório**: Email e senha do usuário |

#### Buscar usuário por e-mail

```http
  GET /usuario?email={email}
```

| Parâmetro | Tipo     | Descrição                         |
|:----------|:---------|:----------------------------------|
| `email`   | `string` | **Obrigatório**: Email do usuário |

#### Deletar usuário por e-mail

```http
  DELETE /usuario/{email}
```

| Parâmetro | Tipo     | Descrição                                        |
|:----------|:---------|:-------------------------------------------------|
| `email`   | `string` | **Obrigatório**: Email do usuário a ser deletado |

#### Atualizar dados do usuário

```http
  PUT /usuario
```

| Parâmetro          | Tipo     | Descrição                                     |
|:-------------------|:---------|:----------------------------------------------|
| `UsuarioDTORecord` | `JSON`   | **Obrigatório**: Dados do usuário a atualizar |
| `Authorization`    | `string` | **Obrigatório**: Token JWT do usuário         |

#### Atualizar endereço

```http
  PUT /usuario/endereco?id={id}
```

| Parâmetro           | Tipo   | Descrição                                      |
|:--------------------|:-------|:-----------------------------------------------|
| `id`                | `long` | **Obrigatório**. ID do endereço                |
| `EnderecoDTORecord` | `JSON` | **Obrigatório**: Dados do endereço a atualizar |

#### Atualizar telefone

```http
  PUT /usuario/telefone?id={id}
```

| Parâmetro           | Tipo   | Descrição                                      |
|:--------------------|:-------|:-----------------------------------------------|
| `id`                | `long` | **Obrigatório**. ID do telefone                |
| `TeleFoneDTORecord` | `JSON` | **Obrigatório**: Dados do telefone a atualizar |

#### Cadastrar endereço

```http
  POST /usuario/endereco
```

| Parâmetro           | Tipo     | Descrição                               |
|:--------------------|:---------|:----------------------------------------|
| `EnderecoDTORecord` | `JSON`   | **Obrigatório**: Dados do novo endereço |
| `Authorization`     | `string` | **Obrigatório**: Token JWT do usuário   |

#### Cadastrar telefone

```http
  POST /usuario/telefone
```

| Parâmetro           | Tipo     | Descrição                                |
|:--------------------|:---------|:-----------------------------------------|
| `TeleFoneDTORecord` | `JSON`   | **Obrigatório**:  Dados do novo telefone |
| `Authorization`     | `string` | **Obrigatório**: Token JWT do usuário    |

#### Buscar endereço por CEP

```http
  GET /usuario/endereco/{cep}
```

| Parâmetro | Tipo     | Descrição                              |
|:----------|:---------|:---------------------------------------|
| `cep`     | `string` | **Obrigatório**:  CEP a ser consultado |

## Tarefas

#### Criar tarefa para usuário

```http
  POST /tarefas
```

| Parâmetro              | Tipo     | Descrição                                                             |
|:-----------------------|:---------|:----------------------------------------------------------------------|
| `TarefasRecordRequest` | `JSON`   | **Obrigatório**:  Dados da tarefa (nomeTarefa, descricao, dataEvento) |
| `Authorization`        | `string` | **Opcional**: Token JWT do usuário                                    |

#### Buscar tarefas agendadas por período

```http
GET /tarefas/eventos?dataInicial={ISO}&dataFinal={ISO}
```

| Parâmetro       | Tipo             | Descrição                                                 |
|:----------------|:-----------------|:----------------------------------------------------------|
| `dataInicial`   | `datetime (ISO)` | **Obrigatório**:  Data inicial (ex.: 2025-08-13T00:00:00) |
| `dataFinal`     | `datetime (ISO)` | **Obrigatório**: Data final                               |
| `Authorization` | `string`         | **Opcional**: Token JWT do usuário                        |

#### Buscar todas as tarefas do usuário autenticado

```http
GET /tarefas
```

| Parâmetro       | Tipo     | Descrição                          |
|:----------------|:---------|:-----------------------------------|
| `Authorization` | `string` | **Opcional**: Token JWT do usuário |

#### Deletar tarefa por ID

```http
DELETE /tarefas?id={id}
```

| Parâmetro       | Tipo     | Descrição                          |
|:----------------|:---------|:-----------------------------------|
| `id`            | `string` | **Obrigatório**: ID da tarefa      |
| `Authorization` | `string` | **Opcional**: Token JWT do usuário |

#### Alterar status da tarefa

```http
PATCH /tarefas?status={status}&id={id}
```

| Parâmetro       | Tipo            | Descrição                                                                   |
|:----------------|:----------------|:----------------------------------------------------------------------------|
| `status`        | `string (enum)` | **Obrigatório**:  Novo status (**PENDENTE**, **NOTIFICADO**, **CANCELADO**) |
| `id`            | `string`        | **Obrigatório**: ID da tarefa                                               |
| `Authorization` | `string`        | **Opcional**: Token JWT do usuário                                          |

#### Atualizar dados de uma tarefa

```http
PUT /tarefas?id={id}
```

| Parâmetro               | Tipo     | Descrição                                     |
|:------------------------|:---------|:----------------------------------------------|
| `TarefasRecordResponse` | `JSON`   | **Obrigatório**:  Dados atualizados da tarefa |
| `id`                    | `string` | **Obrigatório**: ID da tarefa                 |
| `Authorization`         | `string` | **Opcional**: Token JWT do usuário            |

## Cron (Agendamento)

O BFF possui uma rotina agendada configurada pela variável cron.horario.
A tarefa agendada (CronService) realiza o seguinte fluxo:

**1**. Faz login no serviço de Usuário usando as credenciais definidas em usuario.email e usuario.senha.

**2**. Calcula o intervalo futuro (ex.: now + 1 hora até now + 1 hora + 5 minutos).

**3**. Chama o Gestor de Tarefas para buscar tarefas agendadas nesse intervalo.

**4**. Para cada tarefa encontrada: chama o Serviço de Notificação para enviar e-mail e altera o status da notificação para NOTIFICADO no Gestor de Tarefas.

**Variável importante**: cron.horario (padrão no projeto: 0 0/5 * * * ?) — ajuste conforme necessidade.

## Feedback

Se você tiver algum feedback ou dúvida, por favor deixe por meio de:

**Email**: valberton77@gmail.com

**GitHub**: https://github.com/CabralV8

**LinkedIn**: www.linkedin.com/in/valbertoncabral
