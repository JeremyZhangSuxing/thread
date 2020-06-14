package com.spring.cloud.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang.suxing
 * @date 2020/6/14 10:08
 **/
public class EmployeeData {

    public static List<Employee> getEmployee() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1001, "马化腾", 34, 999999.99));
        list.add(new Employee(1002, "马云", 84, 999999.99));
        list.add(new Employee(1003, "刘强东", 54, 999999.99));
        list.add(new Employee(1004, "雷军", 234, 999999.99));
        list.add(new Employee(1005, "李彦宏", 324, 999999.99));
        list.add(new Employee(1006, "比尔盖茨", 54, 999999.99));
        list.add(new Employee(1007, "任正非", 323, 999999.99));
        list.add(new Employee(1008, "扎克伯格", 13, 999999.99));
        list.add(new Employee(1001, "马化腾", 34, 999999.99));
        return list;
    }
}
