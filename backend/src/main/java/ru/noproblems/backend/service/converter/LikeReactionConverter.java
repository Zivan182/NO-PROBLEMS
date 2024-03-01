package ru.noproblems.backend.service.converter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.LikeReaction;
import ru.noproblems.backend.service.dto.LikeReactionDto;

@Component
@RequiredArgsConstructor
public class LikeReactionConverter {
    public LikeReactionDto toDto(LikeReaction likeReaction) {
        if (likeReaction == null) {
            return null;
        }
        LikeReactionDto likeReactionDto = new LikeReactionDto();
        likeReactionDto.setId(likeReaction.getId());
        var userConverter = new UserConverter();
        likeReactionDto.setUser(userConverter.toDto(likeReaction.getUser()));
        var taskConverter = new TaskConverter();
        likeReactionDto.setTask(taskConverter.toDto(likeReaction.getTask()));
        return likeReactionDto;        
    }

    public LikeReaction toEntity(LikeReactionDto likeReactionDto) {
        if (likeReactionDto == null) {
            return null;
        }
        LikeReaction likeReaction = new LikeReaction();
        likeReaction.setId(likeReactionDto.getId());
        var userConverter = new UserConverter();
        likeReaction.setUser(userConverter.toEntity(likeReactionDto.getUser()));
        var taskConverter = new TaskConverter();
        likeReaction.setTask(taskConverter.toEntity(likeReactionDto.getTask()));
        return likeReaction;        
    }
    
}
