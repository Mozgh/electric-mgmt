package com.gaoh.electric.controller;

import com.gaoh.electric.dto.FactoryDto;
import com.gaoh.electric.dto.ResponseDto;
import com.gaoh.electric.model.Factory;
import com.gaoh.electric.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FactoryController {

    @Autowired
    private FactoryService factoryService;

    @PostMapping("/factory")
    public ResponseDto<Integer> createFactory(@RequestBody Factory factory) {
        try {
            return ResponseDto.<Integer>builder().code(ResponseDto.SUCCESS_CODE).res(factoryService.createFactory(factory)).build();
        } catch (Exception e) {
            return ResponseDto.<Integer>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @PutMapping("/factory/{id}")
    public ResponseDto<Void> updateFactory(@PathVariable("id") Integer id, @RequestBody Factory factory) {
        try {
            factoryService.updateById(id, factory);
            return ResponseDto.<Void>builder().code(ResponseDto.SUCCESS_CODE).build();
        } catch (Exception e) {
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @DeleteMapping("/factory/{id}")
    public ResponseDto<Void> deleteFactory(@PathVariable("id") Integer id) {
        try {
            factoryService.deleteById(id);
            return ResponseDto.<Void>builder().code(ResponseDto.SUCCESS_CODE).build();
        }catch (Exception e) {
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @GetMapping("/factory")
    public ResponseDto<List<FactoryDto>> queryFactory(@RequestParam(value = "query", required = false) String query) {
        try {
            return ResponseDto.<List<FactoryDto>>builder().code(ResponseDto.SUCCESS_CODE).res(factoryService.listFactory(query)).build();
        } catch (Exception e) {
            return ResponseDto.<List<FactoryDto>>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

}
