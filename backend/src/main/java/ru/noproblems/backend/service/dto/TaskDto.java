package ru.noproblems.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
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

    public TaskForUser toTaskForUser() {

        TaskForUser taskForUser = new TaskForUser();
        taskForUser.setId(id);
        taskForUser.setCondition(condition);
        taskForUser.setSolution(solution);
        taskForUser.setTopic(topic != null ? topic.getName() : null);
        taskForUser.setOlympiad(olympiad != null ? olympiad.getName() : null);
        taskForUser.setComplexity(complexity);
        taskForUser.setYear(year);
        taskForUser.setGrade(grade);
        taskForUser.setAuthor(author);
        taskForUser.setWhoAdded(whoAdded != null ? whoAdded.getLogin() : null);
        return taskForUser;
    }
}
