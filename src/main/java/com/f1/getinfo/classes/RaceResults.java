package com.f1.getinfo.classes;

import lombok.Data;

@Data
public class RaceResults {
    private final String position;
    private final String driverName;
    private final String driverSurname;
    private final String constructorName;
    private final String status;
    private final String time;
}

