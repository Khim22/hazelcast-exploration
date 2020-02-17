package com.exploration.hazelcast.service.employee;

import com.exploration.hazelcast.service.employee.interfaces.EmployeeRepository;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private HazelcastInstance hazelcastInstance;

    @Autowired
    public EmployeeRepositoryImpl(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public Employee findByEmployeeId(String id) {
        IMap<String, Employee> map = hazelcastInstance.getMap("employee-hazelcast");
        map.set("1", new Employee("1", "hello", Role.Member, null));
        return map.get(id);
    }

    @Override
    public Map<String, Employee> findAllEmployees() {
        IMap<String, Employee> map = hazelcastInstance.getMap("employee-hazelcast");
        return map;
    }

    @Override
    public void createEmployee(String key, Employee employee) {
        System.out.println(hazelcastInstance.getName());
        IMap<String, Employee> map = hazelcastInstance.getMap("employee-hazelcast");
        map.set(key, employee);
    }

    @Override
    public Map<String, Employee> updateEmployee(String key, Employee employee) {
        return null;
    }

    @Override
    public String deleteEmployee(String key, Employee employee) {
        return null;
    }
}
