package ru.noproblems.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.noproblems.backend.data.JwtToken;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    JwtToken findByToken(String token);
}
