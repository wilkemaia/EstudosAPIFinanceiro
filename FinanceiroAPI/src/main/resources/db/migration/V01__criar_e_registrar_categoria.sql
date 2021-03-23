CREATE TABLE tbl_categoria(
 codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
 nome VARCHAR(50) NOT NULL
 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO tbl_categoria(nome)values('Lazer');
INSERT INTO tbl_categoria(nome)values('Alimentação');
INSERT INTO tbl_categoria(nome)values('Farmácia');
INSERT INTO tbl_categoria(nome)values('Outros');