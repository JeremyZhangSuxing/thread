package com.spring.cloud.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhang.suxing
 * @date 2020/6/20 10:00
 **/
public class TestAnnotation {
    public static void main(String[] args) {
        try {
            Class student = Class.forName("com.spring.cloud.annotation.Student");
            Method studyMethod = student.getDeclaredMethod("study", int.class);
            studyMethod.setAccessible(true);
            if (!studyMethod.isAnnotationPresent(MethodAnnotation.class)) {
                System.out.println("this method which is not configured by annotation MethodAnnotation");
            }
            MethodAnnotation methodAnnotation = studyMethod.getAnnotation(MethodAnnotation.class);
            System.out.println(methodAnnotation.age() + "---" + Arrays.toString(methodAnnotation.array()) + "---" + methodAnnotation.name());
            studyMethod.invoke(student.newInstance(), 10);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
