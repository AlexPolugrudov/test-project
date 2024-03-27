package com.example.testproject.model.messageA;

import com.example.testproject.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MsA implements Serializable {
    @NotBlank(message = "Message is required")
    private String msg;
    @NotNull(message = "Language is required")
    private Lang lng;
    @NotNull(message = "Coordinates is required")
    private Coordinates coordinates;
}
