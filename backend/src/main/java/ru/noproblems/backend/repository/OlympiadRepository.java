package ru.noproblems.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.noproblems.backend.data.Olympiad;

public interface OlympiadRepository extends JpaRepository<Olympiad, Long> {
    Olympiad findByName(String name);
}
