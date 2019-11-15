CREATE TABLE IF NOT EXISTS usuario (
	id BIGINT(20) AUTO_INCREMENT,
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
	celular VARCHAR(15) NOT NULL,
	PRIMARY KEY (id, email)
);

CREATE TABLE IF NOT EXISTS avaliacao (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	avaliacao BIGINT(5) NOT NULL,
	comentario TEXT NOT NULL,
	id_usuario BIGINT(20) NOT NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuario(id)	
);

CREATE TABLE IF NOT EXISTS genero (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS idioma (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS livro (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_genero BIGINT(20) NOT NULL,
	id_idioma BIGINT(20) NOT NULL,
	id_usuario BIGINT(20) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    edicao VARCHAR(50) NOT NULL,
    isbn VARCHAR(15) NOT NULL,
    paginas INT(10) NOT NULL,
    editora VARCHAR(100) NOT NULL,
    autor VARCHAR(100) NOT NULL,  
    conservacao VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_genero) REFERENCES genero(id),
    FOREIGN KEY (id_idioma) REFERENCES idioma(id)    
);

CREATE TABLE IF NOT EXISTS troca (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	troca_realizada VARCHAR(10) NOT NULL,
	id_usuario_solicitante BIGINT(20) NOT NULL,
	id_usuario_solicitado BIGINT(20) NOT NULL,
	FOREIGN KEY (id_usuario_solicitante) REFERENCES usuario(id),
	FOREIGN KEY (id_usuario_solicitado) REFERENCES usuario(id)	
);

CREATE TABLE IF NOT EXISTS troca_livro (
	id_troca BIGINT(20) NOT NULL,
	id_livro BIGINT(20) NOT NULL,
	PRIMARY KEY (id_troca, id_livro),
	FOREIGN KEY (id_troca) REFERENCES troca(id),
	FOREIGN KEY (id_livro) REFERENCES livro(id)
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

