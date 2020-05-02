package com.spring.basicwebsecurity.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class EmployeeEntity {

    @Id
    private UUID employeeId;

}
