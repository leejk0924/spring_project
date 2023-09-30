package com.example.project;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
    Object map(ResultSet resultSet) throws SQLException;
}
