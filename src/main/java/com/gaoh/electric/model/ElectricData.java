package com.gaoh.electric.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
public class ElectricData {

    private Integer id;

    private Integer circuitId;

    private Double electricityA;
    private Double voltageA;
    private Double factorA;
    private Double activePowerA;
    private Double reactivePowerA;
    private Double apparentPowerA;

    private Double electricityB;
    private Double voltageB;
    private Double factorB;
    private Double activePowerB;
    private Double reactivePowerB;
        private Double apparentPowerB;

    private Double electricityC;
    private Double voltageC;
    private Double factorC;
    private Double activePowerC;
    private Double reactivePowerC;
    private Double apparentPowerC;

    private Timestamp time;

}
