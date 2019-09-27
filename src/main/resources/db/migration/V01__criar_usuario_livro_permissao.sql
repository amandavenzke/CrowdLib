CREATE TABLE IF NOT EXISTS usuario (
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

CREATE TABLE IF NOT EXISTS livro (
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

CREATE TABLE IF NOT EXISTS permissao (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
);

