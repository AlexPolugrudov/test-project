package com.example.testproject.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Date implements Serializable {
    private String Utc;
    private int unix;
    private String local;
    private int timeZoneOffset;
}
