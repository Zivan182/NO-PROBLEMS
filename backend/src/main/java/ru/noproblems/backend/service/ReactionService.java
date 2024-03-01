package ru.noproblems.backend.service;

import ru.noproblems.backend.service.dto.LikeReactionDto;
import ru.noproblems.backend.service.dto.SolveReactionDto;
import ru.noproblems.backend.service.dto.TaskDto;
import ru.noproblems.backend.service.dto.UserDto;

public interface ReactionService {
    LikeReactionDto createLikeReaction(UserDto user, TaskDto task);
    LikeReactionDto getLikeReaction(UserDto user, TaskDto task);
    void deleteLikeReaction(UserDto user, TaskDto task);

    SolveReactionDto createSolveReaction(UserDto user, TaskDto task);
    SolveReactionDto getSolveReaction(UserDto user, TaskDto task);
    void deleteSolveReaction(UserDto user, TaskDto task);
}
