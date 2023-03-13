package com.shmyrov.testtask.controllers;

import com.shmyrov.testtask.services.ConverterService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    public final ConverterService converterService;

    public MainController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("/convert")
    @SneakyThrows
    public String convert(@RequestParam String type, @RequestParam String value) {
        return switch (type) {
            case "NumberToString" -> converterService.numberToString(Long.valueOf(value));
            case "StringToNumber" -> converterService.stringToNumber(value).toString();
            default -> throw new Exception("Do you now what you want?");
        };
    }
}
