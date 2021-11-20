package com.spring.jwtissuer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomePageController {

    @GetMapping("/")
    public String index() {
        log.info("Request received for index.html");
        return "index";
    }
}
