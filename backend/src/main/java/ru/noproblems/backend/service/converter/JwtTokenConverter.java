package ru.noproblems.backend.service.converter;

import org.springframework.stereotype.Component;

import ru.noproblems.backend.data.JwtToken;
import ru.noproblems.backend.service.dto.JwtTokenDto;

@Component
public class JwtTokenConverter {
        public JwtTokenDto toDto(JwtToken jwtToken) {
        if (jwtToken == null) {
            return null;
        }
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setId(jwtToken.getId());
        jwtTokenDto.setToken(jwtToken.getToken());
        return jwtTokenDto;
    }

    public JwtToken toEntity(JwtTokenDto jwtTokenDto) {
        if (jwtTokenDto == null) {
            return null;
        }
        JwtToken jwtToken = new JwtToken();
        jwtToken.setId(jwtTokenDto.getId());
        jwtToken.setToken(jwtTokenDto.getToken());
        return jwtToken;
    }
    
}
