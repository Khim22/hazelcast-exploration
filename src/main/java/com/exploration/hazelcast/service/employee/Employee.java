package com.exploration.hazelcast.service.employee;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Employee implements Serializable {
    private String employeeId;
    @Getter
    private String name;
    private Role role;
    private Employee supervisor;

}
