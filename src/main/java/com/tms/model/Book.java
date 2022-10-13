package com.tms.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "books")

@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String name;
    private int count;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updated;
    @ManyToMany(mappedBy = "books")
    private Set<User> users = new HashSet<>();

    public Book(String author, String name, int count) {
        this.author = author;
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "{Author='" + author + '\'' +
                ", Name='" + name + '\'' + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
