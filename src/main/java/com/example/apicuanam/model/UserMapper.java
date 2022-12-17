package com.example.apicuanam.model;

import com.example.apicuanam.entity.User;
import com.example.apicuanam.model.dto.UserDTO;

public class UserMapper {
    public static UserDTO toUserDTO(User user){
        UserDTO temp = new UserDTO();
        temp.setId(user.getId());
        temp.setName(user.getName());
        temp.setEmail(user.getEmail());
        temp.setAvatar(user.getAvatar());
        temp.setPhone(user.getPhone());
        return temp;
    }
}
