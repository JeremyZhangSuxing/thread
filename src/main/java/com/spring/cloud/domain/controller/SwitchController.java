/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.spring.cloud.domain.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
