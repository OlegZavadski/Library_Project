package com.tms.repository;

import com.tms.model.Book;
import com.tms.model.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select * from books as b inner join users_books as ub on b.id = ub.book_id where b.is_deleted = false and b.date_of_issue <= (CURRENT_DATE-20) order by b.date_of_issue", nativeQuery = true)
    List<Book> findOverdueBooks();

    @Query(value = "select * from books as b full join users_books as ub on b.id = ub.book_id where b.is_deleted = false order by b.title", nativeQuery = true)
    List<Book> findAllNotDeletedBooks();

    @Query(value = "select b.author, b.title, b.year, count(b.title) from books as b where b.is_available = true and b.is_deleted = false group by b.author, b.title, b.year order by b.author, b.title, b.year", nativeQuery = true)
    Page<BookProjection> findAllBooksWithCount(Pageable pageable);

    @Query(value = "select b.title, b.year, count(b.title) from books as b where b.is_available = true and b.is_deleted = false and b.author = :author group by b.title, b.year order by b.title", nativeQuery = true)
    List<BookProjection> findByAuthorOrderByTitle(String author);

    @Query(value = "select b.author, b.title, b.year, count(b.title) from books as b where b.is_available = true and b.is_deleted = false and upper(b.author) like upper(:forSearch) or upper(b.title) like upper(:forSearch) group by b.author, b.title, b.year", nativeQuery = true)
    Page<BookProjection> getBooksFromSearch(String forSearch, Pageable pageable);
}
