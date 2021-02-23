INSERT INTO usuario(id, email, senha, data_criacao) values (1l, 'maurice@gmail.com', '$2a$10$dxPsk06lN3rI4QA6HbHub.vkavYivy/nRRoWHVHZ5KXcoiMP9LFHq', NOW());
INSERT INTO usuario(id, email, senha, data_criacao) values (2l, 'maria@gmail.com', '$2a$10$dxPsk06lN3rI4QA6HbHub.vkavYivy/nRRoWHVHZ5KXcoiMP9LFHq', NOW());

INSERT INTO categoria(id, nome) values (1L, 'Eletrônicos');
INSERT INTO categoria(id, nome, categoria_id) values (2L, 'Celular', 1);

INSERT INTO produto(id, nome, valor, quantidade, descricao, categoria_id, usuario_id) values (1L, 'Samsung A10', 2000.0, 5, 'Um celular supimpa', 1, 2);