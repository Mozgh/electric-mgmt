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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
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
                    calculate(data);
                    datas.add(data);
                }
                if (datas.size() >= 1000) {
                    dataMapper.saveDataBatch(datas);
                    datas.clear();
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private ElectricData buildDataInRow(Row row) {
        try {
            ElectricData data = new ElectricData();
            data.setVoltageA(row.getCell(1).getNumericCellValue());
            data.setElectricityA(row.getCell(3).getNumericCellValue());
            data.setFactorA(row.getCell(5).getNumericCellValue());
            data.setVoltageB(row.getCell(7).getNumericCellValue());
            data.setElectricityB(row.getCell(9).getNumericCellValue());
            data.setFactorB(row.getCell(11).getNumericCellValue());
            data.setVoltageC(row.getCell(13).getNumericCellValue());
            data.setElectricityC(row.getCell(15).getNumericCellValue());
            data.setFactorC(row.getCell(17).getNumericCellValue());
            data.setTime(new Timestamp(formatDate(row.getCell(0).getStringCellValue())));
            return data;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    public List<ElectricData> listElectricData(int cid, String phase, String start, String end) throws Exception {
        if(supplyCircuitService.findById(cid) == null) {
            throw new Exception("circuit does not exist!");
        }
        return dataMapper.selectDataByCid(cid, start, end);
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
        data.setActivePowerA(calcActivePower(data.getElectricityA(), data.getVoltageA(), data.getFactorA()));
        data.setReactivePowerA(calcReactivePower(data.getElectricityA(), data.getVoltageA(), data.getFactorA()));
        data.setApparentPowerA(calcApparentPower(data.getElectricityA(), data.getVoltageA()));

        data.setActivePowerB(calcActivePower(data.getElectricityB(), data.getVoltageB(), data.getFactorB()));
        data.setReactivePowerB(calcReactivePower(data.getElectricityB(), data.getVoltageB(), data.getFactorB()));
        data.setApparentPowerB(calcApparentPower(data.getElectricityB(), data.getVoltageB()));

        data.setActivePowerC(calcActivePower(data.getElectricityC(), data.getVoltageC(), data.getFactorC()));
        data.setReactivePowerC(calcReactivePower(data.getElectricityC(), data.getVoltageC(), data.getFactorC()));
        data.setApparentPowerC(calcApparentPower(data.getElectricityC(), data.getVoltageC()));
    }

    private double calcActivePower(Double electricity, Double voltage, Double factor) {
        if (electricity == null || voltage == null || factor == null) {
            return 0;
        } else {
            return new BigDecimal(electricity * voltage * Math.cos(factor) / 1000).setScale(4, RoundingMode.HALF_UP).doubleValue();
        }
    }

    private double calcReactivePower(Double electricity, Double voltage, Double factor) {
        if (electricity == null || voltage == null || factor == null) {
            return 0;
        } else {
            return new BigDecimal(electricity * voltage * Math.sin(factor) / 1000).setScale(4, RoundingMode.HALF_UP).doubleValue();
        }
    }

    private double calcApparentPower(Double electricity, Double voltage) {
        if (electricity == null || voltage == null) {
            return 0;
        } else {
            return new BigDecimal(electricity * voltage / 1000).setScale(4, RoundingMode.HALF_UP).doubleValue();
        }
    }

}
