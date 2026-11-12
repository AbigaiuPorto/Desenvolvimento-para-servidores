CREATE DATABASE bd_Login;

Use bd_Login;

CREATE TABLE Usuario(
    usuario varchar(10) primary key,
    nome varchar(30) default '',
    senha char(32) default ''
);


-- Usuário de teste (senha: 123, gravada com MD5)
INSERT INTO Usuario (usuario, nome, senha) VALUES ('teste', 'Usuario Teste', md5('123'));

SELECT * FROM Usuario;
