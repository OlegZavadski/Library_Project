package com.tms.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<User> users = new ArrayList<>();

    public Book(String author, String name, int count) {
        this.author = author;
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
//        return "Book{" +
//                "author='" + author + '\'' +
//                ", name='" + name + '\'' +
//                '}';
        return "{Author='" + author + '\'' +
                ", Name='" + name + '\'' + "}";
    }
}
