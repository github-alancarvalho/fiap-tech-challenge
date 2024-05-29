# Tech Challenge - SOAT07

Desafio desenvolvido com DDD, Docker e Arquitetura Hexagonal para o curso de Software Architecture da FIAP Pós Tech.

## Versão
1.0.0

## Índice
<a href="#tecnologias">Tecnologias</a> •
<a href="#requisitos-mínimos">Requisitos minimos</a> •
<a href="#como-rodar">Como rodar</a> •
<a href="criterios-de-aceite">Criterios de aceite</a> •
<a href="#autores">Autores</a>

## Tecnologias
<div style="display: inline_block"><br>
    <img align="center" alt="java" height="50" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg">    
    <img align="center" alt="jspring" height="40" width="80" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" />  
    <img align="center" alt="mariaDB" height="50" width="80" src="https://github.com/devicons/devicon/blob/v2.16.0/icons/mariadb/mariadb-original.svg">       
</div>

## Requisitos mínimos
- git
- docker e docker-compose

## Como rodar
Clone este repositório
```bash
$ git clone https://github.com/github-alancarvalho/fiap-tech-challenge.git
```
- Abra o projeto na IDE de sua preferência

- No terminal
  docker compose up

- Quando os containers estiverem rodando com sucesso, executar os comandos abaixo para efetuar a carga inicial das categorias.
  - docker exec -it mariadb-techchallenge bash
    apt-get update
    apt-get install mysql-client
    mysql -u root -prootroot tech-challenge < /docker-entrypoint-initdb.d/init.sql
    exit


## Problema
Há uma lanchonete de bairro que está expandindo devido seu grande sucesso. Porém, com a expansão e sem um sistema de controle de pedidos, o atendimento aos clientes pode ser caótico e confuso. Por exemplo, imagine que um cliente faça um pedido complexo, como um hambúrguer personalizado com ingredientes específicos, acompanhado de batatas fritas e uma bebida. O atendente pode anotar o pedido em um papel e entregá-lo à cozinha, mas não há garantia de que o pedido será preparado corretamente.
Sem um sistema de controle de pedidos, pode haver confusão entre os atendentes e a cozinha, resultando em atrasos na preparação e entrega dos pedidos. Os pedidos podem ser perdidos, mal interpretados ou esquecidos, levando à insatisfação dos clientes e a perda de negócios.
Em resumo, um sistema de controle de pedidos é essencial para garantir que a lanchonete possa atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada. Sem ele, expandir a lanchonete pode acabar não dando certo, resultando em clientes insatisfeitos e impactando os negócios de forma negativa.
Para solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente, com as seguintes funcionalidades:

Pedido: Os clientes são apresentados a uma interface de seleção na qual podem optar por se identificarem via CPF, se cadastrarem com nome, e-mail ou não se identificar, podendo montar o combo na seguinte sequência, sendo todas elas opcionais: Lanche, Acompanhamento, Bebida e Sobremesa.
Em cada etapa é exibido o nome, descrição e preço de cada produto.

Pagamento: O sistema deverá possuir uma opção de pagamento integrada para MVP. A forma de pagamento oferecida será via QRCode do Mercado Pago.

Acompanhamento: Uma vez que o pedido é confirmado e pago, ele é enviado para a cozinha para ser preparado. Simultaneamente deve aparecer em um monitor para o cliente acompanhar o progresso do seu pedido com as seguintes etapas:
- Recebido
- Em preparação
- Pronto
- Finalizado


Deve ser possível acompanhar os pedidos em andamento e tempo de espera de cada pedido.

Entrega: Quando o pedido estiver pronto, o sistema deverá notificar o cliente que ele está pronto para retirada. Ao ser retirado, o pedido deve ser atualizado para o status finalizado.

Além das etapas do cliente, o estabelecimento precisa de um acesso administrativo:

Gerenciar clientes: Com a identificação dos clientes o estabelecimento pode trabalhar em campanhas promocionais.
Gerenciar produtos e categorias: Os produtos dispostos para escolha do cliente serão gerenciados pelo estabelecimento, definindo nome, categoria, preço, descrição e imagens. Para esse sistema teremos categorias fixas: Lanche, Acompanhamento, Bebida e Sobremesa.

As informações dispostas no sistema de pedidos precisarão ser gerenciadas pelo estabelecimento através de um painel administrativo.

