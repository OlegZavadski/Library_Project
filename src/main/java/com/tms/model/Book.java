package com.tms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String title;
    private Integer year;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    private Date updated;
    @Column(name = "is_available")
    private boolean isAvailable = true;
    @Column(name = "is_deleted")
    private boolean isDeleted = false;
    @Column(name = "date_of_issue")
    private Date dateOfIssue;
    @ManyToOne
    @JoinTable(name = "users_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public Book(String author, String title, Integer year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }
}
