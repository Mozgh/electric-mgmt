package com.gaoh.electric.controller;

import com.gaoh.electric.dto.ResponseDto;
import com.gaoh.electric.model.SupplyCircuit;
import com.gaoh.electric.service.SupplyCircuitService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CircuitController {

    @Autowired
    private SupplyCircuitService supplyCircuitService;

    @PostMapping("/workshop/{wid}/circuit")
    public ResponseDto<Integer> insertCircuit(@PathVariable("wid") int wid, @RequestBody SupplyCircuit supplyCircuit) {
        try {
            if (StringUtil.isEmpty(supplyCircuit.getName())) {
                return ResponseDto.<Integer>builder().code(ResponseDto.FAILED_CODE).error("name can not be empty").build();
            }

            return ResponseDto.<Integer>builder().code(ResponseDto.SUCCESS_CODE).res(supplyCircuitService.insertCircuit(wid, supplyCircuit.getName())).build();
        }catch (Exception e) {
            return ResponseDto.<Integer>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @GetMapping("/workshop/{wid}/circuit")
    public ResponseDto<List<SupplyCircuit>> listCircuitByWid(@PathVariable("wid") int wid) {
        try {
            return ResponseDto.<List<SupplyCircuit>>builder().code(ResponseDto.SUCCESS_CODE).res(supplyCircuitService.listByWorkshop(wid)).build();
        }catch (Exception e) {
            return ResponseDto.<List<SupplyCircuit>>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @PutMapping("/circuit/{cid}")
    public ResponseDto<Void> updateName(@PathVariable("cid") int cid, @RequestBody String name) {
        try {
            supplyCircuitService.updateCircuitName(cid, name);
            return ResponseDto.<Void>builder().code(ResponseDto.SUCCESS_CODE).build();
        } catch (Exception e) {
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @DeleteMapping("/circuit/{cid}")
    public ResponseDto<Void> deleteCircuit(@PathVariable("cid") int cid) {
        try {
            supplyCircuitService.deleteByCid(cid);
            return ResponseDto.<Void>builder().code(ResponseDto.SUCCESS_CODE).build();
        } catch (Exception e) {
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

}
