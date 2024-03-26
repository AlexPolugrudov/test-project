package com.example.testproject.model.messageB;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MsB {

    private String txt;
    private LocalDateTime createdDt;
    private double currenTemp;
}
