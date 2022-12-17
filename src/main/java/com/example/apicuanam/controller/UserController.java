package com.example.apicuanam.controller;

import com.example.apicuanam.entity.User;
import com.example.apicuanam.model.UserToCreate;
import com.example.apicuanam.model.dto.UserDTO;
import com.example.apicuanam.service.UserService;
import com.example.apicuanam.service.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<UserDTO> users = userService.getListUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDTO result = userService.getUserById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUserByName(@RequestParam(required = false,defaultValue = "") String name) {
        List<UserDTO> result = userService.searchUserByName(name);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        List<UserDTO> result = userService.getListUsers();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<?> creatUser(@RequestBody UserToCreate userToCreate){
        userService.creatUser(userToCreate);
        return ResponseEntity.ok(userService.getListUsers());
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateUserByEmail(@PathVariable String email,@RequestBody UserToCreate userToUpdate) {
        userService.updateUser(email,userToUpdate);
        return ResponseEntity.ok(userService.getListUsers());
    }
}
