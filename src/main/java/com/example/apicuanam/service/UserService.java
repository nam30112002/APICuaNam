package com.example.apicuanam.service;

import com.example.apicuanam.entity.User;
import com.example.apicuanam.model.UserToCreate;
import com.example.apicuanam.model.UserToUpdate;
import com.example.apicuanam.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface UserService {
    public List<UserDTO> getListUsers() throws SQLException;
    public UserDTO getUserById(int id) throws SQLException;
    public List<UserDTO> searchUserByName(String name) throws SQLException;
    public void deleteUserById(int id) throws SQLException;
    public void createUser(UserToCreate userToCreate) throws SQLException;
    public void updateUser(String email, UserToUpdate userToUpdate) throws SQLException;
}
