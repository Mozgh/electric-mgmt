package com.gaoh.electric.dto;

import com.gaoh.electric.model.SupplyCircuit;
import com.gaoh.electric.model.Workshop;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkshopDto {

    private String name;

    private String description;

    private int status;

    private List<SupplyCircuit> circuits;

    public WorkshopDto(Workshop workshop) {
        this.name = workshop.getName();
        this.description = workshop.getDescription();
    }

    public WorkshopDto() {

    }
}
