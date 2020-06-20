package com.spring.cloud.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhang.suxing
 * @date 2020/6/14 11:03
 **/
public class StreamApiTest1 {

    /**
     * 1-筛选与切片
     */
    @Test
    public void test1() {
        List<Employee> list = EmployeeData.getEmployee();
//       filter(Predicate p)- 接收lambda，从流中排查某些元素
        list.stream().filter(v -> v.getSalary() > 5000).forEach(System.out::println);
        System.out.println("====================================================");

//       limit(n)截断流，使其元素不超过给定数量
        list.stream().limit(1).forEach(v -> {
            System.out.print(v.getName() + "\t");
            System.out.println(v.getSalary());
        });
        System.out.println("====================================================");

//       skip(n) 跳过元素，返回一个仍掉了前n个元素的流。若流中元素不足n个，则返回一个空的stream
        list.stream().skip(1).forEach(v -> System.out.print(v.getName() + "\t" + v.getSalary()));
        System.out.println("====================================================");

//      distinct() 筛选，通过stream产生的元素的hashcode() 和 equals() 去除重复元素
        list.stream().distinct().forEach(v -> System.out.print(v.getName() + "\t" + v.getSalary() + "\n"));
    }

    /**
     * 2-映射
     */
    @Test
    public void test2() {
        //mao(Function f) ---  接收一个函数作为参数，将元素转化为其他形式货提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素
        List<String> stringList = Arrays.asList("aa", "bb", "cc");
        stringList.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("====================================================");

        //flatMap(Function f) 接收一个函数作为参数，江stream中的每个值都换成另一个stream，然后把所有的stream连接成一个stream
        stringList.stream().flatMap(StreamApiTest1::fromStringToStream).forEach(System.out::println);
    }

    /**
     * string--->stream
     */
    private static Stream<Character> fromStringToStream(String string) {
        ArrayList<Character> characters = new ArrayList<>();
        for (Character character : string.toCharArray()) {
            characters.add(character);
        }
        return characters.stream();
    }

    /**
     * 3-排序
     */
    @Test
    public void test3() {
        //sorted()自然排序
        List<Integer> integers = Arrays.asList(12, 43, -9, 87, 56, 2);
        integers.stream().sorted().forEach(System.out::println);

        //sorted()定制排序
        List<Employee> employees = EmployeeData.getEmployee();
        //easy
        employees.stream().sorted(Comparator.comparingInt(Employee::getAge)).forEach(System.out::println);
        //complex
        employees.stream().sorted((e1, e2) -> {
            int compare = Integer.compare(e1.getAge(), e2.getAge());
            if (compare != 0) {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
            return compare;

        }).forEach(System.out::println);

    }
}
