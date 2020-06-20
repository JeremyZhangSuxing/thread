package com.spring.cloud.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhang.suxing
 * @date 2020/6/14 14:26
 **/
public class StreamApiTest2 {
    /**
     * stream  的终止操作
     */
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployee();
        //allMatch
        System.out.println(employees.stream().allMatch(v -> v.getAge() > 18));

        //anyMatch
        System.out.println(employees.stream().anyMatch(v -> v.getAge() > 19));

        //noneMatch
        System.out.println(employees.stream().noneMatch(v -> v.getAge() > 18));

        //findFirst
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);

        //findAny
        Optional<Employee> any = employees.stream().findAny();
        System.out.println(any);

        //count 返回stream中总数量 steam操作之后统计元素个数
        long count = employees.stream().count();
        System.out.println(count);

        //max
        Optional<Double> max = employees.stream().map(Employee::getSalary).max(Double::compareTo);
        System.out.println(max);

        //min
        Optional<Integer> min = employees.stream().map(Employee::getId).min(Integer::compareTo);
        System.out.println(min);

        //forEach(Consumer c) 内部迭代
        employees.stream().forEach(System.out::println);
        //外部拿到集合的指针 进行迭代
        employees.forEach(System.out::println);
    }

    /**
     * 2-规约
     */
    @Test
    public void test2() {
        //reduce(T identity,BinaryOperator)---可以将stream中的元素反复结合起来，得到一个值。返回 T
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = integers.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        System.out.println("================================================================");
        //reduce(BinaryOperator)---可以将stream中的元素反复结合起来，得到一个值。返回Optional<T>
        List<Employee> employees = EmployeeData.getEmployee();
        Optional<Double> optionalADouble = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(optionalADouble);
    }

    @Test
    public void test3() {
//        collect(Collector c) -- 将stream转换为其他形式。接收一个Collector接口实现，用于给Stream元素做汇总的方法
//        Collectors 实用类提供了很多 static 方法，可u以方便的创建收集器实例
        //TEST 1  list
        List<Employee> employeeList = EmployeeData.getEmployee();
        List<Employee> collect = employeeList.stream().filter(v -> v.getSalary() > 5000).collect(Collectors.toList());
        collect.forEach(System.out::println);
        //TEST 2  set
        Set<Employee> employeeSet = employeeList.stream().filter(v -> v.getSalary() > 5000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
    }
}
