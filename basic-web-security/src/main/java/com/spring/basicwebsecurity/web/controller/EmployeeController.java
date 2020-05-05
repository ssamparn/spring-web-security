package com.spring.basicwebsecurity.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/employee")
    public String getEmployee(@RequestParam (value = "employeeName") String employeeName) {
        return employeeName;
    }

    @GetMapping("/admin/employee")
    public String getAdmin(@RequestParam (value = "employeeName") String employeeName) {
        return "Hello Admin" + employeeName;
    }
}
