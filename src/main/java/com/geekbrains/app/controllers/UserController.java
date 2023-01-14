package com.geekbrains.app.controllers;

import com.geekbrains.app.services.UserService;
import com.geekbrains.app.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    //досуп без регистрации
    @GetMapping("/home")
    public String homePage() {
        return "Добро пожаловать на домашнюю страницу 'Постграмм'";
    }

    @GetMapping("/auth_page/")
    public String authPage() {
        return "авторизация";
    }

    //доступ авторизированным пользователям
    @GetMapping("/profile")
    public String profilePage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Вы вошли как: " + user.getUsername() + " Ваша почта: " + user.getEmail();
    }

    //доступ авторизированным пользователям с WRITE_ACCESS
    @GetMapping("/write")
    public String writePage() {
        return "У вас открыт доступ на написание постов";
    }

    //доступ авторизированным пользователям с READ_ACCESS
    @GetMapping("/read")
    public String readPage() {
        return "У вас открыт доступ на чтение постов";
    }

    //доступ авторизированным пользователям с DELETE_ACCESS
    @GetMapping("/admin")
    public String adminPage() {
        return "Панель управления Администратора: Имеется доступ на удаление постов";
    }
}
