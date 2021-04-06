CREATE TABLE bdrestapi.tbl_produto(
   codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   preco DOUBLE(9,2),
   cod_categoria BIGINT(20),
   ativo BOOLEAN NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET utf8;

ALTER TABLE bdrestapi.tbl_produto  
add constraint fk_produto_categoria
foreign key(cod_categoria) references tbl_categoria (cod_categoria)
ON DELETE NO ACTION
ON UPDATE CASCADE;

INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo) values("LEITE TRADIÇÃO 1L",2,4.19,1);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo) values("AÇUCAR ITAMARATI 1KG",2,2.65,1);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo) values("TRIGO DONA BENTA 1KG",2,3.59,1);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo) values("CAFÉ URUPÁ 500GR",2,5.78,1);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo) values("MACARRÃO ELIANE 500GR",2,2.79,1);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo) values("BISCOITO MABEL 800GR",2,7.99,1);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo) values("CREME DE LEITE ITALAC 50 GR",2,1.99,1);

INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo)values("NOTE BOOK SANSUNG",4,1800,1);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo)values("CAMERA FOTOGRAFICA 12 PIXEL",4,445,1);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco,ativo)values("SMART BAND SANSUNG",4,299,1);