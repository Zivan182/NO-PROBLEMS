package ru.noproblems.backend.service.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String email;
    private String name;
    private String surname;
    private String password;
    private Boolean isAdmin;
    private List<RoleDto> roles;
    
}
