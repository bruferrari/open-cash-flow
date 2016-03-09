package com.ferrarib.opencf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by bruno on 3/7/16.
 */
public class ConnectionFactory {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/opencf_db", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
