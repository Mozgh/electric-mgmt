package com.gaoh.electric.service;

import com.gaoh.electric.mapper.DataMapper;
import com.gaoh.electric.model.ElectricData;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataService {

    @Autowired
    private SupplyCircuitService supplyCircuitService;

    @Autowired
    private DataMapper dataMapper;

    public void importData(int circuitId, String filePath) throws Exception {
        if (supplyCircuitService.findById(circuitId) == null) {
            throw new Exception("circuit does not exist!");
        }

        try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            List<ElectricData> datas = new ArrayList<>();

            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                ElectricData data = buildDataInRow(sheet.getRow(i));
                if (data != null) {
                    data.setCircuitId(circuitId);
                    datas.add(data);
                }
            }

            dataMapper.saveDataBatch(datas);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private ElectricData buildDataInRow(Row row) {
        try {
            ElectricData data = new ElectricData();
            data.setVoltageA(row.getCell(1).getNumericCellValue());
            data.setElectricityA(row.getCell(3).getNumericCellValue());
            data.setVoltageB(row.getCell(5).getNumericCellValue());
            data.setElectricityB(row.getCell(7).getNumericCellValue());
            data.setVoltageC(row.getCell(9).getNumericCellValue());
            data.setElectricityC(row.getCell(11).getNumericCellValue());
            data.setTime(new Date(formatDate(row.getCell(0).getStringCellValue())));
            return data;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    public List<ElectricData> listElectricData(int cid, long start, long end) throws Exception {
        if(supplyCircuitService.findById(cid) == null) {
            throw new Exception("circuit does not exist!");
        }
        return dataMapper.selectDataByCid(cid, new Date(start), new Date(end));
    }

    private Long formatDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        try {
            return sdf.parse(date).getTime();
        } catch (ParseException e) {
            log.error("time format error ..." + e.getMessage());
            return null;
        }
    }

    private void calculate(ElectricData data) {

    }

}
