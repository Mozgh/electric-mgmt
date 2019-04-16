package com.gaoh.electric.service;

import com.gaoh.electric.mapper.FactoryMapper;
import com.gaoh.electric.model.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryService {

    @Autowired
    private FactoryMapper factoryMapper;

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

    public List<Factory> listFactory(String query) {
        if (query == null) {
            query = "";
        }
        return factoryMapper.listFactory("%" + query + "%");
    }
}
