package ru.noproblems.backend.service.converter;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.Role;
import ru.noproblems.backend.data.User;
import ru.noproblems.backend.service.dto.RoleDto;
import ru.noproblems.backend.service.dto.UserDto;

@Component
@RequiredArgsConstructor
public class UserConverter {

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setPassword(user.getPassword());
        userDto.setIsAdmin(user.getIsAdmin());
        
        ArrayList<RoleDto> roles = new ArrayList<RoleDto>();
        var roleConverter = new RoleConverter();
        for (Role role : user.getRoles()) {
            roles.add(roleConverter.toDto(role));
        }
        userDto.setRoles(roles.stream().toList());

        return userDto;
    }

    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPassword(userDto.getPassword());
        user.setIsAdmin(userDto.getIsAdmin());

        ArrayList<Role> rolesDto = new ArrayList<Role>();
        var roleConverter = new RoleConverter();
        for (RoleDto roleDto : userDto.getRoles()) {
            rolesDto.add(roleConverter.toEntity(roleDto));
        }
        user.setRoles(rolesDto.stream().toList());

        return user;
    }
}
