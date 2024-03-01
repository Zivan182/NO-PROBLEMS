package ru.noproblems.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeReactionDto {
    private Long id;
    private UserDto user;
    private TaskDto task;    
}
