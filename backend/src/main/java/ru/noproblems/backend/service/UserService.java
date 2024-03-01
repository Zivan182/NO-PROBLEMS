package ru.noproblems.backend.service;

import ru.noproblems.backend.service.dto.UserDto;

public interface UserService {
    UserDto getUserById(Long userId);
    UserDto getUserByLogin(String login);
    UserDto saveUser(UserDto userDto);
    UserDto authenticateUser(String login, String password);
    boolean updateUser(Long userId, UserDto userDto);
        // UserDto createUser(UserDto userDto);
    // void deleteUser(Long userId);
    // List<UserDto> getAllUsers();
    
}
