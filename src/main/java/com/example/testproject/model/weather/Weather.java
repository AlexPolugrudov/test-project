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
public class Weather implements Serializable {

    private String kind;
    private Date date;
    private Temperature temperature;
    private Description description;
    private Humidity humidity;
    private Pressure pressure;
    private Cloudinnes cloudinnes;
    private Storm storm;
    private Precipitation precipitation;
    private int phenomenon;
    private String icon;
    private int gm;
}
