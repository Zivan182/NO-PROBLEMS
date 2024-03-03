package ru.noproblems.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.noproblems.backend.data.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByName(String name);
}
