package ru.noproblems.backend.service.converter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.Task;
import ru.noproblems.backend.service.dto.TaskDto;

@Component
@RequiredArgsConstructor
public class TaskConverter {
    public TaskDto toDto(Task task) {
        if (task == null) {
            return null;
        }
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setCondition(task.getCondition());
        taskDto.setSolution(task.getSolution());
        var topicConverter = new TopicConverter();
        taskDto.setTopic(topicConverter.toDto(task.getTopic()));
        var olympiadConverter = new OlympiadConverter();
        taskDto.setOlympiad(olympiadConverter.toDto(task.getOlympiad()));
        taskDto.setComplexity(task.getComplexity());
        taskDto.setYear(task.getYear());
        taskDto.setGrade(task.getGrade());
        taskDto.setAuthor(task.getAuthor());
        var userConverter = new UserConverter();
        taskDto.setWhoAdded(userConverter.toDto(task.getWhoAdded()));
        return taskDto;
    }

    public Task toEntity(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setCondition(taskDto.getCondition());
        task.setSolution(taskDto.getSolution());
        var topicConverter = new TopicConverter();
        task.setTopic(topicConverter.toEntity(taskDto.getTopic()));
        var olympiadConverter = new OlympiadConverter();
        task.setOlympiad(olympiadConverter.toEntity(taskDto.getOlympiad()));
        task.setComplexity(taskDto.getComplexity());
        task.setYear(taskDto.getYear());
        task.setGrade(taskDto.getGrade());
        task.setAuthor(taskDto.getAuthor());
        var userConverter = new UserConverter();
        task.setWhoAdded(userConverter.toEntity(taskDto.getWhoAdded()));
        return task;
    }
}
