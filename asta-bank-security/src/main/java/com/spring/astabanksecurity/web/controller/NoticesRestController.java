package com.spring.astabanksecurity.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesRestController {

    @GetMapping("/notices")
    public String getNotices() {
        return "Here are the notices details from the DB";
    }
}
