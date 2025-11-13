package com.politecnicllevant.firstweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    private final String URL = "jdbc:mysql://db:3306/movies";
    private final String USER = "root";
    private final String PASSWORD = "root";


    public Connection connect() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex){
            throw new RuntimeException("No mysql driver found");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
