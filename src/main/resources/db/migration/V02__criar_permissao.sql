INSERT INTO crowdlib.usuario (nome, sexo, data_nascimento, email, senha, logradouro, complemento, numero, bairro, cidade, estado, cep, celular) VALUES ('Amanda Venzke', 'FEMININO', '1991-05-30', 'patriciavenzke@gmail.com', '$2a$10$u.WjRhyOAsgnvYWUFA86W.ALODHF7u4IauJOO75ygF0RLm6IFliVy', 'Rua Jau', 'Bloco C Apto 71', '51', 'Santo Antonio',	'Osasco', 'SP',	'6126320','551198090987');

INSERT INTO crowdlib.PERMISSAO (descricao) VALUES ('ROLE_CADASTRAR_USUARIO');
INSERT INTO crowdlib.usuario_permissao (id_usuario, id_permissao) VALUES (1, 1);

--TESTE