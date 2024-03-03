package ru.noproblems.backend.service;

import java.util.List;

import ru.noproblems.backend.service.dto.FilterParams;
import ru.noproblems.backend.service.dto.TaskDto;
import ru.noproblems.backend.service.dto.TaskRequest;

public interface TaskService {
    TaskDto getDtoFromRequest(TaskRequest request);
    TaskDto getTaskById(Long taskId);
    List<TaskDto> getTasksByFilters(FilterParams filters, Long userId);
    TaskDto saveTask(TaskDto task);
    TaskDto updateTask(Long taskId, TaskDto task);
}
