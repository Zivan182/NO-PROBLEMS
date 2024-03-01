package ru.noproblems.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.noproblems.backend.data.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByLogin(String login);
}
