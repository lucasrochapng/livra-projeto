CREATE TABLE IF NOT EXISTS categoria(
    id integer primary key autoincrement not null,
    nome varchar(255) not null,
    descricao varchar(255) not null
);

CREATE TABLE IF NOT EXISTS tarefa(
    id integer primary key autoincrement not null,
    nome varchar(255) not null,
    descricao varchar(255) not null,
    dataPrazo date not null,
    categoriaId int not null,
    foreign key (categoriaId) references categoria(id)
);

CREATE TABLE IF NOT EXISTS usuarios(
    id int AUTO_INCREMENT not null,
    nome varchar(255) not null,
    nomeUsuario varchar(255) not null,
    senha varchar(255) not null,
    telefone int not null,
    idade int not null,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS autores(
    id int AUTO_INCREMENT not null,
    nome varchar(255) not null,
    PRIMARY KEY(id)
);