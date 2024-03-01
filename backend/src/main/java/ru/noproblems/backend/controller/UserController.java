package ru.noproblems.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.User;
import ru.noproblems.backend.security.JwtTokenProvider;
import ru.noproblems.backend.security.UserPrincipal;
import ru.noproblems.backend.service.JwtTokenService;
import ru.noproblems.backend.service.RoleService;
import ru.noproblems.backend.service.UserService;
import ru.noproblems.backend.service.converter.UserConverter;
import ru.noproblems.backend.service.dto.AuthRequest;
import ru.noproblems.backend.service.dto.AuthResponse;
import ru.noproblems.backend.service.dto.JwtTokenDto;
import ru.noproblems.backend.service.dto.RoleDto;
import ru.noproblems.backend.service.dto.SignUpRequest;
import ru.noproblems.backend.service.dto.UserDto;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenService jwtTokenService;
    private final UserService userService;
    private final UserConverter userConverter;
    private final RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody AuthRequest authRequest) {
        UserDto userDto = userService.authenticateUser(authRequest.getLogin(), authRequest.getPassword());
        if (userDto == null) {
            return ResponseEntity.status(403).body("Invalid login or password"); 
        }
        String token = jwtTokenProvider.createToken(userDto.getId(), userDto.getLogin(), userDto.getRoles());
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setToken(token);
        jwtTokenService.createToken(jwtTokenDto);
        return ResponseEntity.ok(new AuthResponse(token));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        UserDto userDto = signUpRequest.toUserDto();
        RoleDto role = roleService.getRoleByName("USER");
        if (role == null) {
            RoleDto roleDto = new RoleDto();
            roleDto.setName("USER");
            role = roleService.createRole(roleDto);
        }
        userDto.setRoles(List.of(role));
        userDto = userService.saveUser(userDto);
        if(userDto == null) {
            return ResponseEntity.status(405).body("User with such login exists");
        }
        User user = userConverter.toEntity(userDto);
        String token = jwtTokenProvider.generateToken(new UserPrincipal(user.getId(), user.getLogin(), user.getPassword(), user.getRoles()));
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setToken(token);
        jwtTokenService.createToken(jwtTokenDto);
        return ResponseEntity.ok(new AuthResponse(token));
    }
  
    @GetMapping("/logout")
    public ResponseEntity<?> logOut(
            @RequestHeader("ApiToken") final String jwtToken) {
        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);

        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        jwtTokenService.deleteById(token.getId());

        return ResponseEntity.ok().body("Logged out");
    }

    @GetMapping("/data")
    public ResponseEntity<?> getUserData(
            @RequestHeader("ApiToken") final String jwtToken) {
        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);

        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        UserDto userDto = userService.getUserById(userId);

        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/data/edit")
    public ResponseEntity<?> editUserData(
            @RequestHeader("ApiToken") final String jwtToken,
            @RequestBody SignUpRequest signUpRequest) {
        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);

        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        userService.updateUser(userId, signUpRequest.toUserDto());

        return ResponseEntity.ok().body("User data has been changed");
    }

    
}
