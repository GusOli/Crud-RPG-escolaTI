
| Nº  | Funcionalidade                                 | Método | Rota                                                        |
|-----|------------------------------------------------|--------|-------------------------------------------------------------|
| 1   | Cadastrar Personagem                           | POST   | `/personagens`                                              |
| 2   | Cadastrar Item Mágico                          | POST   | `/itens-magicos`                                            |
| 3   | Listar Personagens                             | GET    | `/personagens`                                              |
| 4   | Buscar Personagem por Identificador            | GET    | `/personagens/{id}`                                         |
| 5   | Atualizar Nome de Guerreiro por Identificador  | PUT    | `/personagens/{id}/nome-aventureiro`                        |
| 6   | Remover Personagem                             | DELETE | `/personagens/{id}`                                         |
| 7   | Listar Itens Mágicos                           | GET    | `/itens-magicos`                                            |
| 8   | Buscar Item Mágico por Identificador           | GET    | `/itens-magicos/{id}`                                       |
| 9   | Adicionar Item Mágico ao Personagem            | POST   | `/personagens/{personagemId}/itens/{itemId}`                |
| 10  | Listar Itens Mágicos por Personagem            | GET    | `/personagens/{personagemId}/itens`                         |
| 11  | Remover Item Mágico do Personagem              | DELETE | `/personagens/{personagemId}/itens/{itemId}`                |
| 12  | Buscar Amuleto do Personagem                   | GET    | `/personagens/{personagemId}/amuleto`                       |

Crie um novo item mágico (arma, armadura ou amuleto) enviando os dados no formato JSON abaixo:

{
  "nome": "Espada Flamejante",
  "tipo": "ARMA",
  "forca": 5,
  "defesa": 0
}
Crie um novo personagem enviando os dados no formato JSON abaixo:

{
  "nome": "Arthur",
  "nomeAventureiro": "Rei Arthur",
  "classe": "GUERREIRO",
  "level": 1,
  "forcaBase": 6,
  "defesaBase": 4
}

Tecnologias Utilizadas
Java 

Spring Boot

JPA / Hibernate

H2 Database (banco de dados em memória para testes)

Postman (para testar as APIs)
