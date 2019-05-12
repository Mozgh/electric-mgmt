package com.gaoh.electric.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class ElectricData {

    private Integer id;

    private Integer circuitId;

    private Double electricityA;
    private Double voltageA;

    private Double electricityB;
    private Double voltageB;

    private Double electricityC;
    private Double voltageC;

    private Double activePower;

    private Double reactivePower;

    private Double apparentPower;

    private Double powerFactor;

    private Double electricEnergy;

    private Date time;

}
