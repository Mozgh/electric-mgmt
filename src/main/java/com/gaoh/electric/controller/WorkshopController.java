package com.gaoh.electric.controller;

import com.gaoh.electric.dto.ResponseDto;
import com.gaoh.electric.dto.WorkshopDto;
import com.gaoh.electric.service.WorkshopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class WorkshopController {

    @Autowired
    private WorkshopService workshopService;

    @PostMapping("/factory/{fid}/workshop")
    public ResponseDto<Integer> createWorkshop(@PathVariable("fid") int fid, @RequestBody WorkshopDto workshopDto) {
        try {
            return ResponseDto.<Integer>builder().code(ResponseDto.SUCCESS_CODE).res(workshopService.createWorkshop(fid, workshopDto)).build();
        } catch (Exception e) {
            return ResponseDto.<Integer>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @PutMapping("/factory/{fid}/workshop/{wid}")
    public ResponseDto<Void> updateWorkshop(@PathVariable("fid") Integer fid,
                                            @PathVariable("wid") Integer wid,
                                            @RequestBody WorkshopDto workshopDto) {
        try {
            workshopService.update(fid, wid, workshopDto);
            return ResponseDto.<Void>builder().code(ResponseDto.SUCCESS_CODE).build();
        } catch (Exception e) {
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @DeleteMapping("/factory/{fid}/workshop/{wid}")
    public ResponseDto<Void> deleteWorkshop(@PathVariable("fid") int fid, @PathVariable("wid") int wid) {
        try {
            workshopService.delete(fid, wid);
            return ResponseDto.<Void>builder().code(ResponseDto.SUCCESS_CODE).build();
        } catch (Exception e) {
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }
}
