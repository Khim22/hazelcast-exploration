package com.exploration.hazelcast.service.employee;

import com.exploration.hazelcast.service.employee.interfaces.EmployeeRepository;
import com.exploration.hazelcast.service.employee.interfaces.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Spy
    EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

    @Test
    public void CreateEmployee_ShouldSaveEmployeeInRepo() {
        // arrange
        Employee member = new Employee("4", "Darren", Role.Member, null);

        // act
        employeeService.saveEmployee(member);

        // assert
        verify(employeeRepository, times(1)).createEmployee(member.getEmployeeId(), member);
    }

    @Test
    public void GetAllEmployees_ShouldReturnListOfAllEmployees() {
        // arrange
        Employee head = new Employee("1", "joh", Role.Head, null);
        Employee manager = new Employee("2", "sandy", Role.Manager, head);
        Employee lead = new Employee("3", "ismail", Role.Lead, manager);
        Employee member = new Employee("4", "Darren", Role.Member, lead);
        Map<String, Employee> allEmployees = Map.ofEntries(
                Map.entry(head.getEmployeeId(), head),
                Map.entry(manager.getEmployeeId(), manager),
                Map.entry(lead.getEmployeeId(), lead),
                Map.entry(member.getEmployeeId(), member)
        );

        when(employeeRepository.findAllEmployees()).thenReturn(allEmployees);

        // act
        Map<String, Employee> result = employeeService.getAllEmployees();

        // assert
        assertThat(result).containsValues(head, manager, lead, member);
    }

}
