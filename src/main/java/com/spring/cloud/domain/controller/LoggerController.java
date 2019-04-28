/*
 * All rights Reserved, Designed By jere
 *
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.spring.cloud.domain.controller;

import com.github.pagehelper.PageInfo;
import com.spring.cloud.application.ThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author suxing.zhang
 * @since 2019/4/26
 */
@RestController
@RequestMapping("/log")
@Api(value = "SayController|一个用来测试swagger注解的控制器")
public class LoggerController {
    private final ThreadService threadService;

    @Autowired
    public LoggerController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/list")
    @ApiOperation("测试")
    public ResponseEntity<List<String>> getMsgList(){
        return ResponseEntity.ok().body(threadService.getList());
    }

    @GetMapping("/page")
    @ApiOperation("page")
    public ResponseEntity<PageInfo<String>> getMsgPage(@RequestParam Integer start, @RequestParam Integer pageSize ){
        return ResponseEntity.ok().body(threadService.getLogsPage(start,pageSize));
    }
}
