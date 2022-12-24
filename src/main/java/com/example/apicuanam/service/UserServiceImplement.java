package com.example.apicuanam.service;

import com.example.apicuanam.entity.User;
import com.example.apicuanam.exception.NotFoundException;
import com.example.apicuanam.model.UserMapper;
import com.example.apicuanam.model.UserToCreate;
import com.example.apicuanam.model.UserToUpdate;
import com.example.apicuanam.model.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserServiceImplement implements UserService{
    private static ArrayList<User> users = new ArrayList<User>();
    /* static {
        users.add(new User(1,"Nam","nam@gmail.com","0975001","avatar1","123456"));
        users.add(new User(2,"Hung","hung@gmail.com","0975002","avatar2","123456"));
        users.add(new User(3,"Long","long@gmail.com","0975003","avatar3","123456"));
        users.add(new User(4,"Dat","dat@gmail.com","0975004","avatar4","123456"));
        System.out.println(users.get(0).getId());
    }*/


    @Override
    public List<UserDTO> getListUsers() throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost\\NAM30112002;database=project1;encrypt=false;user=nam;password=nam30112002";
        Connection conn = DriverManager.getConnection(dbURL);
        Statement statement = conn.createStatement();
        String SQL = "select * from [user]";
        ResultSet resultSet = statement.executeQuery(SQL);
        List<UserDTO> result = new ArrayList<UserDTO>();
        while (resultSet.next()) {
            User user = new User(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
            result.add(UserMapper.toUserDTO(user));
        }
        //System.out.println(result.get(1).getName());
        return result;
    }

    @Override
    public UserDTO getUserById(int id) throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost\\NAM30112002;database=project1;encrypt=false;user=nam;password=nam30112002";
        Connection conn = DriverManager.getConnection(dbURL);
        Statement statement = conn.createStatement();
        String SQL = "select * from [user]";
        ResultSet resultSet = statement.executeQuery(SQL);
        UserDTO result = new UserDTO();
        while (resultSet.next()){
            int idCheck = resultSet.getInt(1);
            if(idCheck==id){
                User user = new User(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                result = UserMapper.toUserDTO(user);
                break;
            }
        }
        return result;
    }


    @Override
    public List<UserDTO> searchUserByName(String name) throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost\\NAM30112002;database=project1;encrypt=false;user=nam;password=nam30112002";
        Connection conn = DriverManager.getConnection(dbURL);
        Statement statement = conn.createStatement();
        String SQL = "select * from [user]";
        ResultSet resultSet = statement.executeQuery(SQL);
        List<UserDTO> result = new ArrayList<UserDTO>();
        while (resultSet.next()){
            String nameCheck = resultSet.getString(2);
            if(Objects.equals(nameCheck, name)){
                User user = new User(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                var temp = UserMapper.toUserDTO(user);
                result.add(temp);
            }
        }

        if(result.isEmpty()){
            throw new NotFoundException("khong tim thay nguoi dung");
        }
        return result;
    }

    @Override
    public void deleteUserById(int id) throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost\\NAM30112002;database=project1;encrypt=false;user=nam;password=nam30112002";
        Connection conn = DriverManager.getConnection(dbURL);
        Statement statement = conn.createStatement();
        String SQL = "DELETE FROM [user] WHERE id = " + String.valueOf(id);
        //System.out.println(SQL);
        statement.executeUpdate(SQL);
    }

    @Override
    public void createUser(UserToCreate userToCreate) throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost\\NAM30112002;database=project1;encrypt=false;user=nam;password=nam30112002";
        Connection conn = DriverManager.getConnection(dbURL);
        Statement statement = conn.createStatement();
        String SQL = "select max(id) from [user]";
        ResultSet resultSet = statement.executeQuery(SQL);
        int maxId = 0;
        if(resultSet.next()){
            maxId = resultSet.getInt(1);
        }
        String SQL1 = "insert into [user] values (" + String.valueOf(maxId+1) + ",'" + userToCreate.getName() + "','"
                + userToCreate.getEmail() + "','" + userToCreate.getPhone() + "','" + userToCreate.getAvatar() + "','" +
                userToCreate.getPassword() + "');" ;
        //System.out.println(SQL1);
        statement.executeUpdate(SQL1);
    }

    @Override
    public void updateUser(String email, UserToUpdate userToUpdate) throws SQLException {
        String dbURL = "jdbc:sqlserver://localhost\\NAM30112002;database=project1;encrypt=false;user=nam;password=nam30112002";
        Connection conn = DriverManager.getConnection(dbURL);
        Statement statement = conn.createStatement();
        String sql = "UPDATE [user] SET name = '" + userToUpdate.getName() + "', phone = '" +
                userToUpdate.getPhone() + "', avatar = '" + userToUpdate.getAvatar() +
                "', password = '" + userToUpdate.getPassword() + "' where email = '" + email + "';";
        System.out.println(sql);
        statement.executeUpdate(sql);
    }
}
