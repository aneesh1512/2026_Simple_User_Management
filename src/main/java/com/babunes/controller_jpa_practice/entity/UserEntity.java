package com.babunes.controller_jpa_practice.entity;

import com.babunes.controller_jpa_practice.dto.UserDTO;
import com.babunes.controller_jpa_practice.exception.CustomException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;
    private String email;
    private String password;

//    @CreationTimestamp
//    private LocalDateTime createdAt;

//    @UpdateTimestamp
//    private LocalDateTime updatedAt;


    public static UserEntity convertTODTO(UserDTO userDTO) {
        return UserEntity.builder().name(userDTO.getName()).email(userDTO.getEmail()).password(userDTO.getPassword()).build();
    }
}
