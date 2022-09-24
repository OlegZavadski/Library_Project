package com.tms.repository.impl;

import com.tms.model.Book;
import com.tms.model.Client;
import com.tms.repository.ClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    public static final String GET_BY_LOGIN_AND_PASSWORD = "SELECT * FROM clients WHERE login=? AND password=?";
    public static final String GET_BY_LOGIN = "SELECT * FROM clients WHERE login=?";
    //    public static final String GET_ALL_USERS = "SELECT * FROM clients";
    public static final String GET_ONLY_USERS = "SELECT * FROM clients WHERE role=?";
    private final SessionFactory sessionFactory;

    @Autowired
    public ClientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Client client = session.find(Client.class, id);
        transaction.commit();
        session.close();
        return client;
    }

    @Override
    public void registrationClient(Client client) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }

    @Override
    public Client findByLoginAndPassword(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<Client> nativeQuery = session.createSQLQuery(GET_BY_LOGIN_AND_PASSWORD).addEntity(Client.class);
        nativeQuery.setParameter(1, login).setParameter(2, password);
        Client client = nativeQuery.getResultStream().findFirst().orElse(null);
        transaction.commit();
        session.close();
        return client;
    }

    @Override
    public Client findByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<Client> nativeQuery = session.createSQLQuery(GET_BY_LOGIN).addEntity(Client.class);
        nativeQuery.setParameter(1, login);
        Client client = nativeQuery.getResultStream().findFirst().orElse(null);
        transaction.commit();
        session.close();
        return client;
    }

    @Override
    public List<Client> findAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery<Client> nativeQuery = session.createSQLQuery(GET_ONLY_USERS).addEntity(Client.class);
        nativeQuery.setParameter(1, "ROLE_USER");
        List<Client> users = nativeQuery.getResultList();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Client client = session.find(Client.class, id);
        session.delete(client);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Book> getAllBooks(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = session.find(Client.class, id).getBooks();
        transaction.commit();
        session.close();
        return books;
    }
}