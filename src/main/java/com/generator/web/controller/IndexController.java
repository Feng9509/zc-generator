package com.generator.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @descrption 跳转首页
 * @Date 2019/2/19
 * @Auther zhangchao
 */
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping("")
    public String test() {
        return "index";
    }
}