## Critérios de aceite
Entregável 1: Documentação do sistema (DDD) com Event Storming, incluindo todos os passos/tipos de diagrama mostrados na aula 6 do módulo de DDD, e utilizando a linguagem ubíqua, dos seguintes fluxos:
a. Realização do pedido e pagamento
b. Preparação e entrega do pedido

Entregável 2:Uma aplicação para todo o sistema de backend (monolito) que deverá ser desenvolvido seguindo os padrões apresentados nas aulas:
Utilizando arquitetura hexagonal
APIs:
- Cadastro do Cliente
- Identificação do Cliente via CPF
- Criar, editar e remover produtos
- Buscar produtos por categoria
- Fake checkout, apenas enviar os produtos escolhidos para a fila. O checkout é a finalização do pedido.
- Listar os pedidos
  Disponibilizar também o Swagger para consumo dessas APIs
  Banco de dados à sua escolha - Inicialmente deveremos trabalhar e organizar a fila dos pedidos apenas em banco de dados

Entregável 3: A aplicação deve ser entregue com um Dockerfile configurado para executá-la corretamente, e um docker-compose.yml para subir o ambiente completo.


# Solução
## Domain Storytelling
### Fluxo Realização do pedido e pagamento:
![Fast Food - Pedido e Pagamento_2024-04-14 (4)](https://github.com/github-alancarvalho/fiap-tech-challenge-initial/assets/166860938/fd01d09f-12f7-4d5c-ab10-85ee8a89566c)


### Fluxo Preparação e entrega do pedido:
![Fast Food - Preparação e entrega do pedido_2024-04-14 (2)](https://github.com/github-alancarvalho/fiap-tech-challenge-initial/assets/166860938/022d29a1-c181-47bd-bb56-95237aa196d3)


## Event Storming
Para documenação dos fluxos foi criado um board na no Miro onde foi desenhado o fluxo de negócio e disponibilizado o dicionário de linguagem ubíqua.
https://miro.com/app/board/uXjVKUnohjM=/?share_link_id=725040074360

### Domínios
- Subdomínio Principal:
    - Gestão de Pedido
- Subdomínio Genérico:
    - Lógica de pagamento integrada ao mercado pago.
    - Autenticação e Autorização
    - Notificações - Envio de notificações sobre o progresso dos pedidos para os clientes
    - Gestão de estoque
- Subdomínio Suporte:
    - Gestão de clientes
    - Gestão de Produtos e Categorias,
    - Acompanhamento online de pedidos,
    - Interface do usuário


### Contextos delimitados

- Clientes
- Produto(Cardápio)
- Pedido (Realização do pedido)
- Pagamento
- Sistema Externo - Mercado Pago
- Preparação
- Entrega
- Acompanhamento




### Dicionário de linguagem ubíqua
- Cliente: Pessoa que deseja efetuar ou já tenha efetuado uma compra no restaurante
- Cozinheiro: Pessoa que prepara o pedido
- Item: Item é uma referência a qualquer produto disponível ou adicionado ao pedido.
- Pedido: Itens que o cliente deseja comprar ou que já comprou.
- Identificação: Pode se identificar usando CPF, nome, e-mail ou não se identificar.

Cardápio: A lista de ítens disponíveis para compra. Esses itens são agrupados em 4 categorias: Lanche, Acompanhamento, Bebida e Sobremesa. Todos os itens são opcionais.
- Lanche: No momento da compra, lanche pode ser, um hamburguer simples, cachorro quente, hamburguer gourmet, hamburguer vegano, entre outros
- Acompanhamento: No momento da compra, acompanhamento pode ser, por exemplo: Batata frita, frango frito, maçã, entre outros produtos.
- Bebida: No momento da compra, bebida pode ser, por exemplo: Sucos, água, chás gelados, entre outros.
- Sobremesa: No momento da compra, sobremesa pode ser, por exemplo: sorvetes, frutas, tortas, bolos, milkshakes especiais ou outros doces.

Etapas do Progresso do Pedido:
- RECEBIDO: Pagamento confirmado e o pedido está na fila para ser preparado
- EM PREPARAÇÃO: Preparação do pedido foi iniciada
- PRONTO: Pedido está liberado para ser retirado pelo cliente
- FINALIZADO: Pedido foi retirado pelo cliente


---

### Api
- Após subir a aplicação recomendo usar o Swagger para testar os endpoints
    - http://localhost:8080/swagger-ui/index.html


## Autor
- [Alan Carvalho](https://github.com/github-alancarvalho)