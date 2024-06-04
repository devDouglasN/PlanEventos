# 🎉 PlanEventos

###  PlanEventos é uma aplicação backend projetada para gerenciar eventos, incluindo participantes e organizadores. A API permite o cadastro completo de eventos, especificando todos os participantes e organizadores envolvidos, juntamente com a data do evento, local, horário, quantidade de pessoas e outras informações relevantes. Além disso, a aplicação oferece funcionalidades para atualização e exclusão de eventos, bem como o gerenciamento de participantes e organizadores associados.

<br>

## 🎥  Assista ao vídeo do projeto  
### Vídeo detalhado do projeto onde cada recurso é explicado e demonstrado em ação! Veja abaixo:



https://github.com/devDouglasN/PlanEventos/assets/122110326/42ae90d2-fb54-4ebe-b443-a275f6fca193



<br>

## ⚙️ Funcionalidades 
+ 🧾 Cadastro de eventos  
+ 👥 Cadastro de participantes  
+ 🧑‍🤝‍🧑 Cadastro de organizadores  
+ 🔄 Atualização e exclusão de eventos, participantes e   organizadores  

<br>

## 🛠️ Tecnologias Utilizadas  
### Backend
| Tecnologia            | Versão  |  
|-----------------------|---------|  
| Java                  | 17      |
| Spring Boot           | 3.2.5   |  
| Spring Security       | 6.2.4   |  
| Spring Boot Data JPA  | 3.2.5   |
| Spring Boot Validation| 3.2.5   |  
| Spring Boot Web       | 3.2.5   |
| Spring Boot Devtools  | 3.2.5   |
| MySQL Connector/J     | runtime |
| H2 Database           | runtime |
| Hibernate Core        | 6.4.4   |
| Springdoc OpenAPI     | 2.5.0   |
| Spring Security Test  | 6.2.4   |
| Lombok                | provided|
| JetBrains Annotations | RELEASE |

<br>

## 📁 Estrutura do Projeto
planeventos/  
├── src/    
│   ├── main/
│   │   ├── java/com/douglas/planeventos/  
│   │   │   ├── config/  
│   │   │   ├── domain/  
│   │   │   ├── enums/  
│   │   │   ├── evento/validadores/  
│   │   │   ├── infra/security/  
│   │   │   ├── repositories/  
│   │   │   ├── resource/  
│   │   │   ├── services/  
│   │   │   └── PlanEventosApplication.java  
│   ├── resources/  
│   │   ├── application.properties  
│   │   ├── application-dev.properties  
│   │   ├── application-test.properties  
├── .gitignore  
├── Dockerfile  
├── pom.xml  
├── README.md  

<br>

##  ![Trello](https://img.shields.io/badge/Trello-0052CC?style=for-the-badge&logo=trello&logoColor=white)



