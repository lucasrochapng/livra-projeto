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

CREATE TABLE IF NOT EXISTS livros(
    id int AUTO_INCREMENT not null,
    titulo varchar(255) not null,
    autorId int not null,
    genero varchar(255) not null,
    descricao varchar(255) not null,
    PRIMARY KEY(id),
    foreign key(autorId) references autores(id)
);

CREATE TABLE IF NOT EXISTS livros_usuario(
    id int AUTO_INCREMENT not null,
    id_livro int not null,
    id_usuario int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(id_livro) references livros(id),
    FOREIGN KEY(id_usuario) references usuarios(id)
);

CREATE TABLE IF NOT EXISTS troca(
    id int AUTO_INCREMENT not null,
    id_livro int not null,
    id_usuario int not null,
    estado int,
    PRIMARY KEY(id),
    FOREIGN KEY(id_livro) references livros(id),
    FOREIGN KEY(id_usuario) references usuarios(id)
);