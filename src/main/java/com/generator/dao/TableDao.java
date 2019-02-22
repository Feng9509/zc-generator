package com.generator.dao;

import com.generator.entity.Table;

import java.util.List;

public interface TableDao {
    List<Table> select(String databasename);
}
