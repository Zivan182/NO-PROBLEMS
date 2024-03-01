package ru.noproblems.backend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.Task;
import ru.noproblems.backend.repository.TaskRepository;
import ru.noproblems.backend.service.converter.OlympiadConverter;
import ru.noproblems.backend.service.converter.TaskConverter;
import ru.noproblems.backend.service.converter.TopicConverter;
import ru.noproblems.backend.service.dto.FilterParams;
import ru.noproblems.backend.service.dto.TaskDto;
import ru.noproblems.backend.specification.TaskSpecifications;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final TopicConverter topicConverter;
    private final OlympiadConverter olympiadConverter;

    @Override
    public TaskDto getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        return taskConverter.toDto(task);

    }

    @Override
    public List<TaskDto> getTasksByFilters(FilterParams filters, Long userId) {
        Specification<Task> sp = TaskSpecifications.wasAddedByAdminOr(userId);

        if (filters.getAdded() != null && filters.getAdded().size() == 1 && userId != null) {
            Boolean cond = filters.getAdded().get(0) == "yes";
            sp = sp.and(TaskSpecifications.AddedBy(userId, cond));
        }
        if (filters.getSearch() != null) {
            String[] words = filters.getSearch().split("+");
            String newSearch = String.join(" ", words);
            sp = sp.and(TaskSpecifications.conditionContains(newSearch));
        }
        if (filters.getTopic() != null) {
            List<String> newTopics = Collections.emptyList();
            for(String topic: filters.getTopic()) {
                String[] words = topic.split("+");
                String newTopic = String.join(" ", words);
                newTopics.add(newTopic);
            }
            sp =  sp.and(TaskSpecifications.topicIn(newTopics));
        }
        if (filters.getOlympiad() != null) {
            List<String> newOlympiads = Collections.emptyList();
            for(String olympiad: filters.getOlympiad()) {
                String[] words = olympiad.split("+");
                String newOlympiad = String.join(" ", words);
                newOlympiads.add(newOlympiad);
            }
            sp = sp.and(TaskSpecifications.olympiadIn(newOlympiads));
        }
        if (filters.getYearfrom() != null) {
            sp = sp.and(TaskSpecifications.yearNotLess(filters.getYearfrom()));
        }
        if (filters.getYearto() != null) {
            sp = sp.and(TaskSpecifications.yearNotGreater(filters.getYearto()));
        }

        if (filters.getComplexityfrom() != null) {
            sp = sp.and(TaskSpecifications.complexityNotLess(filters.getComplexityfrom()));
        }
        if (filters.getComplexityto() != null) {
            sp = sp.and(TaskSpecifications.complexityNotGreater(filters.getComplexityto()));
        }
        if (filters.getLiked() != null && filters.getLiked().size() == 1 && userId != null) {
            Boolean cond = filters.getLiked().get(0) == "yes";
            sp = sp.and(TaskSpecifications.LikedBy(userId, cond));
        }

        if (filters.getSolved() != null && filters.getSolved().size() == 1 && userId != null) {
            Boolean cond = filters.getSolved().get(0) == "yes";
            sp = sp.and(TaskSpecifications.SolvedBy(userId, cond));

        }
        Long page = (filters.getPage() != null ? filters.getPage() : 1);
        int offset = page.intValue();
        Page<Task> tasks = taskRepository.findAll(sp, PageRequest.of(offset, 20));
        List<TaskDto> tasksDto = Collections.emptyList();
        for (Task task : tasks.getContent()) {
            tasksDto.add(taskConverter.toDto(task));
        }

        return tasksDto;
        
    }

    @Override
    public TaskDto saveTask(TaskDto taskDto) {
        if (taskDto.getId() != null) {
            return null;
        }
        Task newTask = taskRepository.save(taskConverter.toEntity(taskDto));
        return taskConverter.toDto(newTask);

    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if (existingTask == null) {
            return null;
        }

        existingTask.setCondition(taskDto.getCondition());
        existingTask.setSolution(taskDto.getSolution());
        existingTask.setTopic(topicConverter.toEntity(taskDto.getTopic()));
        existingTask.setOlympiad(olympiadConverter.toEntity(taskDto.getOlympiad()));
        existingTask.setComplexity(taskDto.getComplexity());
        existingTask.setYear(taskDto.getYear());
        existingTask.setGrade(taskDto.getGrade());
        existingTask.setAuthor(taskDto.getAuthor());

        return taskConverter.toDto(taskRepository.save(existingTask));


    }
    
}
