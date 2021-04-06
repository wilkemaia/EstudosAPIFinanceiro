CREATE TABLE bdrestapi.tbl_categoria(
 cod_categoria BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
 nome VARCHAR(50) NOT NULL
 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO bdrestapi.tbl_categoria(nome)values('Lazer');
INSERT INTO bdrestapi.tbl_categoria(nome)values('Alimentação');
INSERT INTO bdrestapi.tbl_categoria(nome)values('Farmácia');
INSERT INTO bdrestapi.tbl_categoria(nome)values('Informatica');
INSERT INTO bdrestapi.tbl_categoria(nome)values('Outros');