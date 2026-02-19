package com.babunes.controller_jpa_practice.service;

import com.babunes.controller_jpa_practice.dto.UserDTO;
import com.babunes.controller_jpa_practice.exception.CustomException;

public interface UserService {

    UserDTO getUser(Integer id) throws CustomException;
}
