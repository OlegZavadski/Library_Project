create table if not exists clients
(
    id       integer generated by default as identity,
    login    varchar(255)
        constraint unique_login unique,
    password varchar(255),
    role     varchar(255),
    created  date,
    updated  date,
    primary key (id)
);

create table if not exists books
(
    id      integer generated by default as identity,
    author  varchar(255),
    name    varchar(255),
    count   integer not null,
    created date,
    updated date,
    primary key (id)
);

create table if not exists clients_books
(
    client_id integer not null,
    book_id   integer not null,
    constraint fk_client_id foreign key (client_id) references clients (id),
    constraint fk_book_id foreign key (book_id) references books (id)
);