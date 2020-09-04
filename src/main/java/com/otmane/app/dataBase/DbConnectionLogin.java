package com.otmane.app.dataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;


public class DbConnectionLogin {
    public static String query;

    public static int tryConnect(String user ,String pass,String Gender ) throws SQLException{
        try {
            String username="root";
            String password ="";
            String url ="jdbc:mysql://localhost:3308/quiz_app_db";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            if(Gender == "professeur" ){
                 query = "SELECT * FROM user_professeur WHERE username='"+user+"' AND password ='"+pass+"'";
            }
            else if(Gender == "étudiant")
            {
                 query = "SELECT * FROM user_student WHERE username='"+user+"' AND password ='"+pass+"'";
            }
            ResultSet result = statement.executeQuery(query);
            if(connection == null)
            {
                return -1;
            }
            while(result.next()){
                return 0;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(" not connected");
        }catch(SQLSyntaxErrorException e)
        {
            return -1;
        }
        return 1;
    }
}

