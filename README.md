# Baozi Store — API REST

Trabalho da disciplina Desenvolvimento Web Back-End.

## Sobre

API REST de uma loja de baozi. O sistema cadastra clientes e produtos e registra pedidos simples, em que um cliente compra um produto em determinada quantidade.

## Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- Banco relacional
- JSON

## Entidades

- **Cliente:** id, nome, clienteDesde
- **Produto:** id, nome, preco, estoque
- **Pedido:** id, cliente, produto, quantidade
