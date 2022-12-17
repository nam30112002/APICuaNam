package com.example.apicuanam.service;

import com.example.apicuanam.entity.User;
import com.example.apicuanam.model.UserToCreate;
import com.example.apicuanam.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDTO> getListUsers();
    public UserDTO getUserById(int id);
    public List<UserDTO> searchUserByName(String name);
    public void deleteUserById(int id);
    public void creatUser(UserToCreate userToCreate);
    public void updateUser(String email, UserToCreate userToCreate);
}
