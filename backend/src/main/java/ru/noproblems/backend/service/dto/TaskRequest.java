package ru.noproblems.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    private Long id;
    private String condition;
    private String solution;
    private String topic;
    private String olympiad;
    private Long complexity;
    private Long year;
    private Long grade;
    private String author;

}
