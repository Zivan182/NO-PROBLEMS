package ru.noproblems.backend.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.User;
import ru.noproblems.backend.repository.UserRepository;
import ru.noproblems.backend.service.converter.UserConverter;
import ru.noproblems.backend.service.dto.UserDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return userConverter.toDto(user);
    }

    @Override
    public UserDto getUserByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return userConverter.toDto(user);
    }


    @Override
    public boolean updateUser(Long userId, UserDto userDto) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null) {
            return false;
        }

        existingUser.setLogin(userDto.getLogin());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setName(userDto.getName());
        existingUser.setSurname(userDto.getSurname());
        existingUser.setPassword(userDto.getPassword());

        userRepository.save(existingUser);
        return true;
    }

    @Override
    public UserDto saveUser(UserDto user) {
        User userFromDB = userRepository.findByLogin(user.getLogin());

        if (userFromDB != null) {
            return null;
        }

        User newUser = userRepository.save(userConverter.toEntity(user));

        return userConverter.toDto(newUser);
    }

    @Override
    public UserDto authenticateUser(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        return userConverter.toDto(user);
    }

    // @Override
    // public UserDto createUser(UserDto userDto) {
    //     User user = userConverter.toEntity(userDto);
    //     User savedUser = userRepository.save(user);
    //     return userConverter.toDto(savedUser);
    // }
    
    // @Override
    // public void deleteUser(Long userId) {
    //     userRepository.deleteById(userId);
    // }

    // @Override
    // public List<UserDto> getAllUsers() {
    //     return userRepository.findAll()
    //         .stream()
    //         .map(userConverter::toDto)
    //         .toList();
    // }


    
}
