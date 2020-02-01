package com.spring.cloud.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhang.suxing
 * @date 2019/11/22 10:31
 **/
@Controller
@RequestMapping("/template")
public class PageController {

    @RequestMapping("index")
    public String test() {
        return "index";
    }
}
