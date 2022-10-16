package com.tms.repository;

import com.tms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "select * from books inner join users_books ub on books.id = ub.book_id where (CURRENT_DATE-20) >= date_of_issue order by date_of_issue", nativeQuery = true)
    List<Book> showOverdueBooks();
}
