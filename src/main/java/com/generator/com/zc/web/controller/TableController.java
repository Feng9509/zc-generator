package com.generator.com.zc.web.controller;

import com.generator.com.zc.dao.TableDao;
import com.generator.com.zc.entity.Page;
import com.generator.com.zc.entity.Table;
import com.github.pagehelper.PageHelper;
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
    @RequestMapping("listpage.json")
    public Page<Table> listPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        com.github.pagehelper.Page tables = (com.github.pagehelper.Page) tableDao.select();
        return new Page<Table>(tables.getTotal(), tables);
    }

    @RequestMapping("edit.htm")
    public String edit() {
        return "table_form";
    }
}
