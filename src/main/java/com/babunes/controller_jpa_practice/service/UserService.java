package com.babunes.controller_jpa_practice.service;

import com.babunes.controller_jpa_practice.dto.UserDTO;
import com.babunes.controller_jpa_practice.exception.CustomException;

import java.util.List;

public interface UserService {

    UserDTO getUser(Integer id) throws CustomException;
    UserDTO createUser(UserDTO userDTO) throws CustomException;
    List<UserDTO> getAllUsers(Integer pageNo, Integer pageSize, String sortOrder, String sortField) throws CustomException;
    void deleteUserByEmail(String email) throws CustomException;

    UserDTO updateUser(UserDTO userDTO) throws CustomException;
}
