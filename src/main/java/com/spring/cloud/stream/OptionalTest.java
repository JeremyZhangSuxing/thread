package com.spring.cloud.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author zhang.suxing
 * @date 2020/6/14 21:32
 * <p>
 * 一个容器类 可以保存T 的值，也可以保存null 用来避免空指针异常
 * <p>
 * 常用的api见自己测试
 **/
public class OptionalTest {

    /**
     * of（T T） T  can not be null
     * ofNullable()
     * ofNullable(null)==empty(); 一个空的Optional实例
     */
    @Test
    public void test1() {
        Optional<Country> optional = Optional.of(new Country());
        System.out.println(optional);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_thenNull() {
        Optional<UserxData> emptyOpt = Optional.empty();
        System.out.println(emptyOpt.get());
    }

    /**
     * 从 Optional 实例中取回实际值对象的方法之一是使用 get() 方法：
     */
    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);
        Assert.assertEquals("John", opt.get());
    }

    @Test
    public void whenCheckIfPresent_thenOk() {
        UserxData user = new UserxData("john@gmail.com", "1234");
//        user = null;
        Optional<UserxData> opt = Optional.ofNullable(user);
        opt.ifPresent(u -> Assert.assertEquals(user.getEmail(), u.getEmail()));


        Assert.assertTrue(opt.isPresent());
        Assert.assertEquals(user.getEmail(), opt.get().getEmail());
    }

    /**
     * 这里你可以使用的第一个方法是 orElse()，它的工作方式非常直接，如果有值则返回该值，否则返回传递给它的参数值：
     */
    @Test
    public void whenEmptyValue_thenReturnDefault() {
        UserxData user = null;
        UserxData user2 = new UserxData("anna@gmail.com", "1234");
        UserxData result = Optional.ofNullable(user).orElse(user2);
        Assert.assertEquals(user2.getEmail(), result.getEmail());
    }

    /**
     * 如果对象的初始值不是 null，那么默认值会被忽略：
     */
    @Test
    public void whenValueNotNull_thenIgnoreDefault() {
        UserxData user = new UserxData("john@gmail.com", "1234");
        UserxData user2 = new UserxData("anna@gmail.com", "1234");
        UserxData result = Optional.ofNullable(user).orElse(user2);
//        UserxData result1 = Optional.ofNullable(user).orElseGet( () -> user2);

        Assert.assertEquals("john@gmail.com", result.getEmail());
    }

    /**
     * 这个示例中，两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。不过，orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
     * <p>
     * 在执行较密集的调用时，比如调用 Web 服务或数据查询，这个差异会对性能产生重大影响。
     */
    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        UserxData user = null;
//        logger.debug("Using orElse");
        UserxData result = Optional.ofNullable(user).orElse(createNewUser());
        System.out.println("Using orElseGet");
        UserxData result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    private UserxData createNewUser() {
        System.out.println("Creating New User");
        return new UserxData("extra@gmail.com", "1234");
    }

}
