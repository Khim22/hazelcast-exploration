package com.exploration.hazelcast.service.employee;

import com.exploration.hazelcast.service.config.HazelcastConfiguration;
import com.exploration.hazelcast.service.employee.interfaces.EmployeeRepository;
import com.hazelcast.client.test.TestHazelcastFactory;
import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = { HazelcastConfiguration.class } )
public class EmployeeRepositoryTest {
    private TestHazelcastFactory hazelcastFactory = new TestHazelcastFactory();

    private HazelcastInstance member;
    private HazelcastInstance client;

    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
        Config config = new Config();
        config.setInstanceName("test-employee");
        member = hazelcastFactory.newHazelcastInstance(config);
        client = hazelcastFactory.newHazelcastClient();
        employeeRepository = new EmployeeRepositoryImpl(member);
    }

    @Test
    public void createEmployee_shouldSaveInCache() throws Exception {
        // arrange
        Employee inserted = new Employee("1", "Test Employee", Role.Member, null);

        // act
        employeeRepository.createEmployee("1", inserted);

        // assert
        final IMap<String, Employee> testMap = client.getMap("employee-hazelcast");
        final Employee result = testMap.get("1");
        assertThat(result).isEqualTo(inserted);
    }

    @Test
    public void findAllEmployees_shouldReturnMapOfAllEmployees(){
        // arrange
        Employee head = new Employee("1", "joh", Role.Head, null);
        Employee manager = new Employee("2", "sandy", Role.Manager, head);
        Employee lead = new Employee("3", "ismail", Role.Lead, manager);
        Employee memberEmp = new Employee("4", "Darren", Role.Member, lead);

        member.getMap("employee-hazelcast").set(head.getEmployeeId(), head);
        member.getMap("employee-hazelcast").set(head.getEmployeeId(), head);
        member.getMap("employee-hazelcast").set(manager.getEmployeeId(), manager);
        member.getMap("employee-hazelcast").set(lead.getEmployeeId(), lead);
        member.getMap("employee-hazelcast").set(memberEmp.getEmployeeId(), memberEmp);

        // act
        Map<String, Employee> result = employeeRepository.findAllEmployees();

        // assert
        final IMap<String, Employee> testMap = client.getMap("employee-hazelcast");
        assertThat(result).containsValues(head, manager, lead, memberEmp);
    }
}
