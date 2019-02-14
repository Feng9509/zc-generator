package com.generator.utils;

import com.generator.com.zc.entity.Table;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtil {
    private static Properties properties = null;

    public static String DRIVER = "";
    public static String URL = "";
    public static String USERNAME = "";
    public static String PASSWORD = "";
    public static String DATABASENAME = "";

    static {
        try {
            InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties = new Properties();
            properties.load(inputStream);

            DRIVER = properties.getProperty("jdbc.driver");
            URL = properties.getProperty("jdbc.url");
            USERNAME = properties.getProperty("jdbc.username");
            PASSWORD = properties.getProperty("jdbc.password");
            DATABASENAME = properties.getProperty("jdbc.databasename");

            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static List<Table> getTables() {
        List<Table> result = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT TABLE_NAME, CREATE_TIME, UPDATE_TIME, TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?");
            preparedStatement.setString(1, DATABASENAME);
            ResultSet resultSet = preparedStatement.executeQuery();
            Table table = null;
            result = new ArrayList<Table>();
            while (resultSet.next()) {
                String tablename = resultSet.getString("TABLE_NAME");
                String createtime = resultSet.getString("CREATE_TIME");
                String updatetime = resultSet.getString("UPDATE_TIME");
                String commnet = resultSet.getString("TABLE_COMMENT");

                table = new Table();
                table.setName(tablename);
                table.setCreatetime(createtime);
                table.setUpdatetime(updatetime);
                table.setComment(commnet);

                result.add(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
