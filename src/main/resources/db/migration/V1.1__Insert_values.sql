insert into users (login, password, role, created, updated, is_deleted)
values ('Kyle', '$2a$10$WD5gHHObTC2mRCIS2BJFjeiQoAnZriMbUUMKy.xcxmEcDw82b32H.', 'ROLE_ADMIN', '2022-09-26',
        '2022-09-26', false),
       ('Mike', '$2a$10$r4HxiS.59e9CbEa9hnCTm.OQfyC4tJBU43Wm1MP7Af423A5Tm1w9y', 'ROLE_USER', '2022-09-26',
        '2022-09-26', false),
       ('Peter', '$2a$10$YLdgwBNzJQzczQq8YjZ7eu/U2maNfv1NXUtmz5nXlo2qznWMcZSVa', 'ROLE_USER', '2022-09-26',
        '2022-09-26', false),
       ('Andy', '$2a$10$ible0yWIvqn5JPFcibyMBeYdGNCKDL2cEqBzFHRtiSyebprBYL0Vi', 'ROLE_USER', '2022-09-26',
        '2022-09-26', false);

insert into books (author, title, year, created, updated, is_available, is_deleted, date_of_issue)
values ('Stephen King', 'It', 2005, '2022-09-26',
        '2022-09-26', false, false, '2022-09-30'),
       ('Stephen King', 'It', 2005, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Herbert Schildt', 'Java 8. The Complete Reference', 2015, '2022-09-26',
        '2022-09-26', false, false, '2022-10-10'),
       ('Herbert Schildt', 'Java 8. The Complete Reference', 2015, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Stephen King', '11/22/63', 2010, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Leo Tolstoy', 'War and Peace', 2004, '2022-09-26',
        '2022-09-26', false, false, '2022-09-26'),
       ('Herbert Schildt', 'Java: A Beginner''s Guide, 5th edition', 2012, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Cay Horstmann', 'Core Java SE 9 for the Impatient', 2018, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Herbert Schildt', 'Java 8. The Complete Reference', 2015, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Stephen King', '11/22/63', 2012, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Ray Bradbury', 'Fahrenheit 451', 2017, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Cay Horstmann', 'Core Java SE 9 for the Impatient', 2018, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Leo Tolstoy', 'War and Peace', 2004, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Herbert Schildt', 'Java: A Beginner''s Guide, 5th edition', 2012, '2022-09-26',
        '2022-09-26', true, false, null),
       ('Ray Bradbury', 'Fahrenheit 451', 2017, '2022-09-26',
        '2022-09-26', true, false, null);

insert into users_books (user_id, book_id)
values (2, 1),
       (2, 6),
       (3, 3);