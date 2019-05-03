package com.gaoh.electric.mapper;

import com.gaoh.electric.model.Workshop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkshopMapper {

    int insertWorkshop(Workshop workshop);

    void updateWorkshopById(Workshop workshop);

    void deleteWorkshopById(@Param("id") int id);

    Workshop selectByWid(@Param("wid") int wid);

    Workshop selectByFidAndWid(@Param("fid") int fid, @Param("wid") int wid);

    List<Workshop> selectByFactoryId(@Param("factoryId") int factoryId);

}
