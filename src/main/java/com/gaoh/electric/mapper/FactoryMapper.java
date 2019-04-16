package com.gaoh.electric.mapper;

import com.gaoh.electric.model.Factory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FactoryMapper {

    int insertFactory(Factory factory);

    void updateFactoryById(Factory factory);

    void deleteFactoryById(int id);

    List<Factory> listFactory(@Param("query") String query);

    Factory selectFactoryById(@Param("id") int id);

    List<Factory> selectFactoryByName(@Param("name") String name);
}
