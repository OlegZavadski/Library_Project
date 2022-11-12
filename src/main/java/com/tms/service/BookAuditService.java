package com.tms.service;

import com.tms.model.BookAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookAuditService {
    void save(BookAudit bookAudit);

    Page<BookAudit> findAllBookAudit(Pageable pageable);

    Page<BookAudit> findBookAuditByUserId(Integer userId, Pageable pageable);

}
