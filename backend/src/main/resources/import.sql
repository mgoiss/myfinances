INSERT INTO tb_tipo_operacao (nome, data_criacao) VALUES ('Receita', NOW());
INSERT INTO tb_tipo_operacao (nome, data_criacao) VALUES ('Dispesa', NOW());

INSERT INTO tb_categoria (nome, data_criacao) VALUES ('Alimentação', NOW());
INSERT INTO tb_categoria (nome, data_criacao) VALUES ('Gasolina', NOW());
INSERT INTO tb_categoria (nome, data_criacao) VALUES ('Agua', NOW());
INSERT INTO tb_categoria (nome, data_criacao) VALUES ('Luz', NOW());
INSERT INTO tb_categoria (nome, data_criacao) VALUES ('Cartao Casa', NOW());
INSERT INTO tb_categoria (nome, data_criacao) VALUES ('Streaming', NOW());
INSERT INTO tb_categoria (nome, data_criacao) VALUES ('Salario', NOW());
INSERT INTO tb_categoria (nome, data_criacao) VALUES ('Outros', NOW());

/*USUÁRIO 1*/
INSERT INTO tb_usuario (nome, email, senha) VALUES ('Matheus', 'mrochagois@gmail.com', '1234');

INSERT INTO tb_local_movimento (nome, saldo, status, usuario_id) VALUES ('CARTEIRA', 2000.00, true, 1);
INSERT INTO tb_local_movimento (nome, saldo, status, usuario_id) VALUES ('CARTÃO', -200.00, true, 1);
INSERT INTO tb_local_movimento (nome, saldo, status, usuario_id) VALUES ('NUCONTA', 500.00, true, 1);
INSERT INTO tb_local_movimento (nome, saldo, status, usuario_id) VALUES ('NEON', 500.00, false, 1);

INSERT INTO tb_movimento (descricao, valor, data, local_movimento_id, categoria_id, tipo_operacao_id) VALUES ('SALARIO', 2000.00, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 1, 7, 1)
INSERT INTO tb_movimento (descricao, valor, data, local_movimento_id, categoria_id, tipo_operacao_id) VALUES ('ROUPA', 200.00, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 2, 8, 2)
INSERT INTO tb_movimento (descricao, valor, data, local_movimento_id, categoria_id, tipo_operacao_id) VALUES ('COMISSÃO', 500.00, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 3, 8, 1)

/*USUÁRIO 2*/
INSERT INTO tb_usuario (nome, email, senha) VALUES ('Querem', 'querem@gmail.com', '1234');

INSERT INTO tb_local_movimento (nome, saldo, status, usuario_id) VALUES ('CARTEIRA', 00.00, true, 2);
INSERT INTO tb_local_movimento (nome, saldo, status, usuario_id) VALUES ('CARTÃO INTER', 00.00, true, 2);
INSERT INTO tb_local_movimento (nome, saldo, status, usuario_id) VALUES ('NEON', 00.00, true, 2);

INSERT INTO tb_movimento (descricao, valor, data, local_movimento_id, categoria_id, tipo_operacao_id) VALUES ('CONSULTA MARIA', 60.00, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 5, 8, 1)
INSERT INTO tb_movimento (descricao, valor, data, local_movimento_id, categoria_id, tipo_operacao_id) VALUES ('ROUPA', 20.00, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 6, 8, 2)
INSERT INTO tb_movimento (descricao, valor, data, local_movimento_id, categoria_id, tipo_operacao_id) VALUES ('CONSULTA JOÃO', 500.00, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 7, 8, 1)