/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.spring.cloud.domain.controller;

import com.github.pagehelper.PageInfo;
import com.spring.cloud.application.CacheService;
import com.spring.cloud.application.ThreadService;
import com.spring.cloud.domain.entity.UserInfo;
import com.spring.cloud.domain.entity.UserInfoExample;
import com.spring.cloud.interfaces.mapper.UserInfoMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author suxing.zhang
 * @since 2019/4/26
 */
@RestController
@RequestMapping("/log")
@Api(tags = {"SayController|一个用来测试swagger注解的控制器"})
@Slf4j
public class LoggerController {
    private final ThreadService threadService;
    private final CacheService cacheService;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    public LoggerController(ThreadService threadService, CacheService cacheService) {
        this.threadService = threadService;
        this.cacheService = cacheService;
    }

    @GetMapping("/list")
    @ApiOperation("测试")
    public ResponseEntity<List<String>> getMsgList() {
        return ResponseEntity.ok().body(threadService.getList());
    }

    @GetMapping("/page")
    @ApiOperation("page")
    public ResponseEntity<PageInfo<String>> getMsgPage(@RequestParam Integer start, @RequestParam Integer pageSize) {
        return ResponseEntity.ok().body(threadService.getLogsPage(start, pageSize));
    }

    @ApiOperation("批量插入")
    @PostMapping("/batch")
    public void insertBatch() {
        log.debug("测试批量插入");
        threadService.testInsertBatch();
    }

    @GetMapping("/log/{id}")
    public ResponseEntity<UserInfo> getLogById(@PathVariable Long id) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andIdEqualTo(id);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(userInfoMapper.selectByExample(example).get(0));
    }

    @GetMapping("/test")
    public ResponseEntity<UserInfo> getById() {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andIdEqualTo(88L);
        return ResponseEntity.status(200).body(userInfoMapper.selectByExample(example).get(0));

    }
}
