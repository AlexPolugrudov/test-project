package com.example.testproject.controller;

import com.example.testproject.model.messageA.MsA;
import com.example.testproject.service.AdapterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "main_controller_methods")
@Validated
@RestController
@RequiredArgsConstructor
public class AdapterController {

    private final AdapterService adapterService;

    @Operation(
            summary = "Получает сообщение",
            description = "Получает сообщение из сервиса А, обращается к стороннему сервису погоды и отправляет полученные данные в сервис B"
    )
    @PostMapping("/process")
    public ResponseEntity<String> processMessage(@RequestBody @Valid MsA msA) {
        var message = adapterService.processMessage(msA);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
