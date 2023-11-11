package com.spring.astabanksecurity.service;

import com.spring.astabanksecurity.entity.Notice;
import com.spring.astabanksecurity.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> getNotices() {
        return noticeRepository.findAllActiveNotices();
    }

}
