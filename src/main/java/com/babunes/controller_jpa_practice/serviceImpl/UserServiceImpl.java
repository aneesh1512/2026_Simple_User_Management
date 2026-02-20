package com.babunes.controller_jpa_practice.serviceImpl;

import com.babunes.controller_jpa_practice.dto.UserDTO;
import com.babunes.controller_jpa_practice.entity.UserEntity;
import com.babunes.controller_jpa_practice.exception.CustomException;
import com.babunes.controller_jpa_practice.repository.UserRepository;
import com.babunes.controller_jpa_practice.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.security.cert.CertStoreException;
import java.util.List;
import java.util.Objects;
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
        return UserDTO.convertToDTO(List.of(entityF)).get(0);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO){
        UserEntity user = UserEntity.convertTODTO(userDTO);
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers(Integer pageNo, Integer pageSize, String sortOrder, String sortField) throws CustomException {
        Pageable page = PageRequest.of(pageNo, pageSize,
                Sort.by(sortOrder.toLowerCase().contains(Sort.Direction.ASC.toString().toLowerCase())
                        ?Sort.Direction.ASC :Sort.Direction.DESC ,
                        Objects.nonNull(sortField) && !sortField.isEmpty() ? sortField: "name"));
        return UserDTO.convertToDTO(userRepository.findAll(page).toList());
    }

    @Override
    public void deleteUserByEmail(String email) throws CustomException {
        Optional<UserEntity> user = userRepository.findUserEntityByEmail(email);
        if(user.isPresent()){
            userRepository.deleteById(user.get().getId());
        }else{
            throw new CustomException("User not Found");
        }
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws CustomException {
        Optional<UserEntity> user = userRepository.findUserEntityByEmail(userDTO.getEmail());
        if(user.isEmpty()){
            throw new CustomException("User not Found");
        }
        UserEntity u = user.get();
        if(userDTO.getEmail() != null){
            u.setEmail(userDTO.getEmail());
        }
        if(userDTO.getPassword() != null){
            u.setPassword(userDTO.getPassword());
        }
        if(userDTO.getName() != null){
            u.setName(userDTO.getName());
        }
        userRepository.save(u);
        return UserDTO.convertToDTO(List.of(u)).get(0);
    }
}
