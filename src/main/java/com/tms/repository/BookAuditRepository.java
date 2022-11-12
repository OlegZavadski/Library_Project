package com.tms.repository;

import com.tms.model.BookAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookAuditRepository extends JpaRepository<BookAudit, Integer> {
    @Query(value = "select * from book_audit as ba where ba.user_id = :userId order by ba.return_date, ba.title, ba.author, ba.year", nativeQuery = true)
    List<BookAudit> findBookAuditByUserId(Integer userId);

    @Query(value = "select * from book_audit as ba order by ba.return_date, ba.title, ba.author, ba.year", nativeQuery = true)
    List<BookAudit> findAllBookAudit();
}
