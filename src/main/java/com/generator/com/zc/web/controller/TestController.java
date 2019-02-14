package com.generator.com.zc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class TestController {

    @RequestMapping("")
    public String test() {
        return "index";
    }
}
