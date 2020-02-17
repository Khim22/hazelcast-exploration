package com.exploration.hazelcast.service.employee.interfaces;

import com.exploration.hazelcast.service.employee.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    Employee findByEmployeeId(String id);
    Map<String, Employee> findAllEmployees();
    void createEmployee(String key, Employee employee);
    Map<String, Employee> updateEmployee(String key, Employee employee);
    String deleteEmployee(String key, Employee employee);
}
