package com.babunes.controller_jpa_practice.controller;


import com.babunes.controller_jpa_practice.dto.UserDTO;
import com.babunes.controller_jpa_practice.exception.CustomException;
import com.babunes.controller_jpa_practice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.apache.coyote.Response;
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
        return createResponse(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping()
    private ResponseEntity<?> getUsers(@RequestParam(name="page") Integer pageNo,
                                       @RequestParam(required = false, name="size", defaultValue = "10") Integer pageSize,
                                       @RequestParam(required = false, name="sort", defaultValue = "asc") String sortOrder,
                                       @RequestParam(required = false, name="sortf")  String sortField
                                       ) throws CustomException {
        return createResponse(userService.getAllUsers(pageNo, pageSize, sortOrder,sortField), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) throws CustomException {
        return createResponse(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    private ResponseEntity<?> deleteUser(@PathVariable(name = "email") @NotBlank(message = "email cannot be blank")
                                             String email) throws CustomException {
        userService.deleteUserByEmail(email);
        return createResponse(null, HttpStatus.OK);
    }

    //TODO: to do later
    @PutMapping()
    private ResponseEntity<?> updateUser(){
        return createResponse(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @PatchMapping()
    private ResponseEntity<?> updateUserValues(@RequestBody UserDTO userDTO) throws CustomException {
        return createResponse(userService.updateUser(userDTO), HttpStatus.OK);
    }

    // if we need to add some headers in future for any APIs
    private ResponseEntity<?> createResponse(Object t, HttpStatus status){
        if(status.equals(HttpStatus.CREATED)){
            return new ResponseEntity<>(t, status);
        }else if(status.equals(HttpStatus.OK)){
            return new ResponseEntity<>(t, status);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
