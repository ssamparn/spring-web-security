package com.spring.astabanksecurity.web.controller;

import com.spring.astabanksecurity.entity.Notice;
import com.spring.astabanksecurity.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class NoticesRestController {

    private final NoticeService noticeService;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> notices = noticeService.getNotices();
        if (notices != null )
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        else return null;
    }
}
