CREATE TABLE bdrestapi.tbl_produto(
   codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   preco DOUBLE(9,2),
   cod_categoria BIGINT(20)
)ENGINE=InnoDB DEFAULT CHARSET utf8;

ALTER TABLE bdrestapi.tbl_produto  
add constraint fk_produto_categoria
foreign key(cod_categoria) references tbl_categoria (codigo)
ON DELETE NO ACTION
ON UPDATE CASCADE;

INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco) values("LEITE TRADIÇÃO 1L",2,4.19);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco) values("AÇUCAR ITAMARATI 1KG",2,2.65);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco) values("TRIGO DONA BENTA 1KG",2,3.59);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco) values("CAFÉ URUPÁ 500GR",2,5.78);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco) values("MACARRÃO ELIANE 500GR",2,2.79);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco) values("BISCOITO MABEL 800GR",2,7.99);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco) values("CREME DE LEITE ITALAC 50 GR",2,1.99);

INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco)values("NOTE BOOK SANSUNG",15,1800);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco)values("CAMERA FOTOGRAFICA 12 PIXEL",15,445);
INSERT INTO bdrestapi.tbl_produto(nome,cod_categoria,preco)values("SMART BAND SANSUNG",15,299);