package com.example.demo;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataManager {

    private static Connection connection;

    public DataManager(){
        String url = "jdbc:mysql://localhost:3306/morracineseadvanced";
        String username = "root";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        }catch(Exception ex){

        }
    }

    public Optional<User> getUser(String username){
        Optional optional = Optional.empty();
        try{
            String getQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.first()){
                optional = Optional.of(new User(resultSet.getString("username"),resultSet.getString("password"),resultSet.getInt("elo")));
            }
        }catch(Exception ex) {

        }
        return optional;
    }

    public List<User> getUsers(){
        List<User> userList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()){
                userList.add(new User(resultSet.getString("username"),"",resultSet.getInt("elo")));
            }
        }catch(Exception ex) {

        }
        return userList;
    }

    public boolean register(String username,String password) {
        boolean result = false;
        try {
            String insertQuery = "INSERT INTO users (username,password,elo) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 10);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                result = true;
            }
        } catch (Exception ex) {

        }
        return result;
    }

    public boolean newRequest(String senderUsername, String receiverUsername){
        boolean result = false;
        try{
            String insertQuery = "INSERT INTO friendrequest (senderUsername, receiverUsername) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,senderUsername);
            preparedStatement.setString(2,receiverUsername);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                result = true;
            }
        }catch (Exception ex){

        }
        return result;
    }

    public List<FriendRequest> getRequests(String senderUsername){
        List<FriendRequest> friendRequestList = new ArrayList<>();
        try{
            String getQuery = "SELECT * FROM friendrequest WHERE senderUsername = ? AND state = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setString(1,senderUsername);
            preparedStatement.setString(1,"pending");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                friendRequestList.add(new FriendRequest(resultSet.getInt("id"),resultSet.getString("senderUsername"),resultSet.getString("receiverUsername"),resultSet.getString("status")));
            }
        }catch(Exception ex) {

        }
        return friendRequestList;
    }

    public List<Friend> getFriends(String username){
        List<Friend> friendList = new ArrayList<>();
        try{
            String getQuery = "SELECT * FROM friends WHERE friendOneUsername = ? OR friendTwoUsername = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                friendList.add(new Friend(resultSet.getString("friendOneUsername"),resultSet.getString("friendTwoUsername")));
            }
        }catch(Exception ex) {

        }
        return friendList;
    }

    public boolean modifyRequest(String senderUsername,String receiverUsername,String state){
        boolean result = false;
        try{
            String updateQuery = "UPDATE friendRequest SET state = ? WHERE senderUsername = ? AND receiverUsername = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1,state);
            preparedStatement.setString(2,senderUsername);
            preparedStatement.setString(3,receiverUsername);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                result = true;
            }
        }catch (Exception ex){

        }
        return result;
    }

    public boolean modifyElo(String username,Integer value){
        boolean result = false;
        try{
            String updateQuery = "UPDATE user SET elo = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1,value);
            preparedStatement.setString(2,username);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                result = true;
            }
        }catch (Exception ex){

        }
        return result;
    }

    public boolean newMatch(String playerOneUsername){
        boolean result = false;
        try{
            String insertQuery = "INSERT INTO matches(playerOneUsername) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1,playerOneUsername);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                result = true;
            }
        }catch(Exception ex){

        }
        return result;
    }
    public boolean selectMove(String playerUsername,String move,String playerNumber){
        boolean result = false;
        try{
            String updateQuery = "UPDATE matches SET ? = ? WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1,"player"+playerNumber+"Move");
            preparedStatement.setString(2,move);
            preparedStatement.setString(3,playerUsername);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                result = true;
            }
        }catch (Exception ex){

        }
        return result;
    }

    public boolean joinMatch(String playerUsername,Integer id){
        boolean result = false;
        try{
            String updateQuery = "UPDATE matches SET playerTwoUsername = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1,playerUsername);
            preparedStatement.setInt(2,id);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                result = true;
            }
        }catch (Exception ex){

        }
        return result;
    }
    public List<Match> getMatches(String playerUsername){
        List<Match> matchList = new ArrayList<>();
        try{
            String getQuery = "SELECT * FROM matches WHERE playerOneUsername = ? OR playerTwoUsername = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setString(1,playerUsername);
            preparedStatement.setString(2,playerUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                matchList.add(new Match(resultSet.getInt("id"),resultSet.getString("playerOneUsername"),resultSet.getString("playerTwoUsername"),resultSet.getString("playerOneMove"),resultSet.getString("playerTwoMove"),resultSet.getString("winnerUsername")));
            }
        }catch(Exception ex) {

        }
        return matchList;
    }
    public boolean deleteMatch(Integer id){
        boolean result = false;
        try{
            String deleteQuery = "DELETE FROM matches WHERE id = ? AND winnerUsername IS NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1,id);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                result = true;
            }
        }catch (Exception ex){

        }
        return result;
    }
    public boolean setWinner(String winnerUsername,Integer id){
        boolean result = false;
        try{
            String updateQuery = "UPDATE matches SET winnerUsername = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1,winnerUsername);
            preparedStatement.setInt(2,id);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                result = true;
            }
        }catch (Exception ex){

        }
        return result;
    }

}
