package com.generator.zcgenerator.com.zc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

@Controller
@RequestMapping("")
public class TestController {

    @RequestMapping("")
    public String test() {
        return "index";
    }

    public static void main(String[] args) {
        pathTest();
    }

    public static void pathTest() {
        File file = new File("");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void JDBCTest() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "zc123");
            if (conn == null) {
                System.out.println("获取链接失败");
            }

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

            while (tables.next()) {
                String tabelName = tables.getString("TABLE_NAME");
                System.out.println(tabelName);
            }

            ResultSet city = metaData.getColumns(null, null, "city", null);
            while (city.next()) {
                System.out.println(city.getString("COLUMN_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
