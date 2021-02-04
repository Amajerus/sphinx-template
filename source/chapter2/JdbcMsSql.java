package com.sqlsamples;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcMsSql {

    public static void main(String[] args) {

        String connectionUrl = "jdbc:sqlserver://Servername:1434;databaseName=master;user=username;password=pass";

        try {
            // Load SQL Server JDBC driver and establish connection.
            System.out.print("Connecting to SQL Server ... ");
            try (Connection connection = DriverManager.getConnection(connectionUrl)) {
                System.out.println("Done.");
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }
}