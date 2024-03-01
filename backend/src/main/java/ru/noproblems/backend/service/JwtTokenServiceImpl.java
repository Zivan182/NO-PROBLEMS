package ru.noproblems.backend.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.JwtToken;
import ru.noproblems.backend.repository.JwtTokenRepository;
import ru.noproblems.backend.service.converter.JwtTokenConverter;
import ru.noproblems.backend.service.dto.JwtTokenDto;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtTokenConverter jwtTokenConverter;
    private final JwtTokenRepository jwtTokenRepository;

    @Override
    public JwtTokenDto createToken(JwtTokenDto tokenDto) {
        JwtToken token = jwtTokenConverter.toEntity(tokenDto);
        JwtToken savedToken = jwtTokenRepository.save(token);
        return jwtTokenConverter.toDto(savedToken);
    }

    @Override
    public JwtTokenDto getTokenById(Long id) {
        JwtToken jwtToken = jwtTokenRepository.findById(id).orElse(null);
        if (jwtToken == null) {
            return null;
        }
        return jwtTokenConverter.toDto(jwtToken);
    }

    @Override
    public JwtTokenDto getTokenByName(String token) {
        JwtToken jwtToken = jwtTokenRepository.findByToken(token);
        if (token == null) {
            return null;
        }
        return jwtTokenConverter.toDto(jwtToken);
    }

    @Override
    public boolean deleteById(Long id) {
        JwtTokenDto tokenPresent = this.getTokenById(id);
        if (tokenPresent == null) {
            return false;
        }

        jwtTokenRepository.deleteById(id);
        return false;
    }

    // @Override
    // public boolean saveToken(JwtTokenDto token) {
    //     JwtToken tokenFromDB = jwtTokenRepository.findById(token.getId()).orElse(null);
    //     if (tokenFromDB == null) {
    //         return false;
    //     }

    //     JwtTokenDto newJwtToken = new JwtTokenDto();
    //     newJwtToken.setId(token.getId());
    //     newJwtToken.setToken(token.getToken());

    //     jwtTokenRepository.save(jwtTokenConverter.toEntity(newJwtToken));

    //     return true;
    // }
    
}
