package com.tms.service.impl;

import com.tms.model.BookAudit;
import com.tms.repository.BookAuditRepository;
import com.tms.service.BookAuditService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookAuditServiceImpl implements BookAuditService {
    private final BookAuditRepository bookAuditRepository;

    public BookAuditServiceImpl(BookAuditRepository bookAuditRepository) {
        this.bookAuditRepository = bookAuditRepository;
    }

    @Override
    public void save(BookAudit bookAudit) {
        bookAuditRepository.save(bookAudit);
    }

    @Override
    public Page<BookAudit> findAllBookAudit(Pageable pageable) {
        return bookAuditRepository.findAllBookAudit(pageable);
    }

    @Override
    public Page<BookAudit> findBookAuditByUserId(Integer userId, Pageable pageable) {
        return bookAuditRepository.findBookAuditByUserId(userId, pageable);
    }
}
