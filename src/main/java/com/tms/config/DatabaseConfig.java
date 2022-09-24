package com.tms.config;


import com.tms.model.Book;
import com.tms.model.Client;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class DatabaseConfig {
    @Value("${hibernate.connection.driver_class}")
    private String driver;
    @Value("${hibernate.connection.url}")
    private String url;
    @Value("${hibernate.connection.username}")
    private String username;
    @Value("${hibernate.connection.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hbm2ddl;

    @Bean
    public Configuration configuration() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", driver);
        cfg.setProperty("hibernate.connection.url", url);
        cfg.setProperty("hibernate.connection.username", username);
        cfg.setProperty("hibernate.connection.password", password);
        cfg.setProperty("hibernate.dialect", dialect);
        cfg.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
        cfg.addAnnotatedClass(Client.class);
        cfg.addAnnotatedClass(Book.class);
        return cfg;
    }

    @Bean
    public SessionFactory factory(Configuration cfg) {
        return cfg.buildSessionFactory();
    }
}
