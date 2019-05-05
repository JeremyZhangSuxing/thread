/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author suxing.zhang
 * @since 2019/5/2
 */
@Slf4j
public class Test {
    private static final String SYNC_HK_SWITCH = "accounting.sync.switch";

    @Autowired
    private RedisTemplate redisTemplate;

    class FlightTicketInfo {

        private String orderNumber;

        private String userName;

        private String age;

        public FlightTicketInfo(String orderNumber, String userName, String age) {
            this.orderNumber = orderNumber;
            this.userName = userName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "FlightTicketInfo{" +
                    "orderNumber='" + orderNumber + '\'' +
                    ", userName='" + userName + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public String getUserName() {
            return userName;
        }

        public String getAge() {
            return age;
        }
    }

    /**
     * 去重
     *
     * @author suxing.zhang
     * 2019/5/5
     */
    @org.junit.Test
    public void testList() {
        List<FlightTicketInfo> infoList = new ArrayList<>();
        infoList.add(new FlightTicketInfo("22222", "xiaoming", "22"));
        infoList.add(new FlightTicketInfo("22222", "xiaoming", "22"));
        infoList.add(new FlightTicketInfo("33333", "xiaoming", "23"));
        infoList.add(new FlightTicketInfo("11111", "xiaoming", "22"));
        infoList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(f -> f.getUserName() + f.getOrderNumber()))), ArrayList::new))
                .forEach(System.out::println);
    }


    @org.junit.Test
    public void testRedis() {

    }

}
