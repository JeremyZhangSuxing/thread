package com.spring.cloud.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang.suxing
 * @date 2020/6/14 9:43
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer id;

    private String name;

    private Integer age;

    private Double salary;

    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
