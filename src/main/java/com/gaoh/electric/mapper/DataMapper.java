package com.gaoh.electric.mapper;

import com.gaoh.electric.model.ElectricData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface DataMapper {

    int saveDataBatch(List<ElectricData> datas);

    List<ElectricData> selectDataByCid(@Param("cid") int cid, @Param("start")Date start, @Param("end") Date end);

}
