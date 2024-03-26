package com.example.testproject.controller;

import com.example.testproject.model.messageA.MsA;
import com.example.testproject.service.AdapterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "main_controller_methods")
@RestController
@RequiredArgsConstructor
public class AdapterController {

    private final AdapterService adapterService;

    @Operation(
            summary = "Получает сообщение"
    )
    @PostMapping("/process")
    public ResponseEntity<String> processMessage(@RequestBody MsA msA) {
        var message = adapterService.processMessage(msA);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
