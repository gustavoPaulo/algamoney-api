CREATE TABLE pessoa(
	
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo tinyint NOT NULL,
    logradouro VARCHAR(200),
    numero VARCHAR(10),
    complemento VARCHAR(30),
    bairro VARCHAR(30),
    cep VARCHAR(10),
    cidade VARCHAR(30),
    estado VARCHAR(10)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES('Gustavo', 1, 'Rua Machado de Assis', '720', 'Fundos', 'Parque São Vicente', '11365010', 'São Vicente', 'SP');

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES('Gabriela', 0, 'Av Persio de Queiros Filho', '206', 'Apto 10', 'Catiapoã', '11300710', 'São Vicente', 'SP');