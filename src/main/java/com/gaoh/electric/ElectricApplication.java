package com.gaoh.electric;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gaoh.electric.mapper")
public class ElectricApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectricApplication.class, args);
    }

}
