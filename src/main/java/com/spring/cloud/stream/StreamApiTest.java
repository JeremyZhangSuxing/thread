package com.spring.cloud.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhang.suxing
 * @date 2020/6/14 9:43
 * <p>
 * 1)
 * stream与collection 的区别 stream是有关计算的，面象cpu；collection 是一种静态的内存数据结构，面向内存
 * <p>
 * 2)
 * 1.stream 不会自己存储元素
 * 2.stream 不会改变源对象。相反，他们会返回一个持有结果的新的stream
 * 3.stream 操作是延迟执行的。意味着他们会等到需要结果的时候才执行
 * <p>
 * 3)
 * stream的执行流程
 * stream实例化--->一系列中间操作（过滤，映射，。。。）--->终止操作
 * <p>
 * 4)说明：
 * 1.一个中间操作链，对数据源做处理，
 * 2.一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用。
 **/
public class StreamApiTest {
    /**
     * 集合创建stream
     */
    @Test
    public void test1() {
        //获取stream的方法再collection接口中，by实现类去调用
        List<Employee> employees = EmployeeData.getEmployee();
        Stream<Employee> stream = employees.stream();
        //获取并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }

    /**
     * 数组创建stream
     */
    @Test
    public void test2() {
        //包装类型
        int[] arr = new int[]{1, 2, 3, 4, 5, 6,};
        IntStream intStream = Arrays.stream(arr);
        //一般类型
        Employee e1 = new Employee(2001, "TOM");
        Employee e2 = new Employee(2002, "JERRY");
        Employee[] employees = new Employee[]{e1, e2};
        Stream<Employee> stream = Arrays.stream(employees);
    }

    /**
     * stream of 方法创建
     */
    @Test
    public void test3() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    /**
     * 创建无限流 用于生成数据
     */
    @Test
    public void test4() {
        //迭代
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        //生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
