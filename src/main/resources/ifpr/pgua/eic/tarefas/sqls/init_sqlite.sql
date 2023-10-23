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