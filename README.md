# Documentacao do Projeto Spring Boot com MySQL e Docker

Este documento descreve a estrutura do projeto e os procedimentos necessarios para configurar, buildar e executar a aplicacao de gerenciamento de alunos utilizando contêineres.

## 1. Estrutura de Diretorios

Para o correto funcionamento, os arquivos devem ser organizados na seguinte estrutura de pastas:

```text
projeto-aluno/
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── exemplo/
                    └── demo/
                        └── DemoApplication.java
```

## 2. Passo a Passo para Execucao

Siga as instrucoes abaixo para subir o ambiente em sua maquina ou VM:

### Passo 1: Preparacao do Ambiente
Certifique-se de que o Docker e o Docker Compose estao instalados. Crie a pasta do projeto e navegue ate ela:
```bash
mkdir -p ~/projeto-aluno/src/main/java/com/exemplo/demo
cd ~/projeto-aluno
```

### Passo 2: Criacao dos Arquivos
Utilize um editor de texto (como nano ou vim) para criar cada um dos arquivos necessarios (docker-compose.yml, Dockerfile, pom.xml e DemoApplication.java), colando seus respectivos conteudos e respeitando a arvore de diretorios mostrada na seção 1.

### Passo 3: Execucao do Docker Compose
Execute o comando abaixo para realizar o build da imagem Java e subir os servicos (Banco de Dados e Aplicacao) em segundo plano:
```bash
sudo docker compose up -d --build
```

### Passo 4: Verificacao dos Logs
Para acompanhar o processo de inicializacao do Spring Boot e verificar se a conexao com o MySQL foi estabelecida, utilize:
```bash
sudo docker compose logs -f
```

## 3. Visualizacao dos Resultados

A aplicacao estara configurada para expor os dados na porta 8080. Apos o sistema subir, os dados do aluno Jonatan serao inseridos automaticamente.

### Via Navegador
Acesse o endereco abaixo para ver a lista de alunos em formato JSON:
`http://<IP-DA-SUA-VM>:8080/alunos`

*(Nota: Se estiver testando localmente, utilize http://localhost:8080/alunos)*

### Via Terminal (Curl)
Voce tambem pode validar o funcionamento direto pelo terminal:
```bash
curl http://localhost:8080/alunos
```

**Resultado esperado no retorno:**
Um objeto JSON contendo o ID, Nome (Jonatan), RM (rm566998) e a Data de Nascimento (26/02/2006).

---
## 4. Comandos de Manutencao

* **Parar a aplicacao:** `sudo docker compose down`
* **Reiniciar servicos:** `sudo docker compose restart`
* **Remover volumes (limpar banco):** `sudo docker compose down -v`
```