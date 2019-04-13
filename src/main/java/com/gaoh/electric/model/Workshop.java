package com.gaoh.electric.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Workshop {

    private Integer id;

    private Integer factoryId;

    private String name;

    private String description;

    private Integer status;
}
