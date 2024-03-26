package com.example.testproject.model.messageA;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    private String latitude;
    private String longitude;
}
