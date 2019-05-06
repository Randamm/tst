package com.randomm.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSqlDB {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/gasis";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection = null;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        return connection;
    }
}
