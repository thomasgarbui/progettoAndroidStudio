package com.example.demo;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataManager {

    private static Connection connection;
    private GameManager gameManager;

    public DataManager(){
        gameManager = new GameManager();
        String url = "jdbc:mysql://127.0.0.1:3306/morracineseadvanced";
        String username = "root";
        String password = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        }catch(Exception ex){

        }
    }

    public User getUser(String username){
        User user = null;
        try{
            String getQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                user = new User(resultSet.getString("username"),resultSet.getString("password"),resultSet.getInt("elo"));
            }
        }catch(Exception ex) {
            ex.printStackTrace();

        }
        return user;
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
            preparedStatement.setInt(3, 1500);
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
            String getQuery = "SELECT * FROM friendRequest WHERE senderUsername = ? AND state = 'pending'";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setString(1,senderUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                friendRequestList.add(new FriendRequest(resultSet.getString("senderUsername"),resultSet.getString("receiverUsername"),resultSet.getString("status")));
            }
        }catch(Exception ex) {

        }
        return friendRequestList;
    }

    public List<FriendRequest> getFriends(String username){
        List<FriendRequest> friendList = new ArrayList<>();
        try{
            String getQuery = "SELECT * FROM friendsRequest WHERE senderUsername = ? OR receiverUsername = ? AND state = 'accepted'";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                friendList.add(new FriendRequest(resultSet.getString("senderUsername"),resultSet.getString("ReceiverUsername"),resultSet.getString("status")));
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
            String updateQuery = "UPDATE user SET elo = (elo+(?)) WHERE username = ?";
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
            if((playerNumber.equals("One")|| playerNumber.equals("Two")) && gameManager.isMoveValid(move)) {
                String updateQuery = "UPDATE matches SET player"+playerNumber+"Move = ? WHERE player"+playerNumber+"Username = ? AND winnerUsername IS NULL";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1,move);
                preparedStatement.setString(2,playerUsername);
                int rows = preparedStatement.executeUpdate();
                if(rows > 0){
                    result = true;
                    Match match = getLatestMatch(playerUsername);
                    if(match != null && (match.getPlayerOneMove() != null && match.getPlayerTwoMove() != null)){
                        Integer winner = gameManager.whoWon(match);
                        if(winner < 0){
                            setWinner("PAREGGIO","PAREGGIO",match.getId());
                        }else{
                            String winnerUsername;
                            String loserUsername;
                            Integer id = match.getId();
                            if(winner == 1){
                                winnerUsername = match.getPlayerOneUsername();
                                loserUsername = match.getPlayerTwoUsername();
                            }else{
                                winnerUsername = match.getPlayerTwoUsername();
                                loserUsername = match.getPlayerOneUsername();
                            }
                            setWinner(winnerUsername,loserUsername,id);
                        }
                    }
                }
            }
        }catch (Exception ex){

        }
        return result;
    }
    public Match getLatestMatch(String playerUsername){
        Match match = null;
        try{
            String getQuery = "SELECT * From matches WHERE playerOneUsername = ? OR playerTwoUsername = ? AND winnerUsername IS NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setString(1,playerUsername);
            preparedStatement.setString(2,playerUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                match = new Match(resultSet.getInt("id"),resultSet.getString("playerOneUsername"),resultSet.getString("playerTwoUsername"),resultSet.getString("playerOneMove"),resultSet.getString("playerTwoMove"),resultSet.getString("winnerUsername"));
            }
        }catch (Exception ex){

        }
        return match;
    }
    public Match getMatch(Integer id){
        Match match = null;
        try{
            String getQuery = "SELECT * FROM matches WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                match = new Match(resultSet.getInt("id"),resultSet.getString("playerOneUsername"),resultSet.getString("playerTwoUsername"),resultSet.getString("playerOneMove"),resultSet.getString("playerTwoMove"),resultSet.getString("winnerUsername"));
            }
        }catch(Exception ex){

        }
        return match;
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
    public boolean setWinner(String winnerUsername,String loserUsername,Integer id){
        boolean result = false;
        try{
            String updateQuery = "UPDATE matches SET winnerUsername = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1,winnerUsername);
            preparedStatement.setInt(2,id);
            int rows = preparedStatement.executeUpdate();
            if(rows > 0){
                result = true;
                Integer value = (getUser(winnerUsername).getElo()+getUser(loserUsername).getElo())/20;
                modifyElo(winnerUsername,value);
                modifyElo(loserUsername,value);
            }
        }catch (Exception ex){

        }
        return result;
    }

}
