package com.shmyrov.testtask.controllers;

import com.shmyrov.testtask.database.ConvertLog;
import com.shmyrov.testtask.database.ConvertLogRepository;
import com.shmyrov.testtask.exceptions.BadParametersToConvert;
import com.shmyrov.testtask.services.ConverterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    final ConverterService converterService;

    final ConvertLogRepository convertLogRepository;

    public MainController(ConverterService converterService, ConvertLogRepository convertLogRepository) {
        this.converterService = converterService;
        this.convertLogRepository = convertLogRepository;
    }

    @GetMapping("/convert")
    public String convert(@RequestParam String type, @RequestParam String value, Principal principal) throws BadParametersToConvert {
        ConvertLog convertLog = new ConvertLog();
        convertLog.setType(type);
        convertLog.setInnerValue(value);
        convertLog.setUserName(principal.getName());

        String convertedValue;
        try {
            switch (type) {
                case "NumberToString":
                    convertedValue = converterService.numberToString(Long.valueOf(value));
                    convertLog.setOuterValue(convertedValue);
                    return convertedValue;
                case "StringToNumber":
                    convertedValue = converterService.stringToNumber(value).toString();
                    convertLog.setOuterValue(convertedValue);
                    return convertedValue;
                default:
                    throw new BadParametersToConvert();
            }
        } finally {
            convertLogRepository.save(convertLog);
        }

    }
}
