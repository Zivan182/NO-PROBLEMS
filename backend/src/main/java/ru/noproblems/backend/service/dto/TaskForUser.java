package ru.noproblems.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskForUser {
    private Long id;

    private String condition;
    private String solution;

    private TopicDto topic;
    private OlympiadDto olympiad;

    private Long complexity;
    private Long year;
    private Long grade;

    private String author;
    private UserDto whoAdded;

    private boolean liked;
    private boolean solved;
    private boolean added;
}
