package com.otmane.app.dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    private static Connection con;

    public MysqlConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/quiz_app_db", "root", "");
            System.out.println("liaison avec data base faite");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error msg: " + ex.getMessage());
            con = null;
        }
    }
    public Connection getConnection() {
        return con;
    }
}
