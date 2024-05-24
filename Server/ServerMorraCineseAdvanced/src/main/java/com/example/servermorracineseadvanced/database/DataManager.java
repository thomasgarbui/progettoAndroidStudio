package com.example.servermorracineseadvanced.database;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.concurrent.ExecutionException;

@Service
public class DataManager {

    private static Connection connection;

    public DataManager(){
        String url = "jdbc:mysql://localhost:3306/morracineseadvanced";
        String username = "root";
        String password = "password";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            connection.close();
        }catch(Exception ex){

        }
    }
}
