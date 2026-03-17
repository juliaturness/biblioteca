# Biblioteca Pessoal (Personal Library System)

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-6DB33F?style=flat&logo=spring&logoColor=white)
![Node.js](https://img.shields.io/badge/Node.js-18.x-339933?style=flat&logo=node.js&logoColor=white)
![Python](https://img.shields.io/badge/Python-3.x-3776AB?style=flat&logo=python&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?style=flat&logo=postgresql&logoColor=white)
![Auth0](https://img.shields.io/badge/Auth0-EB5424?style=flat&logo=auth0&logoColor=white)

## Visão Geral do Projeto

Este repositório contém um sistema avançado de gerenciamento de acervo literário e acompanhamento de leitura. O projeto adota uma arquitetura multi-linguagem orientada a serviços, integrando **Node.js** (Frontend), **Java com Spring Boot** (Core System) e **Python** (Data & Automation Worker), com a segurança e gestão de identidade gerenciadas pelo **Auth0**.

O sistema vai além do gerenciamento básico, oferecendo importação automática de metadados, análise de estatísticas de leitura e um sistema inteligente de recomendações personalizadas.

---

## Arquitetura e Fluxo de Dados
A solução foi desenhada para escalabilidade e separação de responsabilidades. O diagrama abaixo ilustra como os diferentes ecossistemas se comunicam:

```mermaid
 flowchart TD
    classDef frontend fill:#339933,stroke:#fff,stroke-width:2px,color:#fff;
    classDef backend fill:#6DB33F,stroke:#fff,stroke-width:2px,color:#fff;
    classDef auth fill:#EB5424,stroke:#fff,stroke-width:2px,color:#fff;
    classDef database fill:#336791,stroke:#fff,stroke-width:2px,color:#fff;
    classDef python fill:#3776AB,stroke:#fff,stroke-width:2px,color:#fff;
    classDef external fill:#4285F4,stroke:#fff,stroke-width:2px,color:#fff;

    U((Usuário))
    
    subgraph client_side [Client-Side]
        UI[Frontend App\nNode.js]:::frontend
    end

    subgraph identity [Gestão de Identidade]
        Auth[Auth0\nLogin & Segurança]:::auth
    end

    subgraph server_side [Server-Side / Core System]
        API[Spring Boot API\nJava 17]:::backend
    end

    subgraph persistence [Persistência]
        DB[(PostgreSQL\nDatabase)]:::database
    end

    subgraph automation [Automação e Data Science]
        Worker[Python Worker\nCron Jobs / Pandas]:::python
        GoogleAPI[Google Books API]:::external
    end

    U -- "1. Acessa Interface" --> UI
    UI -- "2. Redireciona para Login" <--> Auth
    UI -- "3. Envia Requisição (Bearer JWT)" --> API
    API -- "4. Valida Token do Usuário" --> Auth
    API -- "5. CRUD Principal" <--> DB
    
    Worker -- "6. Lê dados em background" --> DB
    Worker -- "7. Busca metadados\nde livros" --> GoogleAPI
    GoogleAPI -- "Retorna dados enriquecidos" --> Worker
    Worker -- "8. Atualiza DB (Stats/Recomendações)" --> DB
```

---

## Modelagem do Banco de Dados (ERD)

A base de dados relacional foi normalizada para evitar redundâncias e facilitar a integração com o worker em Python.

```mermaid
erDiagram
    USERS ||--o{ USER_BOOKS : "possui na biblioteca"
    BOOKS ||--o{ USER_BOOKS : "é adicionado como"
    USERS ||--o{ READING_GOALS : "define"
    USERS ||--|| READING_STATS : "tem"

    USERS {
        UUID id PK
        VARCHAR auth0_id UK "Token Auth0"
        VARCHAR email UK
        VARCHAR name
        TIMESTAMP created_at
    }

    BOOKS {
        UUID id PK
        VARCHAR isbn UK
        VARCHAR title
        VARCHAR author
        VARCHAR cover_url
        INT total_pages
        VARCHAR array_genres
    }

    USER_BOOKS {
        UUID id PK
        UUID user_id FK
        UUID book_id FK
        VARCHAR status "WANT_TO_READ, READING, FINISHED"
        INT pages_read
        INT rating
        TEXT review_text
    }

    READING_GOALS {
        UUID id PK
        UUID user_id FK
        INT year
        INT target_books
        INT books_read
    }

    READING_STATS {
        UUID id PK
        UUID user_id FK
        DECIMAL avg_pages_per_day
        VARCHAR favorite_genre
        INT total_pages_read
        INT reading_streak
    }
```

---

## Funcionalidades da API (Endpoints Principais)

A API RESTful construída em **Spring Boot** expõe os seguintes endpoints consumidos pelo frontend. *Todas as rotas requerem o envio do token JWT do Auth0 no Header (`Authorization: Bearer <token>`).*

### 📚 Livros e Acervo Pessoal (`/api/books`)
* `GET /api/books/my-library` - Retorna todos os livros do usuário autenticado.
* `POST /api/books` - Adiciona um novo livro à biblioteca pessoal.
* `PUT /api/books/{id}/progress` - Atualiza a página atual ou o status de leitura (ex: mudou de *Lendo* para *Lido*).
* `DELETE /api/books/{id}` - Remove o livro da estante do usuário.

### 📊 Estatísticas e Metas (`/api/stats`)
* `GET /api/stats` - Retorna as estatísticas consolidadas do usuário (processadas previamente pelo Python).
* `POST /api/stats/goals` - Define uma nova meta de leitura para o ano vigente.
* `GET /api/stats/recommendations` - Retorna uma lista de livros sugeridos pelo algoritmo de similaridade.

---

## Estrutura do Repositório

```text
/
├── backend-java/       # API Principal (Spring Boot)
│   ├── src/
│   └── build.gradle
├── client/             # Frontend App (Node.js)
│   ├── src/
│   └── package.json
├── worker-python/      # Automações e Recomendações (Python)
│   ├── main.py
│   └── requirements.txt
└── README.md
```

---

## Pré-requisitos de Instalação

* **Java JDK 17+**
* **Node.js 18+**
* **Python 3.9+** (com `pip`)
* **PostgreSQL 14+**
* **Conta no Auth0** (Para configuração das chaves de ambiente)

---

## Instruções de Execução Local

### 1. Banco de Dados e Variáveis de Ambiente
Crie um banco de dados no PostgreSQL chamado `biblioteca`. Configure as credenciais do banco e os domínios do Auth0 no arquivo `application.properties` (Java) e no `.env` (Node).

### 2. Core System (Java)
Em um terminal, inicie a API principal:
```bash
cd backend-java
./gradlew bootRun
```

### 3. Worker de Automação (Python)
Em um segundo terminal, inicie o serviço de background:
```bash
cd worker-python
pip install -r requirements.txt
python main.py
```

### 4. Execução do Frontend

**4.1. Navegar para o diretório do cliente**
Vá para o diretório do frontend:
```bash
cd client
```

**4.2. Instalar as dependências**
Instale as dependências do projeto de forma limpa:
```bash
npm ci
```

**4.3. Rodar o Frontend**
Execute o servidor de desenvolvimento:
```bash
npm run dev 
```
*A interface estará acessível na porta configurada (ex: `http://localhost:3000`).*

