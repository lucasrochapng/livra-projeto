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

CREATE TABLE IF NOT EXISTS lv_livros(
    id int AUTO_INCREMENT not null,
    titulo varchar(255) not null,
    autorId int not null,
    genero varchar(255) not null,
    descricao varchar(255) not null,
    contato varchar(255) not null,
    usuarioId int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(autorId) references autores(id),
    FOREIGN KEY(usuarioId) references usuarios(id)
);

