package com.example.apicuanam.service;

import com.example.apicuanam.entity.User;
import com.example.apicuanam.exception.NotFoundException;
import com.example.apicuanam.model.UserMapper;
import com.example.apicuanam.model.UserToCreate;
import com.example.apicuanam.model.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserServiceImplement implements UserService{
    private static ArrayList<User> users = new ArrayList<User>();
    static {
        users.add(new User(1,"Nam","nam@gmail.com","0975001","avatar1","123456"));
        users.add(new User(2,"Hung","hung@gmail.com","0975002","avatar2","123456"));
        users.add(new User(3,"Long","long@gmail.com","0975003","avatar3","123456"));
        users.add(new User(4,"Dat","dat@gmail.com","0975004","avatar4","123456"));
        System.out.println(users.get(0).getId());
    }


    @Override
    public List<UserDTO> getListUsers() {
        List<UserDTO> result = new ArrayList<UserDTO>();
        for(User user : users){
            result.add(UserMapper.toUserDTO(user));
        }
        return result;
    }

    @Override
    public UserDTO getUserById(int id) {
        UserDTO result = new UserDTO();
        for(User user : users){
            if(user.getId() == id){
                result = UserMapper.toUserDTO(user);
            }
        }
        return result;
    }

    @Override
    public List<UserDTO> searchUserByName(String name) {
        List<UserDTO> result = new ArrayList<UserDTO>();
        for(User user : users){
            if(Objects.equals(user.getName(), name)){
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
    public void deleteUserById(int id) {
        users.removeIf(user -> Objects.equals(user.getId(), id));
    }

    @Override
    public void creatUser(UserToCreate userToCreate) {
        System.out.println(users.size());
        users.add(new User(users.size()+1,userToCreate.getName(),userToCreate.getEmail(),userToCreate.getPhone(), userToCreate.getAvatar(), userToCreate.getPassword()));

    }

    @Override
    public void updateUser(String email, UserToCreate userToCreate) {
        for(User user : users){
            if(Objects.equals(user.getEmail(), email)){
                user.setName(userToCreate.getName());
                user.setPhone(userToCreate.getPhone());
                user.setAvatar(userToCreate.getAvatar());
                user.setPassword(userToCreate.getPassword());
                break;
            }
        }
    }
}
