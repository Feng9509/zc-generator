package com.generator.zcgenerator.com.zc.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/sys/tables")
public class TableController {

    @RequestMapping("/listtables")
    @ResponseBody
    public List<String> listTables() {

        return null;
    }
}
