package ru.noproblems.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HtmlController {
    @GetMapping(value = {
                    "/",
                    "/login",
                    "/signup",
                    "/account",
                    "/tasks",
                    "/support",
                    "/contacts",
                    "/task-adding",
    })
    public String index() {
        return "index"; // Возвращает имя HTML страницы без расширения
    }

    @GetMapping(value = {
                    "/tasks/{taskId}",
                    "tasks/{taskId}/edit"
    })
    public String index(@PathVariable Long taskId) {
        return "index"; // Возвращает имя HTML страницы без расширения
    }
}


