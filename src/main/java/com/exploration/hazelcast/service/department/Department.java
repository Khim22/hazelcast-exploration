package com.exploration.hazelcast.service.department;

import com.exploration.hazelcast.service.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Department {
    private String departmentId;
    private String name;
    private List<Employee> employees;
}
