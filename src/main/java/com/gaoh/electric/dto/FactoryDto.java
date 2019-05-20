package com.gaoh.electric.dto;

import com.gaoh.electric.model.Factory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FactoryDto {
    private int id;

    private String name;

    private String description;

    private List<WorkshopDto> workshops;

    public FactoryDto(Factory factory) {
        this.id = factory.getId();
        this.name = factory.getName();
        this.description = factory.getDescription();
    }

    public FactoryDto() {

    }
}
