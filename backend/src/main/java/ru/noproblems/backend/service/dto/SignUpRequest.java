package ru.noproblems.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String login;
    private String email;
    private String name;
    private String surname;
    private String password;   
    
    public UserDto toUserDto() {
        UserDto userDto = new UserDto();
        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setName(name);
        userDto.setSurname(surname);
        userDto.setPassword(password);
        userDto.setIsAdmin(false);
        return userDto;
    }
}
