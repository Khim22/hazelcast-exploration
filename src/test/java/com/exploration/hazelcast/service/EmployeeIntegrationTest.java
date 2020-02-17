package com.exploration.hazelcast.service;

import com.exploration.hazelcast.service.employee.Employee;
import com.exploration.hazelcast.service.employee.Role;
import com.exploration.hazelcast.service.employee.interfaces.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TestRestTemplate restTemplate;

    private final String DOMAIN = "http://localhost:";

    @Test
    public void createEmployee_shouldReturnHttp200(){
        // arrange
        Employee emp = new Employee("1", "dummy", Role.Member, null);

        //act
        ResponseEntity<String> response = restTemplate.postForEntity(DOMAIN + port + "/employee/create", emp, String.class);

        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void GetAllEmployees_shouldReturnMapOfAllEmployees(){
        // arrange
        Employee emp1 = new Employee("1", "dummy", Role.Lead, null);
        Employee emp2 = new Employee("2", "dummy2", Role.Member, emp1);
        Employee emp3 = new Employee("3", "dummy3", Role.Member, emp1);

        employeeRepository.createEmployee(emp1.getEmployeeId(), emp1);
        employeeRepository.createEmployee(emp2.getEmployeeId(), emp2);
        employeeRepository.createEmployee(emp3.getEmployeeId(), emp3);

        // act
        ResponseEntity<String> response = restTemplate.getForEntity(DOMAIN + port + "/employee/all", String.class);

        // assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
