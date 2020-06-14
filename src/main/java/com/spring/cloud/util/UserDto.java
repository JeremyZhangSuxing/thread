package com.spring.cloud.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang.suxing
 * @date 2020/4/8 13:52
 **/
@Data
public class UserDto {

    private String userName;

    private String pwd;

    private List<Student> students;


    public static void main(String[] args) {
        List<UserDto> userDtos = new ArrayList<>(2);
        for (int i = 0; i < 2; i++) {
            UserDto dto = new UserDto();
            dto.setPwd("11");
            dto.setUserName("jere" + i);
            List<Student> students = new ArrayList<>(2);
            Student student = new Student();
            student.setAge(1);
            student.setBillNo("111");
            student.setStu("bad");
            students.add(student);
            dto.setStudents(students);
            userDtos.add(dto);
        }
        System.out.println(JSON.toJSONString(userDtos));
    }
}
