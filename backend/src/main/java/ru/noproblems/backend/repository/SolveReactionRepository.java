package ru.noproblems.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.noproblems.backend.data.SolveReaction;
import ru.noproblems.backend.data.Task;
import ru.noproblems.backend.data.User;

public interface SolveReactionRepository extends JpaRepository<SolveReaction, Long>{
    SolveReaction findByUserAndTask(User user, Task task);
}
