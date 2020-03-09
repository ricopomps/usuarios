CREATE DATABASE db_usuarios;
USE db_usuarios;
CREATE TABLE usuarios (
id int NOT NULL AUTO_INCREMENT,
nome varchar(100) NOT NULL,
email varchar(100) NOT NULL,
senha varchar(100) NOT NULL,
primary key(id)
);
CREATE TABLE telefones (
id int NOT NULL AUTO_INCREMENT,
ddd int NOT NULL,
numero varchar(15) NOT NULL,
tipo varchar(15) NOT NULL,
id_usuario int,
primary key(id),
 FOREIGN KEY (id_usuario) REFERENCES usuarios(id)

);
insert into usuarios (nome,email,senha) VALUES ("teste","teste","teste");
insert into telefones (ddd,numero,tipo,id_usuario) VALUES (081,"992248823","Celular",1);