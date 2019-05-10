package com.gaoh.electric.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DataController {

    @GetMapping("/data/template")
    public void exportTemplate(HttpServletResponse response) {

    }

    @PostMapping(value = "/data/import", consumes = "multipart/form-data")
    public void importData(@RequestParam("importFile")MultipartFile[] multipartFiles) {
        String filePath = "/tmp/import" + System.currentTimeMillis() + ".xlsx";
    }
}
