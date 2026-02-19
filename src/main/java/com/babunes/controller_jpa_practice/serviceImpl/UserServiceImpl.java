package com.babunes.controller_jpa_practice.serviceImpl;

import com.babunes.controller_jpa_practice.dto.UserDTO;
import com.babunes.controller_jpa_practice.entity.UserEntity;
import com.babunes.controller_jpa_practice.exception.CustomException;
import com.babunes.controller_jpa_practice.repository.UserRepository;
import com.babunes.controller_jpa_practice.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUser(Integer id) throws CustomException {
        Optional<UserEntity> entity = this.userRepository.findById(id);
        UserEntity entityF = entity.orElseThrow(() -> new CustomException("User not Found"));
        return UserDTO.convertToDTO(entityF);
    }
}
