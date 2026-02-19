package com.babunes.controller_jpa_practice.controller;


import com.babunes.controller_jpa_practice.dto.UserDTO;
import com.babunes.controller_jpa_practice.exception.CustomException;
import com.babunes.controller_jpa_practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    private ResponseEntity<?> getUser(@PathVariable(name = "id") Integer id) throws CustomException {
        return createResponse(userService.getUser(id), HttpStatus.CREATED);
    }

    @PostMapping()
    private UserDTO createUser(@RequestBody UserDTO userDTO){
        return userDTO;
    }

    @DeleteMapping()
    private UserDTO deleteUser(@RequestBody UserDTO userDTO){
        return userDTO;
    }

    @PutMapping()
    private UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userDTO;
    }

    @PatchMapping()
    private UserDTO updateUserValues(@RequestBody UserDTO userDTO){
        return userDTO;
    }


    private ResponseEntity<?> createResponse(UserDTO userDTO, HttpStatus status){
        if(status.equals(HttpStatus.CREATED)){
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
