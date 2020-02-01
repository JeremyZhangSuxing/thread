/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.spring.cloud.domain.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author suxing.zhang
 * @since 2019/5/5
 */
@Slf4j
@RequestMapping("/switch")
@Api(tags = {"开关控制"})
@RestController
public class SwitchController {

    private final RedisTemplate redisTemplate;
    private static final String SYNC_HK_SWITCH = "jere.test.switch";

    public SwitchController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/turn")
    public String turn(@RequestParam String switchOperation) {
        if ("on".equals(switchOperation)) {
            redisTemplate.opsForValue().set(SYNC_HK_SWITCH, "on");
        } else if ("off".equals(switchOperation)) {
            redisTemplate.delete(SYNC_HK_SWITCH);
        }
        return switchOperation + "----操作成功";
    }

    @RequestMapping("/count")
    public ResponseEntity getDate(@RequestParam Double saleDate, @RequestParam Double returnDate,
                                  @RequestParam Double saleDiscount, @RequestParam Double returnDiscount) {
        Map<String, Double> map = new HashMap<>(4);
        Double saleResult = BigDecimal.valueOf(saleDate).subtract(BigDecimal.valueOf(saleDiscount)).doubleValue();
        Double returnResult = BigDecimal.valueOf(returnDate).subtract(BigDecimal.valueOf(returnDiscount)).doubleValue();
        Double countResult = BigDecimal.valueOf(saleResult).subtract(BigDecimal.valueOf(returnResult)).doubleValue();
        map.put("销售统计", saleResult);
        map.put("客退统计", returnResult);
        map.put("本月pos结果", countResult);
        return ResponseEntity.ok(map);
    }

//    public static void main(String[] args) {
//        String orderNo = "";
//        Map<String, String> map = new HashMap<>(3);
//        Long dateSourceNo = Crc32.get(orderNo) % 2;
//        Long tableNo = (Crc32.get(orderNo) / 2) % 2;
//        Long detailTableNo = (Crc32.get(orderNo) / 2) % 8;
//        map.put("数据库号", dateSourceNo.toString());
//        map.put("非明细表号", tableNo.toString());
//        map.put("明细表标号", detailTableNo.toString());
//        System.out.println(map);
//    }
}
