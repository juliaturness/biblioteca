# Biblioteca Pessoal (Personal Library System)

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-6DB33F?style=flat&logo=spring&logoColor=white)
![React](https://img.shields.io/badge/React-18-61DAFB?style=flat&logo=react&logoColor=black)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?style=flat&logo=postgresql&logoColor=white)

## Visão Geral do Projeto

Este repositório contém a implementação Full Stack de um sistema de gerenciamento de acervo literário. O projeto integra uma API robusta desenvolvida em **Java (Spring Boot)** com uma interface moderna e reativa construída em **React**, demonstrando domínio sobre o ciclo completo de desenvolvimento web (Client-Server Architecture).

O sistema permite o gerenciamento do ciclo de vida de leitura, categorização de obras e oferece uma experiência de usuário fluida através de uma Single Page Application (SPA).

## Arquitetura de Software

A solução segue o modelo de arquitetura em camadas, com separação clara entre cliente e servidor:

### 1. Server-Side (Backend API)
Responsável pelas regras de negócio, segurança e persistência de dados.
* **Framework:** Spring Boot 3.x
* **Interface:** API RESTful
* **Persistência:** Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL

### 2. Client-Side (Frontend Application)
Responsável pela interação com o usuário e consumo da API.
* **Framework:** React 18
* **Estilo Arquitetural:** SPA (Single Page Application)
* **Gerenciamento de Estado:** React Hooks / Context API
* **Comunicação:** Axios/Fetch para consumo dos endpoints Java.

---

## Estrutura do Repositório

```text
/
├── backend/            # API Spring Boot 
│   ├── src/
│   ├── build.gradle
│   └── ...
├── frontend/           # Aplicação React 
│   ├── src/
│   ├── package.json
│   └── public/
└── README.md

```

---

## Pré-requisitos de Instalação

Para executar este projeto localmente, as seguintes ferramentas devem estar instaladas e configuradas no ambiente:

* **Java JDK 17** ou superior
* **Node.js 18+** (Necessário para gerenciamento de pacotes do frontend)
* **PostgreSQL 14** ou superior
* **Git**

---

## Instruções de Build e Execução

### 1. Clonagem do Repositório

```bash
git clone [https://github.com/juliaturness/biblioteca.git](https://github.com/juliaturness/biblioteca.git)
cd biblioteca

```

### 2. Configuração do Banco de Dados

...

### 3. Execução do Backend (Spring Boot)

...

### 4. Execução do Frontend (React)

Em um novo terminal, navegue até o diretório do cliente e instale as dependências:

```bash
cd frontend
npm install
npm run dev

```

*A aplicação web abrirá automaticamente em: `http://localhost:3000*`

---

## Documentação da API

A documentação detalhada dos endpoints (Swagger/OpenAPI) está em desenvolvimento.

