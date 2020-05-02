package com.spring.basicwebsecurity.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class EmployeeController {

    @GetMapping("/employee")
    public String getEmployee(@RequestParam (value = "employeeName") String employeeName) {
        return employeeName;
    }
}
