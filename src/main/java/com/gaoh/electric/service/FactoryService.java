package com.gaoh.electric.service;

import com.gaoh.electric.dto.FactoryDto;
import com.gaoh.electric.mapper.FactoryMapper;
import com.gaoh.electric.mapper.WorkshopMapper;
import com.gaoh.electric.model.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryService {

    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private WorkshopService workshopService;

    public int createFactory(Factory factory) throws Exception {
        if (factory.getName() == null || "".equals(factory.getName())) {
            throw new Exception("Factory name can not be null!");
        }
        List<Factory> factorys = factoryMapper.selectFactoryByName(factory.getName());
        if (factorys != null && !factorys.isEmpty()) {
            throw new Exception("The factory already exists!");
        }
        factoryMapper.insertFactory(factory);
        return factory.getId();
    }

    public void updateById(Integer id, Factory factory) throws Exception {
        if (id == null) {
            throw new Exception("Id can not be null!");
        }

        Factory factory1 = factoryMapper.selectFactoryById(id);
        if (factory1 == null) {
            throw new Exception("The factory does not exist");
        }
        factory.setId(id);
        factoryMapper.updateFactoryById(factory);
    }

    public void deleteById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Id can not be null!");
        }
        Factory factory = factoryMapper.selectFactoryById(id);
        if (factory == null) {
            throw new Exception("The factory does not exist!");
        }

        factoryMapper.deleteFactoryById(id);
    }

    public List<FactoryDto> listFactory(String query) throws Exception {
        List<FactoryDto> factoryDtos = new ArrayList<>();
        if (query == null) {
            query = "";
        }
        List<Factory> factories = factoryMapper.listFactory("%" + query + "%");
        if (CollectionUtils.isEmpty(factories)) {
            return new ArrayList<>();
        }
        for (Factory factory : factories) {
            FactoryDto factoryDto = new FactoryDto(factory);
            factoryDto.setWorkshops(workshopService.selectByFactoryId(factory.getId()));
            factoryDtos.add(factoryDto);
        }
        return factoryDtos;
    }
}
