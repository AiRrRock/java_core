package ru.geekbrains.march.chat.server;

import java.sql.*;

public class SqlliteAuthenticationProvider implements AuthenticationProvider {
    private Connection connection;
    private PreparedStatement psInsertUser;
    private PreparedStatement psFindByLogin;
    private PreparedStatement psUpdateNickname;
    private PreparedStatement psFindByNickname;


    public SqlliteAuthenticationProvider() {
        connect();
        prepareStatements();
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        User u = getUserByLogin(login);
        if (u != null && u.getLogin().equals(login) && u.getPassword().equals(password)) {
            return u.getNickName();
        }
        return null;
    }

    @Override
    public void changeNickname(String oldNickname, String newNickname) {
        User u = getUserByNickName(oldNickname);
        if (u != null) {
            try {
                psUpdateNickname.setString(1, newNickname);
                psUpdateNickname.setString(2, u.getLogin());
                psUpdateNickname.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean signUpUser(String login, String password, String nickname) {
        try {
            psInsertUser.setString(1, login);
            psInsertUser.setString(2, password);
            psInsertUser.setString(3, nickname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to DB");
        }
    }

    private void prepareStatements() {
        try {
            psInsertUser = connection.prepareStatement("insert into users (login, password, nickname) values(?, ?, ?)");
            psFindByLogin = connection.prepareStatement("select * from users where login=?");
            psFindByNickname = connection.prepareStatement("select * from users where nickname=?");
            psUpdateNickname = connection.prepareStatement("update users set nickname=? where login=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean doesLoginExist(String login) {
        return getUserByLogin(login) != null;
    }

    private User getUserByLogin(String login) {
        try {
            psFindByLogin.setString(1, login);
            ResultSet resultSet = psFindByLogin.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private User getUserByNickName(String login) {
        try {
            psFindByNickname.setString(1, login);
            ResultSet resultSet = psFindByLogin.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
