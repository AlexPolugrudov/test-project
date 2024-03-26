package com.example.testproject.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Precipitation {

    private int type;
    private double amount;
    private int intensity;
}
