package com.tms.repository;

import com.tms.model.BookAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BookAuditRepository extends JpaRepository<BookAudit, Integer> {
    @Query(value = "select * from book_audit as ba where ba.user_id = :userId order by ba.return_date, ba.title, ba.author, ba.year", nativeQuery = true)
    Page<BookAudit> findBookAuditByUserId(Integer userId, Pageable pageable);

    @Query(value = "select * from book_audit as ba order by ba.return_date, ba.title, ba.author, ba.year", nativeQuery = true)
    Page<BookAudit> findAllBookAudit(Pageable pageable);
}
