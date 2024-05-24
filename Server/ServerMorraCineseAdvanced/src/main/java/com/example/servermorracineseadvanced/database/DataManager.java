package com.example.servermorracineseadvanced.database;
import com.example.servermorracineseadvanced.api.model.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.*;
import java.util.Optional;
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

    public Optional<User> getUser(String username){
        Optional optional = Optional.empty();
        try{
            String insertQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.first()){
                optional = Optional.of(new User(resultSet.getString("username"),resultSet.getString("password"),resultSet.getInt("elo")));
            }
        }catch(Exception ex) {

        }
        return optional;
    }
}
