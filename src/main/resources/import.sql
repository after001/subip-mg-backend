-- Pessoas
INSERT INTO tb_pessoa (nome, cpf, email) VALUES ('Ana Souza', '11111111111', 'ana.souza@email.com');
INSERT INTO tb_pessoa (nome, cpf, email) VALUES ('Bruno Lima', '22222222222', 'bruno.lima@email.com');
INSERT INTO tb_pessoa (nome, cpf, email) VALUES ('Carla Rocha', '33333333333', 'carla.rocha@email.com');

-- Usuarios
INSERT INTO tb_usuario (login, perfil, pessoa_id) VALUES ('ana', 'LEITOR', 1);
INSERT INTO tb_usuario (login, perfil, pessoa_id) VALUES ('bruno', 'ATENDENTE', 2);
INSERT INTO tb_usuario (login, perfil, pessoa_id) VALUES ('carla', 'ADMIN', 3);

-- Generos
INSERT INTO tb_genero (descricao) VALUES ('Romance');
INSERT INTO tb_genero (descricao) VALUES ('Realismo');
INSERT INTO tb_genero (descricao) VALUES ('Literatura Brasileira');

-- Livros
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Dom Casmurro', '9788535910663', 1);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('O Cortico', '9788572326972', 2);
INSERT INTO tb_livro (titulo, isbn, genero_id) VALUES ('Capitaes da Areia', '9788535914067', 3);

-- Bibliotecas
INSERT INTO tb_biblioteca (nome) VALUES ('Biblioteca Publica Estadual de Minas Gerais');
INSERT INTO tb_biblioteca (nome) VALUES ('Biblioteca Municipal de Timoteo');
INSERT INTO tb_biblioteca (nome) VALUES ('Biblioteca Comunitaria Vale do Aco');

-- Exemplares
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0001', 'DISPONIVEL', 1, 1);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0002', 'EMPRESTADO', 1, 2);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0003', 'DISPONIVEL', 2, 1);
INSERT INTO tb_exemplar (tombo, situacao, livro_id, biblioteca_id) VALUES ('TOMBO-0004', 'INDISPONIVEL', 3, 3);
