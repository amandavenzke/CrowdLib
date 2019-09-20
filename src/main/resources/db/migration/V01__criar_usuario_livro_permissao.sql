DELETE FROM crowdlib.flyway_schema_history where installed_rank = '01';
DELETE FROM crowdlib.flyway_schema_history where installed_rank = '02';
DELETE FROM crowdlib.flyway_schema_history where installed_rank = '03';

DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS livro;
DROP TABLE IF EXISTS lpermissao;
DROP TABLE IF EXISTS lusuario_permissao;

CREATE TABLE usuario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(150) NOT NULL,
    logradouro VARCHAR(100),
	complemento VARCHAR(50),
	numero INT(5),
	bairro VARCHAR(50),
	cidade VARCHAR(50),
	estado VARCHAR(2),
	cep VARCHAR(9),
	celular VARCHAR(15)
);

CREATE TABLE livro (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    edicao VARCHAR(50) NOT NULL,
    isbn INT(15) NOT NULL,
    paginas INT(10) NOT NULL,
    editora VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,  
    conservacao VARCHAR(20) NOT NULL
);

CREATE TABLE permissao (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
);

CREATE TABLE usuario_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
);

INSERT INTO crowdlib.usuario (nome, sexo, data_nascimento, email, senha, logradouro, complemento, numero, bairro, cidade, estado, cep, celular) VALUES ('Amanda Venzke', 'FEMININO', '1991-05-30', 'patriciavenzke@gmail.com', '$2a$10$u.WjRhyOAsgnvYWUFA86W.ALODHF7u4IauJOO75ygF0RLm6IFliVy', 'Rua Jau', 'Bloco C Apto 71', '51', 'Santo Antonio',	'Osasco', 'SP',	'6126320','551198090987');

INSERT INTO crowdlib.PERMISSAO (descricao) VALUES ('ROLE_CADASTRAR_USUARIO');
INSERT INTO crowdlib.usuario_permissao (id_usuario, id_permissao) VALUES (1, 1);