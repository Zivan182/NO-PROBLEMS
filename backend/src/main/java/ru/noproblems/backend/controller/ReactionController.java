package ru.noproblems.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.security.JwtTokenProvider;
import ru.noproblems.backend.service.JwtTokenService;
import ru.noproblems.backend.service.ReactionService;
import ru.noproblems.backend.service.TaskService;
import ru.noproblems.backend.service.UserService;
import ru.noproblems.backend.service.dto.JwtTokenDto;
import ru.noproblems.backend.service.dto.LikeReactionDto;
import ru.noproblems.backend.service.dto.SolveReactionDto;
import ru.noproblems.backend.service.dto.TaskDto;
import ru.noproblems.backend.service.dto.UserDto;

@RestController
@RequestMapping("/reactions")
@RequiredArgsConstructor
public class ReactionController {

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenService jwtTokenService;
    private final ReactionService reactionService;
    private final UserService userService;
    private final TaskService taskService;

    @PostMapping("/like/{taskId}")
    public ResponseEntity<?> addLikeReaction(
            @RequestHeader("ApiToken") final String jwtToken,
            @PathVariable Long taskId) {
        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);

        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        UserDto userDto = userService.getUserById(userId);
        TaskDto taskDto = taskService.getTaskById(taskId);
        if (userDto == null || taskDto == null) {
            return ResponseEntity.badRequest().body("Invalid userId or taskId");
        }
        LikeReactionDto likeReactionDto = reactionService.createLikeReaction(userDto, taskDto);
        if (likeReactionDto == null) {
            return ResponseEntity.badRequest().body("Like reaction already exist");
        }
        return ResponseEntity.ok().body("Like reaction added");
    }

    @DeleteMapping("/like/{taskId}")
    public ResponseEntity<?> removeLikeReaction(
            @RequestHeader("ApiToken") final String jwtToken,
            @PathVariable Long taskId) {
        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);

        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        UserDto userDto = userService.getUserById(userId);
        TaskDto taskDto = taskService.getTaskById(taskId);
        if (userDto == null || taskDto == null) {
            return ResponseEntity.badRequest().body("Invalid userId or taskId");
        }
        reactionService.deleteLikeReaction(userDto, taskDto);
        return ResponseEntity.ok().body("Like reaction removed");
    }

    @PostMapping("/solve/{taskId}")
    public ResponseEntity<?> addSolveReaction(
            @RequestHeader("ApiToken") final String jwtToken,
            @PathVariable Long taskId) {
        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);

        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        UserDto userDto = userService.getUserById(userId);
        TaskDto taskDto = taskService.getTaskById(taskId);
        if (userDto == null || taskDto == null) {
            return ResponseEntity.badRequest().body("Invalid userId or taskId");
        }
        SolveReactionDto solveReactionDto = reactionService.createSolveReaction(userDto, taskDto);
        if (solveReactionDto == null) {
            return ResponseEntity.badRequest().body("Solve reaction already exist");
        }
        return ResponseEntity.ok().body("Solve reaction added");
    }

    @DeleteMapping("/solve/{taskId}")
    public ResponseEntity<?> removeSolveReaction(
            @RequestHeader("ApiToken") final String jwtToken,
            @PathVariable Long taskId) {
        JwtTokenDto token = jwtTokenService.getTokenByName(jwtToken);

        if (!jwtTokenProvider.validateToken(jwtToken) || token == null) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(jwtToken);
        UserDto userDto = userService.getUserById(userId);
        TaskDto taskDto = taskService.getTaskById(taskId);
        if (userDto == null || taskDto == null) {
            return ResponseEntity.badRequest().body("Invalid userId or taskId");
        }
        reactionService.deleteSolveReaction(userDto, taskDto);
        return ResponseEntity.ok().body("Solve reaction removed");
    }
    
}
