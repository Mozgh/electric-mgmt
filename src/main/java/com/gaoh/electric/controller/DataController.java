package com.gaoh.electric.controller;

import com.gaoh.electric.dto.ResponseDto;
import com.gaoh.electric.model.ElectricData;
import com.gaoh.electric.model.SupplyCircuit;
import com.gaoh.electric.service.DataService;
import com.gaoh.electric.service.SupplyCircuitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@Slf4j
public class DataController {

    @Autowired
    private DataService dataService;

    @Autowired
    private SupplyCircuitService supplyCircuitService;

    private static final String TEMPLATE_PATH = "tmp/export/数据录入模板.xls";

    @GetMapping("/data/template")
    public void exportTemplate(HttpServletResponse response) {
        File file = new File(TEMPLATE_PATH);
        try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis)) {
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=数据录入模板.xls");
            byte[] buffer = new byte[1024];
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            response.flushBuffer();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/circuit/{cid}/data/import", consumes = "multipart/form-data")
    public ResponseDto<Void> importData(@PathVariable(value = "cid") Integer cid, @RequestParam("importFile") MultipartFile[] multipartFiles) {
        SupplyCircuit supplyCircuit = supplyCircuitService.findById(cid);
        if (supplyCircuit == null) {
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error("circuit does not exist").build();
        }

        String filePath = "tmp/import/" + cid + System.currentTimeMillis() + ".xls";
        try (BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(new File(filePath)))) {
            MultipartFile multipartFile = multipartFiles[0];
            bf.write(multipartFile.getBytes());
            dataService.importData(cid, filePath);

            return ResponseDto.<Void>builder().code(ResponseDto.SUCCESS_CODE).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseDto.<Void>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @GetMapping("/circuit/{cid}/data/{phase}")
    public ResponseDto<List<ElectricData>> queryData(@PathVariable(value = "cid") Integer cid,
                                                     @PathVariable(value = "phase") String phase,
                                                     @RequestParam(value = "start") String start,
                                                     @RequestParam("end") String end) {
        try {
            return ResponseDto.<List<ElectricData>>builder().code(ResponseDto.SUCCESS_CODE).res(dataService.listElectricData(cid, phase, start, end)).build();
        } catch (Exception e) {
            return ResponseDto.<List<ElectricData>>builder().code(ResponseDto.FAILED_CODE).error(e.getMessage()).build();
        }
    }
}
