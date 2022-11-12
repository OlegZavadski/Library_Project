package com.tms.service;

import com.tms.model.BookAudit;

import java.util.List;

public interface BookAuditService {
    void save(BookAudit bookAudit);

    List<BookAudit> findAllBookAudit();

    List<BookAudit> findBookAuditByUserId(Integer userId);

}
