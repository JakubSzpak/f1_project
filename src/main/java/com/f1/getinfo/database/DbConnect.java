package com.f1.getinfo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "admin";

    public static Connection connect() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
