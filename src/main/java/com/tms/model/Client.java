package com.tms.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")

@NoArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date created;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date updated;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "clients_books",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books = new ArrayList<>();

    public Client(String login, String password, ROLE role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
