
# Projeto API Account Bank

Uma API (POCs) construída para cadastro de conta bancárias e realizar transações.

## Referência

- [ngrok](https://ngrok.com/)
- [Automated Test](https://ipkiss.pragmazero.com/)

## Documentação da API

#### Redefinir o estado

```http
  POST /reset
```

Retorna código 200 OK.

--
#### Obter saldo para conta existente ou inexistente

```http
  GET /balance?account_id=1234
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `account_id`      | `string` | **Obrigatório**. O ID da conta bancária |

#### getById(param)

Recebe um parâmetro string e retorna o saldo ou erro 400 OK.

--
#### Criar a conta bancária com saldo

```http
  POST /event {"type":"deposit", "destination":"100", "amount":10}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `type` | `string` | **Obrigatório**. Tipo de transação |
| `destination` | `string` | **Obrigatório**. Número da conta bancária |
| `amount` | `double` | **Obrigatório**. Valor da transação |

#### event(Object request)

Recebe os parâmetros e retorna {"destination": {"id":"100", "balance":10}} 201 OK.

--
#### Retirar valor da conta existente ou inexistente

```http
  POST /event {"type":"withdraw", "origin":"100", "amount":5}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `type`      | `string` | **Obrigatório**. Tipo de transação |
| `origin` | `string` | **Obrigatório**. Número da conta bancária |
| `amount` | `double` | **Obrigatório**. Valor da transação |

#### event(Object request)

Recebe os parâmetros e retorna {"origin": {"id":"100", "balance":15}} 201 OK ou 404 0.00.

--
#### Transferência de conta existente

```http
  POST /event {"type":"transfer", "origin":"100", "amount":15, "destination":"300"}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `type`      | `string` | **Obrigatório**. Tipo de transação |
| `origin` | `string` | **Obrigatório**. Número da conta bancária |
| `amount` | `double` | **Obrigatório**. Valor da transação |
| `destination` | `string` | **Obrigatório**. Número da conta bancária |

#### event(Object request)

Recebe os parâmetros e retorna {"origin": {"id":"100", "balance":0}, "destination": {"id":"300", "balance":15}} 201 OK ou 404 0.00.

## Apêndice

A API foi construída para testes aplicados e foi utilizado https://ngrok.com/ para criação do servidor e executado os testes no https://ipkiss.pragmazero.com/

## Contributing
Solicitações de pull são bem-vindas. Para grandes mudanças, abra um problema primeiro para discutir o que você gostaria de mudar.
## Autores

- [@abdielnunes](https://www.github.com/abdielnunes)


## Etiquetas

[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/abdielnunes/api-bank-account/LICENSEs)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)