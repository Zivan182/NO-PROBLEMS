package ru.noproblems.backend.service.converter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.SolveReaction;
import ru.noproblems.backend.service.dto.SolveReactionDto;

@Component
@RequiredArgsConstructor
public class SolveReactionConverter {
    public SolveReactionDto toDto(SolveReaction solveReaction) {
        if (solveReaction == null) {
            return null;
        }
        SolveReactionDto solveReactionDto = new SolveReactionDto();
        solveReactionDto.setId(solveReaction.getId());
        var userConverter = new UserConverter();
        solveReactionDto.setUser(userConverter.toDto(solveReaction.getUser()));
        var taskConverter = new TaskConverter();
        solveReactionDto.setTask(taskConverter.toDto(solveReaction.getTask()));
        return solveReactionDto;        
    }

    public SolveReaction toEntity(SolveReactionDto solveReactionDto) {
        if (solveReactionDto == null) {
            return null;
        }
        SolveReaction solveReaction = new SolveReaction();
        solveReaction.setId(solveReactionDto.getId());
        var userConverter = new UserConverter();
        solveReaction.setUser(userConverter.toEntity(solveReactionDto.getUser()));
        var taskConverter = new TaskConverter();
        solveReaction.setTask(taskConverter.toEntity(solveReactionDto.getTask()));
        return solveReaction;        
    }
    
}
