
USE tech-challenge;

-- Create table 'cliente'
CREATE TABLE IF NOT EXISTS cliente (
  cpf VARCHAR(14) NOT NULL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  telefone VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS categoria (
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  descricao VARCHAR(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS produto (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  categoria_id BIGINT NOT NULL,
  preco DOUBLE NOT NULL,
  FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE pedido (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  cliente_cpf varchar(14) NOT NULL,
  status VARCHAR(255),
  FOREIGN KEY (cliente_cpf) REFERENCES cliente(cpf)
);

CREATE TABLE item_pedido (
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    FOREIGN KEY (pedido_id) REFERENCES `pedido`(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

CREATE TABLE pagamento (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  pedido_id BIGINT NOT NULL,
  status VARCHAR(255) NOT NULL,
  valor DOUBLE NOT NULL,
  CONSTRAINT FK_pagamento_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE
);

INSERT INTO categoria (nome, descricao)
VALUES
  ('Lanche', 'X-Salada, sanduíches, hot dog, etc'),
  ('Acompanhamento', 'Batata frita, maça, nugget, etc'),
  ('Sobremesa', 'Trufa, torta de maça, sorvete, etc'),
  ('Bebida', 'Refrigerantes, sucos, água mineral, etc');
