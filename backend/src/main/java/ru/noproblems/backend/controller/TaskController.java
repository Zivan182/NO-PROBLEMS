package ru.noproblems.backend.controller;

import java.util.ArrayList;
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
import ru.noproblems.backend.service.dto.TaskRequest;
import ru.noproblems.backend.service.dto.UserDto;

@CrossOrigin(origins = "http://localhost:*", maxAge = 3600)
@RestController
@RequestMapping("/tasksinfo")
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
            FilterParams filterParams
            ) {

        
        if (jwtToken.equals("")) {
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
            List<TaskDto> tasksDto = taskService.getTasksByFilters(filterParams, userId);
            List<TaskForUser> tasksForUser = new ArrayList<TaskForUser>();
            for (TaskDto taskDto : tasksDto) {
                TaskForUser taskForUser = taskDto.toTaskForUser();
                
                taskForUser.setAdded(taskDto.getWhoAdded().getId().equals(userId));
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

    // @GetMapping("/search2")
    // public ResponseEntity<?> getTasksA(
    //         @RequestHeader("ApiToken") final String jwtToken,
    //         @RequestParam FilterParams filterParams
    //         ) {

        
    //     if (jwtToken == "") {
    //         if (filterParams.getLiked() != null || filterParams.getSolved() != null || 
    //                 filterParams.getAdded() != null) {
    //             return ResponseEntity.badRequest().body("Permission denied");
    //         }
    //         List<TaskDto> tasksDto = taskService.getTasksByFilters(filterParams, null);
    //         return ResponseEntity.ok(tasksDto);


    //     }
    //     else {
    //         JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
    //         if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
    //             return ResponseEntity.badRequest().body("Invalid token");
    //         }
    //         Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
    //         List<TaskDto> tasksDto = taskService.getTasksByFilters(filterParams, userId);
    //         List<TaskForUser> tasksForUser = new ArrayList<TaskForUser>();
    //         for (TaskDto taskDto : tasksDto) {
    //             TaskForUser taskForUser = taskDto.toTaskForUser();
                
    //             taskForUser.setAdded(taskDto.getWhoAdded().getId() == userId);
    //             UserDto userDto = userService.getUserById(userId);
    //             LikeReactionDto likeReactionDto = reactionService.getLikeReaction(userDto, taskDto);
    //             taskForUser.setLiked(likeReactionDto != null);
    //             SolveReactionDto solveReactionDto = reactionService.getSolveReaction(userDto, taskDto);
    //             taskForUser.setSolved(solveReactionDto != null);
    //             tasksForUser.add(taskForUser);
    //         }
    //         return ResponseEntity.ok(tasksForUser);
    //     }
    // }
    
    // @GetMapping("/search1")
    // public ResponseEntity<?> getTasks(
    //         @RequestHeader("ApiToken") final String jwtToken,
    //         @RequestParam(value = "search", required = false) String search,
    //         @RequestParam(value = "topic", required = false) List<String> topic,
    //         @RequestParam(value = "olympiad", required = false) List<String> olympiad,
    //         @RequestParam(value = "complexity_from", required = false) Long complexityfrom,
    //         @RequestParam(value = "complexity_to", required = false) Long complexityto,
    //         @RequestParam(value = "year_from", required = false) Long yearfrom,
    //         @RequestParam(value = "year_to", required = false) Long yearto,
    //         @RequestParam(value = "liked", required = false) List<String> liked,
    //         @RequestParam(value = "solved", required = false) List<String> solved,
    //         @RequestParam(value = "added", required = false) List<String> added,
    //         @RequestParam(value = "added", required = false) Long page
            
    //         ) {
    //     FilterParams filterParams = new FilterParams();
    //     filterParams.setSearch(search);
    //     filterParams.setTopic(topic);
    //     filterParams.setOlympiad(olympiad);
    //     filterParams.setComplexityfrom(complexityfrom);
    //     filterParams.setComplexityto(complexityto);
    //     filterParams.setYearfrom(yearfrom);
    //     filterParams.setYearto(yearto);
    //     filterParams.setLiked(liked);
    //     filterParams.setSolved(solved);
    //     filterParams.setAdded(added);
    //     filterParams.setPage(page);

    //     if (jwtToken == "") {
    //         if (liked != null || solved != null || added != null) {
    //             return ResponseEntity.badRequest().body("Permission denied");
    //         }
    //         List<TaskDto> tasksDto = taskService.getTasksByFilters(filterParams, null);
    //         return ResponseEntity.ok(tasksDto);


    //     }
    //     else {
    //         JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
    //         if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
    //             return ResponseEntity.badRequest().body("Invalid token");
    //         }
    //         Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
    //         List<TaskDto> tasksDto = taskService.getTasksByFilters(filterParams, userId);
    //         List<TaskForUser> tasksForUser = new ArrayList<TaskForUser>();
    //         for (TaskDto taskDto : tasksDto) {
    //             TaskForUser taskForUser = taskDto.toTaskForUser();
                
    //             taskForUser.setAdded(taskDto.getWhoAdded().getId() == userId);
    //             UserDto userDto = userService.getUserById(userId);
    //             LikeReactionDto likeReactionDto = reactionService.getLikeReaction(userDto, taskDto);
    //             taskForUser.setLiked(likeReactionDto != null);
    //             SolveReactionDto solveReactionDto = reactionService.getSolveReaction(userDto, taskDto);
    //             taskForUser.setSolved(solveReactionDto != null);
    //             tasksForUser.add(taskForUser);
    //         }
    //         return ResponseEntity.ok(tasksForUser);
    //     }
    // }

    // @GetMapping("/search")
    // public ResponseEntity<?> getTasks(
    //         @RequestHeader("ApiToken") final String jwtToken,
    //         @RequestBody final FilterParams filterParams) {
    //     if (jwtToken == "") {
    //         if (filterParams.getLiked() != null || filterParams.getSolved() != null || 
    //                 filterParams.getAdded() != null) {
    //             return ResponseEntity.badRequest().body("Permission denied");
    //         }
    //         List<TaskDto> tasksDto = taskService.getTasksByFilters(filterParams, null);
    //         return ResponseEntity.ok(tasksDto);


    //     }
    //     else {
    //         JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
    //         if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
    //             return ResponseEntity.badRequest().body("Invalid token");
    //         }
    //         Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
    //         filterParams.setWhoAddedId(userId);
    //         List<TaskDto> tasksDto = taskService.getTasksByFilters(filterParams, userId);
    //         List<TaskForUser> tasksForUser = new ArrayList<TaskForUser>();
    //         for (TaskDto taskDto : tasksDto) {
    //             TaskForUser taskForUser = taskDto.toTaskForUser();
                
    //             taskForUser.setAdded(taskDto.getWhoAdded().getId() == userId);
    //             UserDto userDto = userService.getUserById(userId);
    //             LikeReactionDto likeReactionDto = reactionService.getLikeReaction(userDto, taskDto);
    //             taskForUser.setLiked(likeReactionDto != null);
    //             SolveReactionDto solveReactionDto = reactionService.getSolveReaction(userDto, taskDto);
    //             taskForUser.setSolved(solveReactionDto != null);
    //             tasksForUser.add(taskForUser);
    //         }
    //         return ResponseEntity.ok(tasksForUser);
    //     }
    // }


    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTask(
            @RequestHeader("ApiToken") final String jwtToken,
            @PathVariable Long taskId) {
            TaskDto taskDto = taskService.getTaskById(taskId);
            if (taskDto == null) {
                return ResponseEntity.status(405).body("Invalid taskId");
            }
        if (jwtToken.equals("")) {
            if (!taskDto.getWhoAdded().getIsAdmin()) {
                return ResponseEntity.status(406).body("Permission denied");
            }
            TaskForUser taskForUser = taskDto.toTaskForUser();
            return ResponseEntity.ok(taskForUser);
        }
        else {
            JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
            if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
                return ResponseEntity.status(407).body("Invalid token");
            }
            Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
            if (!taskDto.getWhoAdded().getIsAdmin() && !taskDto.getWhoAdded().getId().equals(userId)) {
                return ResponseEntity.status(408).body("Permission denied");
            }
            UserDto userDto = userService.getUserById(userId);
            TaskForUser taskForUser = taskDto.toTaskForUser();
            taskForUser.setAdded(taskDto.getWhoAdded().getId().equals(userId));

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
            @RequestBody final TaskRequest taskRequest) {

        TaskDto oldTaskDto = taskService.getTaskById(taskId);
        if (oldTaskDto == null) {
            return ResponseEntity.status(401).body("Invalid taskId");
        }

        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.status(402).body("Invalid token");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        TaskDto taskDto = taskService.getDtoFromRequest(taskRequest);
        if (!taskDto.getId().equals(taskId)) {
            return ResponseEntity.status(405).body("Invalid data to change");
        }

        if (!taskService.getTaskById(taskId).getWhoAdded().getId().equals(userId)) {
            return ResponseEntity.status(403).body("Permission denied");
        }

        taskService.updateTask(taskId, taskDto);
        return ResponseEntity.ok().body("Task changed");
    }



    @PostMapping("/add")
    public ResponseEntity<?> addTask(
            @RequestHeader("ApiToken") final String jwtToken,
            @RequestBody final TaskRequest taskRequest) {

        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);
        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        TaskDto taskDto = taskService.getDtoFromRequest(taskRequest);
        taskDto.setWhoAdded(userService.getUserById(userId));
        taskService.saveTask(taskDto);
        return ResponseEntity.ok().body("Task added");
    }
    
}
