package com.generator.com.zc.web.controller;

import com.generator.com.zc.entity.Table;
import com.generator.com.zc.dao.TableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sys/table")
public class TableController {
    @Autowired
    private TableDao tableDao;

    @RequestMapping("/list.htm")
    public String listTables() {
        return "table_list";
    }

    @ResponseBody
    @RequestMapping("/gettables.json")
    public List<Table> getTables() {
        return tableDao.select();
    }
}
