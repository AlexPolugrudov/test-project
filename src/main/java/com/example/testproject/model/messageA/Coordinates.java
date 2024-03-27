package com.example.testproject.model.messageA;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates implements Serializable {
    @NotNull(message = "Latitude is required")
    private String latitude;
    @NotNull(message = "Longitude is required")
    private String longitude;
}
