package com.spring.cloud.reflect;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * @author zhang.suxing
 * @date 2020/6/20 13:23
 * <p>
 * 反射和使用的场景：
 * 动态性，开始要编译的时候不知道要create的对象
 * <p>
 * 【class interface [] enum annotation primitive type void】
 * 数组 类型 欸和维度一样就是同一个class
 * 加载到内存中的运行时类，会缓存一段时间，在此时间内，我们可以通过不同的方式来获取此运行时的类
 * <p>
 * 获取类的方式三种
 * <p>
 * 反射机制与封装性是否矛盾？
 * 直接new object()/reflect
 * <p>
 * 1.class加载过程 程序经过javac.exe 命令后，会生成一个或者多个字节码文件（.class结尾），接着我们使用java.exe命令对某个字节码文件解释运行。相当于将某个字节码文件加载到内存中---类的加载
 * 加载到内存中的类，我们就称为 【运行时类】，此运行时类就作为class的一个instance.
 * 2.Class 的实例对应着一个运行时类
 * 3.加载到内存中的类，会缓存一定时间，在此时间内，可通过不同的方式获取运行时类
 * <p>
 * classLoader
 * 作用：将class文件字节码内容加载到内存中，并将这些静态数据转换成方法区的运行数据结构，然后在堆中生成一个代表java.lang.Class的对象，作为方法区中类数据的访问入口
 * 类缓存：一旦某个类被加载到类加载器中，他将维持加载一段时间。但是jvm gc 会回收这些 Class对象
 **/
public class ReflectTest {
    @Test
    public void test1() {
        Person person = new Person();
        person.age = 10;
        System.out.println(person.toString());
        person.show();
    }

    @Test
    public void test2() throws Exception {
        Class<Person> clazz = Person.class;
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        Person person = constructor.newInstance("jeremy", 10);
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.setInt(person, 199);
        System.out.println(person);
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(person);
    }

    /**
     * 获取class方式
     * 获取到的是同一个class对象
     */
    @Test
    public void test3() throws ClassNotFoundException {
        //1.运行时类的属性 .class
        Class<Person> personClass = Person.class;
        System.out.println(personClass);
        //2.通过运行时类的对象
        Person person = new Person();
        Class<? extends Person> aClass = person.getClass();
        System.out.println(aClass);
        //3.通过Class静态方法的: forName()
        Class<?> aClass1 = Class.forName("com.spring.cloud.reflect.Person");
        System.out.println(aClass1);
        //4.classLoader
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        Class<?> loadClass = classLoader.loadClass("com.spring.cloud.reflect.Person");
        System.out.println(personClass == loadClass);
    }

    /**
     * 使用类加载器读取配置文件
     */
    @Test
    public void test4() throws Exception {
        //method1:此时文件默认在当前的module下
        //读取配置文件的方式
        FileInputStream fileInputStream = new FileInputStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);
        System.out.println("method1****" + "user:" + properties.get("user") + "****" + "password:" + properties.get("password"));

        //method2 类加载器获取  相对路径在src目录下
        Properties loadProperties = new Properties();
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream asStream = classLoader.getResourceAsStream("jdbc.properties");
        loadProperties.load(asStream);
        System.out.println("method2****" + "user:" + loadProperties.get("user") + "****" + "password:" + loadProperties.get("password"));
    }

    /**
     * 类加载器的种类
     */
    @Test
    public void test5() {
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        System.out.println("自定义类使用系统类加载器加载" + classLoader);

        ClassLoader classLoaderParent = classLoader.getParent();
        System.out.println("系统类加载器加载的parent 扩展类加载器" + classLoaderParent);

        ClassLoader classLoaderParentParent = classLoaderParent.getParent();
        System.out.println("扩展类加载器的parent 引导类加载器 主要负责加载java核心类库，无法加载自定义类" + classLoaderParentParent);
    }

    /**
     * newInstance(); 调用此方法，创建对应的运行时类的对象，内部调用了运行时类的空参构造方法
     * 1.运行时类必须提供空参构造器
     * 2.空参的构造器的访问权限得够 normal set public
     * <p>
     * java bean 中要求提供一个public的空参构造器
     * reason
     * 1.便于通过reflect创建运行时类的对象
     * 2.便于子类extends此运行类时，默认调用的super()时，保证父类有此构造器
     * <p>
     * <p>
     * 反射的动态性
     */
    @Test
    public void test() {
        String classPath;
        for (int i = 0; i < 20; i++) {
            int nextInt = new Random().nextInt(3);
            System.out.println(nextInt);
            switch (nextInt) {
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "com.spring.cloud.reflect.Person";
                    break;
                default:
                    classPath = "";
                    break;
            }
            try {
                Object instance = createInstance(classPath);
                System.out.println(instance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 根据名称创建一个实例
     *
     * @param classPath 该类的相对路径
     * @return 该类的示例
     */
    private Object createInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }

    @Test
    public void testList1() throws Exception {
        ReflectTest.testList(Arrays.asList(new Person("jeremy", 10)));
    }

    public static <T> void testList(List<T> list) throws Exception {
        for (T t : list) {
            Class<?> aClass = t.getClass();
            Field field = t.getClass().getDeclaredField("name");

            //打开私有访问
            field.setAccessible(true);
            //获取属性
            String name = field.getName();
            //获取属性值
            Object value = field.get(t);
            field.set(t, "suxing");
            System.out.println("-------------" + field.get(t));
        }
    }

    @SafeVarargs
    public static <T> void testMapping(T... object) {

    }


}
