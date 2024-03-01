package ru.noproblems.backend.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.LikeReaction;
import ru.noproblems.backend.data.SolveReaction;
import ru.noproblems.backend.repository.LikeReactionRepository;
import ru.noproblems.backend.repository.SolveReactionRepository;
import ru.noproblems.backend.service.converter.LikeReactionConverter;
import ru.noproblems.backend.service.converter.SolveReactionConverter;
import ru.noproblems.backend.service.converter.TaskConverter;
import ru.noproblems.backend.service.converter.UserConverter;
import ru.noproblems.backend.service.dto.LikeReactionDto;
import ru.noproblems.backend.service.dto.SolveReactionDto;
import ru.noproblems.backend.service.dto.TaskDto;
import ru.noproblems.backend.service.dto.UserDto;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final LikeReactionRepository likeReactionRepository;
    private final SolveReactionRepository solveReactionRepository;
    private final LikeReactionConverter likeReactionConverter;
    private final SolveReactionConverter solveReactionConverter;

    private final UserConverter userConverter;
    private final TaskConverter taskConverter;


    public LikeReactionDto createLikeReaction(UserDto user, TaskDto task) {

        LikeReaction likeReactionFromDB = likeReactionRepository.findByUserAndTask(
            userConverter.toEntity(user), taskConverter.toEntity(task));
        if (likeReactionFromDB != null) {
            return null;
        }
        LikeReactionDto likeReactionDto = new LikeReactionDto();
        likeReactionDto.setUser(user);
        likeReactionDto.setTask(task);
        LikeReaction newLikeReaction = likeReactionRepository.save(likeReactionConverter.toEntity(likeReactionDto));
        return likeReactionConverter.toDto(newLikeReaction);

    }

    public LikeReactionDto getLikeReaction(UserDto user, TaskDto task) {
        LikeReaction likeReaction = likeReactionRepository.findByUserAndTask(
            userConverter.toEntity(user), taskConverter.toEntity(task));
        return likeReactionConverter.toDto(likeReaction);

    }

    public void deleteLikeReaction(UserDto user, TaskDto task) {
        LikeReactionDto likeReactionDto = getLikeReaction(user, task);
        if (likeReactionDto == null) {
            return;
        }
        likeReactionRepository.deleteById(likeReactionDto.getId());

    }

    public SolveReactionDto createSolveReaction(UserDto user, TaskDto task) {

        SolveReaction solveReactionFromDB = solveReactionRepository.findByUserAndTask(
            userConverter.toEntity(user), taskConverter.toEntity(task));
        if (solveReactionFromDB != null) {
            return null;
        }
        SolveReactionDto solveReactionDto = new SolveReactionDto();
        solveReactionDto.setUser(user);
        solveReactionDto.setTask(task);
        SolveReaction newSolveReaction = solveReactionRepository.save(solveReactionConverter.toEntity(solveReactionDto));
        return solveReactionConverter.toDto(newSolveReaction);

    }

    public SolveReactionDto getSolveReaction(UserDto user, TaskDto task) {
        SolveReaction solveReaction = solveReactionRepository.findByUserAndTask(
            userConverter.toEntity(user), taskConverter.toEntity(task));
        return solveReactionConverter.toDto(solveReaction);

    }

    public void deleteSolveReaction(UserDto user, TaskDto task) {
        SolveReactionDto solveReactionDto = getSolveReaction(user, task);
        if (solveReactionDto == null) {
            return;
        }
        solveReactionRepository.deleteById(solveReactionDto.getId());

    }
    
}