Você pode acompanhar o progresso do projeto no [Trello](https://trello.com/b/BIXBBg0E/planeventos).

<br>

### 🚀 Pré-requisitos
+ Java 17  
+ Maven  
+ MySQL 

<br>

## 📥 Instalação
### Você pode clonar o repositório no seu terminal:
```
git clone git@github.com:devDouglasN/PlanEventos.git
````
### OU 
[Baixar o ZIP do projeto](https://github.com/devDouglasN/PlanEventos/archive/refs/heads/main.zip) e abri-lo em uma IDE de sua preferência.

<br>

### 📦 Instalando as dependências do projeto:
```
mvn clean install
````

<br>
<br>

## 🏃‍♂️ Rodando o Projeto   
### Ambiente de Desenvolvimento
Para iniciar a aplicação, execute:  
```
mvn spring-boot:run
````
A aplicação estará disponível em http://localhost:8080.

<br>

## 🔗 Endpoints
### Você pode utilizar o Insomnia, Postman ou qualquer outra ferramenta de sua preferência para realizar as requisições.

### Eventos:
POST (Cadastrando um evento)  

- http://localhost:8080/eventos  

Exemplo:
```
{  
     "idsParticipantes": [7, 8, 9],  
     "idsOrganizadores": [1, 2],  
     "dataEvento": "2024-06-02",    
     "horarioInicio": "22:00:00",  
     "horarioFim": "02:00:00",    
     "local": "Rua x, numero 50",
     "descricao": "O evento conta com a presença de Messi",  
     "quantidadePessoas": 100  
}  
````
<br>

GET (Retornando lista de eventos)   
+ http://localhost:8080/eventos  

Exemplo:
```
[
  {
    "id": 1,
    "dataEvento": "2024-06-02",
    "local": "Rua x, numero 50",
    "descricao": "O evento conta com a presença de Messi",
    "status": "ABERTO",
    "horarioInicio": "22:00:00",
    "horarioFim": "02:00:00",
    "quantidadePessoas": 100,
    "participantes": [7, 8, 9],
    "organizadores": [1, 2],
    "nomeParticipante": "Nome do participante",
    "nomeOrganizador": "Nome do organizador"
  }
]
````

<br>

GET (Retornando evento por ID)
- Basta adicionar o ID desejado na requisição:
- http://localhost:8080/eventos/{ID}


<br>

PUT (Atualizando evento)
- http://localhost:8080/eventos/{ID}

Exemplo:
```
{
  "dataEvento": "2024-06-03",
  "horarioInicio": "20:00:00",
  "horarioFim": "00:00:00",
  "local": "Rua y, numero 51",
  "descricao": "O evento conta com a presença de Ronaldo",
  "quantidadePessoas": 150
}
````

<br>

DELETE (Deletando um evento)
- http://localhost:8080/eventos/{ID}

<br>

### Participantes

POST (Cadastrando um participante)
- http://localhost:8080/participants   

Exemplo: 
```
{
    "nome": "Rodolfo Abrantes",
    "cpf": "914.782.130-27",
    "email": "rodolfo@gmail.com",
    "senha": "123"
}
````

<br>

GET (Retornando lista de participantes)
- http://localhost:8080/participants   

Exemplo: 
```
[
    {
        "id": 6,
        "nome": "Mahatma Gandhi",
        "cpf": "260.088.730-09",
        "email": "mahatmaG@mail.com",
        "senha": "$2a$10$E93jDElMhdqAlsRAPmWsNOofIe8Kbbxe2yuK8HI8pyDXL5rMNHj12",
        "perfis": [
            1
        ],
        "dataCriacao": "01/06/2024"
    }
]
````

<br>

GET (Retornando participante por ID)  
- Basta adicionar o ID desejado na requisição:
- http://localhost:8080/participantes/{ID}  


<br>

PUT (Atualizando participante)
- http://localhost:8080/participantes/{ID}  

Exemplo:
```
{
    "nome": "Rodolfo Abrantes Silva",
    "cpf": "914.782.130-27",
    "email": "rodolfo.silva@gmail.com",
    "senha": "123"
}
````

<br>

DELETE (Deletando um participante)
- Basta adicionar o ID desejado na requisição.
- http://localhost:8080/participantes/{ID}


<br>


### Organizadores

POST (Cadastrando um organizador)
- http://localhost:8080/organizadores   

Exemplo: 
```
{
    "nome": "Douglas Nascimento",
    "cpf": "603.413.230-47",
    "email": "douglas.nascimento@gmail.com",
    "senha": "123"
}
````

<br>

GET (Retornando lista de organizadores)
- http://localhost:8080/organizadores   

Exemplo: 
```
[
    {
        "id": 6,
        "nome": "Cleber Oliveira",
        "cpf": "491.588.500-31",
        "email": "cleber.oliveira@mail.com",
        "senha": "$2a$10$E93jDElMhdqAlsRAPmWsNOofIe8Kbbxe2yuK8HI8pyDXL5rMNHj12",
        "perfis": [
            1
        ],
        "dataCriacao": "01/06/2024"
    }
]
````

<br>

GET (Retornando organizador por ID)  
- Basta adicionar o ID desejado na requisição:
- http://localhost:8080/organizadores/{ID}  


<br>

PUT (Atualizando organizador)
- http://localhost:8080/organizadores/{ID}  

Exemplo:
```
{
    "nome": "Luana Machado",
    "cpf": "454.988.310-30",
    "email": "luana@hotmail.com",
    "senha": "123"
}
````

<br>

DELETE (Deletando um organizador)
- Basta adicionar o ID desejado na requisição.
- http://localhost:8080/organizadores/{ID}



<br>
<br>

Developed by [Douglas do Nascimento](https://github.com/devDouglasN).
