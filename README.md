# 📊 Economic Data Extractor - Pipeline ETL (Stage 1)

Este é o primeiro estágio de um pipeline de engenharia de dados completo. O projeto consiste em um **Extrator (Ingestor)** desenvolvido em Java para automatizar a coleta de indicadores econômicos brasileiros, realizando o processamento inicial e a persistência em um ambiente containerizado.

---

## 🚀 Sobre o Projeto

O objetivo desta aplicação é consumir dados oficiais do **Sistema Gerenciador de Séries Temporais (SGS) do Banco Central do Brasil**. O extrator busca séries históricas de indicadores fundamentais, trata as respostas da API e garante que o banco de dados esteja sempre atualizado e sem duplicidade.

### Principais Funcionalidades:
* **Ingestão Multi-Indicadores**: Suporte para Dólar (PTAX), SELIC e IPCA.
* **Idempotência (Data Integrity)**: Lógica customizada para verificar a existência do registro (par Indicador/Data) antes da inserção, evitando redundância.
* **Resiliência**: Tratamento de janelas temporais e limites de requisição da API do BCB.
* **Ambiente Isolado**: Configuração pronta para uso via Docker, facilitando o deploy e o desenvolvimento local.

---

## 🛠️ Stack Tecnológica

| Tecnologia | Finalidade |
| :--- | :--- |
| **Java 21** | Linguagem principal (Amazon Corretto) |
| **Spring Boot 3.4** | Framework de desenvolvimento e inversão de controle |
| **Spring Data JPA** | Abstração da camada de persistência e ORM |
| **PostgreSQL 18** | Banco de dados relacional para armazenamento dos dados brutos |
| **Docker & Compose** | Orquestração de containers e ambiente de banco de dados |
| **Maven** | Gerenciamento de dependências e build |

---

## 📋 Arquitetura do Pipeline

O projeto segue a filosofia de sistemas **ETL (Extract, Transform, Load)**:

1.  **Extract**: Java consome os endpoints JSON da API do Banco Central.
2.  **Transform**: Conversão de formatos brasileiros (vírgula para ponto) e tipos de data para `BigDecimal` e `LocalDate`.
3.  **Load**: Persistência no PostgreSQL através do Hibernate/JPA.

---

## 🔧 Como Executar

### Pré-requisitos:
* Docker e Docker Compose instalados.
* JDK 21 ou superior.

### Passo a passo:

1. **Clone o repositório**:
   ```bash
   git clone [https://github.com/SEU_USUARIO/NOME_DO_REPO.git](https://github.com/SEU_USUARIO/NOME_DO_REPO.git)
   cd NOME_DO_REPO
