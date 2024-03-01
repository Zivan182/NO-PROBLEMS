package ru.noproblems.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.noproblems.backend.data.LikeReaction;
import ru.noproblems.backend.data.Task;
import ru.noproblems.backend.data.User;

public interface LikeReactionRepository extends JpaRepository<LikeReaction, Long>{
    LikeReaction findByUserAndTask(User user, Task task);
}
