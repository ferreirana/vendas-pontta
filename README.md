# Vendas (Teste Técnico Pontta)

Sistema de vendas com cadastro de Clientes, Produtos e Pedidos de venda.

## Stack

- Backend: Java 21, Spring Boot 3 (Web, Data JPA, Validation)
- Frontend: Angular, TypeScript
- Banco de dados: PostgreSQL 16
- Ambiente: Docker e Docker Compose

## Estrutura

```
vendas-pontta/
├── api/                  API Spring Boot
├── web/                  Aplicação Angular
│   └── template/         HTMLs de referência visual
├── docker-compose.yml    Banco (e API) via Docker
└── README.md
```

## Pré-requisitos

- JDK 21 e Maven 3.9 ou superior
- Node 20 ou superior e Angular CLI (`npm i -g @angular/cli`)
- Docker com Docker Compose

## Como rodar

1. Subir o banco de dados:
   ```bash
   docker compose up -d db
   ```
2. Subir a API (http://localhost:8080):
   ```bash
   cd api
   mvn spring-boot:run
   ```
   Teste de conexão: http://localhost:8080/api/ping
3. Subir o front (http://localhost:4200):
   ```bash
   cd web
   npm install
   ng serve
   ```

### Tudo via Docker (banco + API)

```bash
docker compose up --build
```

Depois, rode apenas o front com `ng serve`, como no passo 3.

## Testes

```bash
cd api
mvn test
```

Os testes usam banco H2 em memória, então não precisam do PostgreSQL no ar.

## Decisões

CRUD simples, apenas de clientes

## O que ficou de fora

CRUD - produtos e integração produto + cliente

## Dificuldades

Resolver os problemas de sintaxe do Angular, como não tenho tanto conhecimento de Angular, acredito que foi a maior dificuldade.
Lógica no front ainda é um ponto de dificuldade. 
