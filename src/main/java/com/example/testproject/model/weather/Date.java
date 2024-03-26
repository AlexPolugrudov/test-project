package com.example.testproject.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Date {
    private String Utc;
    private int unix;
    private String local;
    private int timeZoneOffset;
}
