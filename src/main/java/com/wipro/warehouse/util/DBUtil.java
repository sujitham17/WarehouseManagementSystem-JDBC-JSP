package com.wipro.warehouse.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getDBConnection() {
        Connection con = null;

        try {
            // Load Oracle Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Create Connection
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "SYSTEM",
                    "System123"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
