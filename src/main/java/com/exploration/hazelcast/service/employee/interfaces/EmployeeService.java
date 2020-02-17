package com.exploration.hazelcast.service.employee.interfaces;

import com.exploration.hazelcast.service.employee.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Map<String, Employee> getAllEmployees();

    void saveEmployee(Employee employee);
}
