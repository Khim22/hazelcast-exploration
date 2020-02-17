package com.exploration.hazelcast.service.employee;

import com.exploration.hazelcast.service.employee.interfaces.EmployeeRepository;
import com.exploration.hazelcast.service.employee.interfaces.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Map<String, Employee> getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.createEmployee(employee.getEmployeeId(), employee);
    }
}
