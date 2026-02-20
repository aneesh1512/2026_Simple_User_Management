package com.babunes.controller_jpa_practice.dto;

import com.babunes.controller_jpa_practice.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//TODO: below tells lombok to use Builder to create object b
//@Jacksonized
public class UserDTO {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;

    public static List<UserDTO> convertToDTO(List<UserEntity> entity) {
        List<UserDTO> list = new ArrayList<>();
        for(UserEntity userEntity : entity) {
            list.add(UserDTO.builder().name(userEntity.getName())
                    .email(userEntity.getEmail())
                    .password(userEntity.getPassword()).build());
        }
        return list;
    }
}
