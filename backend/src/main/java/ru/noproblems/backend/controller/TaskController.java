package ru.noproblems.backend.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.security.JwtTokenProvider;
import ru.noproblems.backend.service.JwtTokenService;
import ru.noproblems.backend.service.ReactionService;
import ru.noproblems.backend.service.TaskService;
import ru.noproblems.backend.service.UserService;
import ru.noproblems.backend.service.dto.FilterParams;
import ru.noproblems.backend.service.dto.JwtTokenDto;
import ru.noproblems.backend.service.dto.LikeReactionDto;
import ru.noproblems.backend.service.dto.SolveReactionDto;
import ru.noproblems.backend.service.dto.TaskDto;
import ru.noproblems.backend.service.dto.TaskForUser;
import ru.noproblems.backend.service.dto.UserDto;

@CrossOrigin(origins = "http://localhost:*", maxAge = 3600)
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenService jwtTokenService;
    private final TaskService taskService;
    private final UserService userService;
    private final ReactionService reactionService;


    @GetMapping("/search")
    public ResponseEntity<?> getTasks(
            @RequestHeader("ApiToken") final String jwtToken,
            @RequestBody final FilterParams filterParams) {
        if (jwtToken == null) {
            if (filterParams.getLiked() != null || filterParams.getSolved() != null || 
                    filterParams.getAdded() != null) {
                return ResponseEntity.badRequest().body("Permission denied");
            }
            List<TaskDto> tasksDto = taskService.getTasksByFilters(filterParams, null);
            return ResponseEntity.ok(tasksDto);


        }
        else {
            JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
            if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
                return ResponseEntity.badRequest().body("Invalid token");
            }
            Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
            filterParams.setWhoAddedId(userId);
            List<TaskDto> tasksDto = taskService.getTasksByFilters(filterParams, userId);
            List<TaskForUser> tasksForUser = Collections.emptyList();
            for (TaskDto taskDto : tasksDto) {
                TaskForUser taskForUser = taskDto.toTaskForUser();
                
                taskForUser.setAdded(taskDto.getWhoAdded().getId() == userId);
                UserDto userDto = userService.getUserById(userId);
                LikeReactionDto likeReactionDto = reactionService.getLikeReaction(userDto, taskDto);
                taskForUser.setLiked(likeReactionDto != null);
                SolveReactionDto solveReactionDto = reactionService.getSolveReaction(userDto, taskDto);
                taskForUser.setSolved(solveReactionDto != null);
                tasksForUser.add(taskForUser);
            }
            return ResponseEntity.ok(tasksForUser);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTask(
            @RequestHeader("ApiToken") final String jwtToken,
            @PathVariable Long taskId) {
            TaskDto taskDto = taskService.getTaskById(taskId);
            if (taskDto == null) {
                return ResponseEntity.badRequest().body("Invalid taskId");
            }
        if (jwtToken == null) {
            if (!taskDto.getWhoAdded().getIsAdmin()) {
                return ResponseEntity.badRequest().body("Permission denied");
            }
            return ResponseEntity.ok(taskDto);
        }
        else {
            JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
            if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
                return ResponseEntity.badRequest().body("Invalid token");
            }
            Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
            if (!taskDto.getWhoAdded().getIsAdmin() && taskDto.getWhoAdded().getId() != userId) {
                return ResponseEntity.badRequest().body("Permission denied");
            }
            UserDto userDto = userService.getUserById(userId);
            TaskForUser taskForUser = taskDto.toTaskForUser();
            taskForUser.setAdded(taskDto.getWhoAdded().getId() == userId);

            LikeReactionDto likeReactionDto = reactionService.getLikeReaction(userDto, taskDto);
            taskForUser.setLiked(likeReactionDto != null);
            SolveReactionDto solveReactionDto = reactionService.getSolveReaction(userDto, taskDto);
            taskForUser.setSolved(solveReactionDto != null);

            return ResponseEntity.ok(taskForUser);
        }
    }

    @PutMapping("/{taskId}/edit")
    public ResponseEntity<?> editTask(
            @RequestHeader("ApiToken") final String jwtToken,
            @PathVariable Long taskId,
            @RequestBody final TaskDto taskDto) {

        TaskDto oldTaskDto = taskService.getTaskById(taskId);
        if (oldTaskDto == null) {
            return ResponseEntity.badRequest().body("Invalid taskId");
        }

        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        if (userId != taskDto.getWhoAdded().getId()) {
            return ResponseEntity.badRequest().body("Permission denied");
        }

        if (taskId != taskDto.getId()) {
            return ResponseEntity.badRequest().body("Invalid data to change");
        }

        taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok().body("Task changed");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTask(
            @RequestHeader("ApiToken") final String jwtToken,
            @RequestBody final TaskDto taskDto) {

        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        taskDto.setWhoAdded(userService.getUserById(userId));
        taskService.saveTask(taskDto);
        return ResponseEntity.ok().body("Task added");
    }
    
}
