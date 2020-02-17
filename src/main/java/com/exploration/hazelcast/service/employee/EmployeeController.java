package com.exploration.hazelcast.service.employee;

import com.exploration.hazelcast.service.employee.interfaces.EmployeeService;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @GetMapping("/all")
    public Map<String, Employee> GetAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/create")
    public boolean CreateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return true;
    }
}
