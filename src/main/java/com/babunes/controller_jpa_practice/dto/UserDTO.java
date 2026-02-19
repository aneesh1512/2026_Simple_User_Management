package com.babunes.controller_jpa_practice.dto;

import com.babunes.controller_jpa_practice.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private String name;
    private String email;
    private String password;

    public static UserDTO convertToDTO(UserEntity entity) {
        return
                UserDTO.builder().name(entity.getName()).email(entity.getEmail()).password(entity.getPassword()).build();
    }
}
