/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * @author suxing.zhang
 * @since 2019/5/2
 */
@Slf4j
public class Test1 {
    private static final String SYNC_HK_SWITCH;

    static {
        SYNC_HK_SWITCH = "accounting.sync.switch";
    }

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
    @Test
    public void testList() {
        List<FlightTicketInfo> infoList = new ArrayList<>();
        infoList.add(new FlightTicketInfo("22222", "xiaoming", "22"));
        infoList.add(new FlightTicketInfo("22222", "xiaoming", "22"));
        infoList.add(new FlightTicketInfo("33333", "xiaoming", "23"));
        infoList.add(new FlightTicketInfo("11111", "xiaoming", "22"));
        /**
         // TreeSetSet的一个实现，默认实现排序；故TreeSet的泛型类型必须是Comparable或者Comparator。TreeSet基于TreeMap实现。
         //infoList.stream()
         //        .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(f -> f.getUserName() + f.getOrderNumber()))), ArrayList::new))
         //        .forEach(System.out::println);

         Map<String, List<FlightTicketInfo>> map = infoList.stream().collect(Collectors.groupingBy(t -> t.getOrderNumber()));
         System.out.println("按年级分组"+map);
         /*然后再对map处理，这样就方便取出自己要的数据
         for(Map.Entry<String, List<FlightTicketInfo>> entry : map.entrySet()){
         System.out.println("key:"+entry.getKey());
         System.out.println("value:"+entry.getValue());
         }
         **/
    }


    @org.junit.Test
    public void testRedis() {
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int num = random.nextInt(10);
            System.out.println(num);
            sb.append(str.charAt(num));
            System.out.println(sb);
            str = str.replace((str.charAt(num) + ""), "");
            System.out.println(str);
        }
    }

}
