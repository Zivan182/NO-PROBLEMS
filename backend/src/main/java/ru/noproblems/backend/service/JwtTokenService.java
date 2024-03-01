package ru.noproblems.backend.service;

import ru.noproblems.backend.service.dto.JwtTokenDto;

public interface JwtTokenService {
    JwtTokenDto createToken(JwtTokenDto tokenDto);
    JwtTokenDto getTokenById(Long id);
    JwtTokenDto getTokenByName(String token);
    boolean deleteById(Long id);

    // boolean saveToken(JwtTokenDto token);


}