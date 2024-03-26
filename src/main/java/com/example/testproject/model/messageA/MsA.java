package com.example.testproject.model.messageA;

import com.example.testproject.enums.Lang;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MsA {
    private String msg;
    private Lang lng;
    private Coordinates coordinates;
}
