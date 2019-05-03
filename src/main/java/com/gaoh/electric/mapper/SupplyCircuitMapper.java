package com.gaoh.electric.mapper;

import com.gaoh.electric.model.SupplyCircuit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SupplyCircuitMapper {

    SupplyCircuit selectByCid(@Param("cid") int cid);

    List<SupplyCircuit> selectByWorkshopId(@Param("workshopId") int workshopId);

    List<SupplyCircuit> selectByWorkshopIdAndName(@Param("workshopId") int workshopId, @Param("name") String name);

    int insertCircuit(SupplyCircuit supplyCircuit);

    void updateName(@Param("name") String name, @Param("circuitId") int circuitId);

    void deleteByCid(@Param("cid") int cid);

    void deleteByWorkshopId(@Param("workshopId") int workshopId);
}
