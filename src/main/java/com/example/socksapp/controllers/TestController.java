package com.example.socksapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Тест", description = "Проверка работоспособности программы")
public class TestController {

    @GetMapping("/start")
    public String appStarted(){
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String appInfo(){
        return "Имя ученика: Егор. Название проекта: SocksApp. Дата создания проекта: 10.05.2023. Проект для склада интернет-магазина носков.";
    }

}
