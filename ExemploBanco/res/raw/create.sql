CREATE TABLE usuarios_tbl (
    id_usuarios  INTEGER PRIMARY KEY autoincrement,
    usuario varchar(45) NOT NULL ,
    senha varchar(45)  NOT NULL,
    nome_completo varchar(45)  NOT NULL
);

insert into usuarios_tbl(usuario,senha,nome_completo) values("gbzarelli","123","guilherme");