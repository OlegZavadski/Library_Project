insert into clients (login, password, role, created, updated)
values ('Kyle', '$2a$10$WD5gHHObTC2mRCIS2BJFjeiQoAnZriMbUUMKy.xcxmEcDw82b32H.', 'ROLE_ADMIN', '2022-09-26',
        '2022-09-26'),
       ('Mike', '$2a$10$r4HxiS.59e9CbEa9hnCTm.OQfyC4tJBU43Wm1MP7Af423A5Tm1w9y', 'ROLE_USER', '2022-09-26',
        '2022-09-26'),
       ('Peter', '$2a$10$YLdgwBNzJQzczQq8YjZ7eu/U2maNfv1NXUtmz5nXlo2qznWMcZSVa', 'ROLE_USER', '2022-09-26',
        '2022-09-26'),
       ('Andy', '$2a$10$ible0yWIvqn5JPFcibyMBeYdGNCKDL2cEqBzFHRtiSyebprBYL0Vi', 'ROLE_USER', '2022-09-26',
        '2022-09-26');

insert into books (author, name, count, created, updated)
values ('Stephen King', 'It', 2, '2022-09-26',
        '2022-09-26'),
       ('Stephen King', '11/22/63', 2, '2022-09-26',
        '2022-09-26');

insert into clients_books (client_id, book_id)
values (2, 1),
       (2, 2),
       (3, 2);