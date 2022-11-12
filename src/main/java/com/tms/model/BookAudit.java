package com.tms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book_audit")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    private String author;
    private String title;
    private Integer year;
    @Column(name = "date_of_issue")
    private Date dateOfIssue;
    @Column(name = "return_date")
    private Date returnDate;
    @Column(name = "days_of_use")
    private Integer daysOfUse;

    public BookAudit(Integer userId, String author, String title, Integer year, Date dateOfIssue, Date returnDate, Integer daysOfUse) {
        this.userId = userId;
        this.author = author;
        this.title = title;
        this.year = year;
        this.dateOfIssue = dateOfIssue;
        this.returnDate = returnDate;
        this.daysOfUse = daysOfUse;
    }
}
