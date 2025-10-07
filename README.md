# Fast Food API

API para gerenciamento de pedidos de fast food, desenvolvida como parte do Tech Challenge da FIAP.

## Vídeo de Apresentação TechChallenge Fase 3 

![Vídeo de Apresentação](https://www.youtube.com/watch?v=XySUTroo2dk)

## Respositorios

![api]         ![=](https://github.com/humbfei-fiap/soat-tech-challenge-fast-food-app)

![gtw+ambda]   ![=](https://github.com/humbfei-fiap/soat-tech-challenge-infra-gtw-lambda)
![eks]         ![=](https://github.com/humbfei-fiap/soat-tech-challenge-infra-eks)
![banco]       ![=](https://github.com/humbfei-fiap/soat-tech-challenge-infra-postgres)
![roles]       ![=](https://github.com/humbfei-fiap/soat-tech-challenge-infra-roles)


## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- Docker
- Maven
- Swagger/OpenAPI

## Estrutura do Projeto

```
src/main/java/com/postechfiap_group130/techchallenge_fastfood/
│
├── api/                              # Camada de Interface
│   └── rest/controller/             # Controladores REST
│
├── application/                      # Lógica de Aplicação
│   ├── exceptions/                  # Exceções personalizadas
│   └── validation/                 # Validações de aplicação
│
├── config/                          # Configurações da aplicação
│
├── core/                            # Núcleo do Domínio
│   ├── controllers/                 # Controladores de negócio
│   ├── dtos/                       # Objetos de Transferência
│   ├── entities/                   # Entidades de Domínio
│   ├── gateways/                   # Implementações de portas de saída
│   ├── interfaces/                 # Portas (interfaces)
│   ├── presenters/                 # Conversores para DTOs
│   └── usecases/                   # Casos de Uso
│
├── mock_payment/                   # Simulação de pagamento
└── webhook/                       # Webhooks externos
```

## Funcionalidades

- Cadastro e gerenciamento de produtos
- Cadastro e gerenciamento de clientes
- Criação e acompanhamento de pedidos
- Checkout de pedidos
- Pagamento


Rotas


## Ordem para Execução das APIs

### 1. Gerenciamento de Produtos
```http
# 1.1 Criar um novo produto
POST /products/create
# 1.2 Listar produtos por categoria
GET /products/category/{category}
# 1.3 Atualizar um produto existente
PUT /products/update
```

### 2. Gerenciamento de Clientes
```http
# 2.1 Cadastrar um novo cliente
POST /customers/create
# 2.2 Buscar cliente por CPF
GET /customers/{cpf}
```

### 3. Gerenciamento de Pedidos
```http
# 3.1 Criar um novo pedido (checkout)
POST /orders/checkout
# 3.2 Listar todos os pedidos
GET /orders
# 3.3 Buscar pedido por ID
GET /orders/{order_id}
# 3.4 Atualizar status do pedido
PATCH /orders/{order_id}/status/{status}
```

### 4. Processamento de Pagamentos
```http
# 4.1 Criar pagamento
POST /payments/create
# 4.2 Verificar status do pagamento
GET /payments/{payment_id}/status
# 4.3 Atualizar status do pagamento (recebe a notificação webhook)
POST /payments/{payment_id}/status
```

### 5. Mock de Pagamento (Simulação)
```http
# 5.1 Simular atualização de status de pagamento
POST /mock/payments
```

## Arquitetura Cloud AWS 
![Arquitetura-AWS](https://www.youtube.com/watch?v=XySUTroo2dk)


## Arquitetura de negócio

1. Order
- Fluxo de Checkout (Criar Pedido)
Order
![Checkout](https://drive.google.com/uc?export=view&id=1ZAsgklCNTtMHvifKypVWJNK0ymMNUyeD&filename=imagem.png)

- Fluxo de Busca de Pedidos
![New Get Orders](https://drive.google.com/uc?export=view&id=11VQyMhUNWZEysEv7EYRwc35LxrDL9PFo&filename=imagem.png)

- Fluxo de Busca de Pedidos por ID
![Get Order by Id](https://drive.google.com/uc?export=view&id=1AVUn7FVVSxTvVErpMYOPMTCxWK4TFCOa&filename=imagem.png)

- Fluxo de Atualização de Status de Pedido
![New Update Order Status](https://drive.google.com/uc?export=view&id=1YwpVu4ZQ799VdKtlhcTsnvnXMT2pcdZ0&filename=imagem.png)

2. Customer
- Fluxo de Criação de Cliente
![Create Customer](https://drive.google.com/uc?export=view&id=19nWQRlb4ebtrKKRJMqDq_M3-DsPX_NXr&filename=imagem.png)

- Fluxo de Consulta de Cliente por CPF
![Get Customer By Cpf](https://drive.google.com/uc?export=view&id=10PtT35LG6ur5nFVfKPrtiLmNI8-GmZEN&filename=imagem.png)

3. Product
- Fluxo de Consulta de Produto por Categoria
![GetProduct By Category](https://drive.google.com/uc?export=view&id=17xTgt2EN_NEtQc0EKIYYBUukU5fvncul&filename=imagem.png)

- Fluxo de Criação de Produto
![Create Product](https://drive.google.com/uc?export=view&id=1HCYbOEUTX0zsV7IGiEHdt_B_aQymnfzK&filename=imagem.png)

- Fluxo de Atualização de Produto
![Update Product](https://drive.google.com/uc?export=view&id=1FM-kZDMAkWiPJJXAi2mpIm2nb5pi-L2Y&filename=imagem.png)

4. Payment
- Fluxo de Consulta de Status de Pagamento
![Get payment Status](https://drive.google.com/uc?export=view&id=15cMtUxSZovchB5_MfogsawedBxB2Qwj8&filename=imagem.png)

- Fluxo de Criação de Pagamento
![New Create Payment](https://drive.google.com/uc?export=view&id=1XEut1e-fDMsPKG198Wh0k3SgpHaByY-L&filename=imagem.png)

- Recebe notificação e Atualiza Status de Pagamento e da Ordem
![New Update Status](https://drive.google.com/uc?export=view&id=15USTvFBMc8RK9K7a2pyOigPc-jUCHfkB&filename=imagem.png)