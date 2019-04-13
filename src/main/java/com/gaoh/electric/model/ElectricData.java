package com.gaoh.electric.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ElectricData {

    private Integer id;

    private Integer cricuitId;

    private Double electrictyA;
    private Double voltageA;

    private Double electrictyB;
    private Double voltageB;

    private Double electrictyC;
    private Double voltageC;

    private Double activePower;

    private Double reactivePower;

    private Double apperantPower;

    private Double powerRactor;

    private Double electricEnergy;

    private Date time;


}
