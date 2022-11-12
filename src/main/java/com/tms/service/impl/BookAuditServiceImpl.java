package com.tms.service.impl;

import com.tms.model.BookAudit;
import com.tms.repository.BookAuditRepository;
import com.tms.service.BookAuditService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<BookAudit> findAllBookAudit() {
        return bookAuditRepository.findAllBookAudit();
    }

    @Override
    public List<BookAudit> findBookAuditByUserId(Integer userId) {
        return bookAuditRepository.findBookAuditByUserId(userId);
    }
}
