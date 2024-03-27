package com.example.testproject.model.messageB;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MsB implements Serializable {

    private String txt;
    private LocalDateTime createdDt;
    private double currentTemp;
}
