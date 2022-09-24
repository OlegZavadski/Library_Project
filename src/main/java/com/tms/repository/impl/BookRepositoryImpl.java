package com.tms.repository.impl;

import com.tms.model.Book;
import com.tms.model.Client;
import com.tms.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    public static final String GET_ALL_BOOKS = "SELECT * FROM books";
    private final SessionFactory sessionFactory;

    @Autowired
    public BookRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
    }

    @Override
    public Book getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.find(Book.class, id);
        transaction.commit();
        session.close();
        return book;
    }

    @Override
    public void addBookToClient(Integer idOfBook, Integer idOfClient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Book bookById = session.find(Book.class, idOfBook);
        Client clientById = session.find(Client.class, idOfClient);
        bookById.setCount(bookById.getCount() - 1);
        List<Book> booksOfClient = clientById.getBooks();
        booksOfClient.add(bookById);
        clientById.setBooks(booksOfClient);
        session.update(bookById);
        session.update(clientById);
        transaction.commit();
        session.close();
    }

    @Override
    public void returnBookFromClient(Integer idBook, Integer idClient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Book bookById = session.find(Book.class, idBook);
        Client clientById = session.find(Client.class, idClient);
        bookById.setCount(bookById.getCount() + 1);
        List<Book> booksOfClient = clientById.getBooks();
        booksOfClient.remove(bookById);
        clientById.setBooks(booksOfClient);
        session.update(bookById);
        session.update(clientById);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Book> showAllBooks() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<Book> nativeQuery = session.createSQLQuery(GET_ALL_BOOKS).addEntity(Book.class);
        List<Book> allBooks = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return allBooks;
    }
}
