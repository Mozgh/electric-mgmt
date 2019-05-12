package com.gaoh.electric.service;

import com.gaoh.electric.dto.WorkshopDto;
import com.gaoh.electric.mapper.FactoryMapper;
import com.gaoh.electric.mapper.WorkshopMapper;
import com.gaoh.electric.model.Factory;
import com.gaoh.electric.model.Workshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopMapper workshopMapper;

    @Autowired
    private FactoryMapper factoryMapper;

    @Autowired
    private SupplyCircuitService supplyCircuitService;

    public int createWorkshop(int factoryId, WorkshopDto workshopDto) throws Exception {
        Factory factory = factoryMapper.selectFactoryById(factoryId);

        if (factory == null) {
            throw new Exception("The factory does not exist");
        }

        Workshop workshop = new Workshop();
        workshop.setFactoryId(factoryId);
        workshop.setName(workshopDto.getName());
        workshop.setDescription(workshopDto.getDescription());
        workshop.setStatus(workshopDto.getStatus());

        workshopMapper.insertWorkshop(workshop);

        return workshop.getId();
    }

    public void update(int fid, int wid, WorkshopDto workshopDto) throws Exception {
        Workshop workshop = workshopMapper.selectByFidAndWid(fid, wid);
        if (workshop == null) {
            throw new Exception("The workshop does not exist");
        }

        workshop.setName(workshopDto.getName());
        workshop.setDescription(workshopDto.getDescription());
        workshop.setStatus(workshopDto.getStatus());

        workshopMapper.updateWorkshopById(workshop);
    }

    public void delete(int factoryId, int workshopId) throws Exception {
        Workshop workshop = workshopMapper.selectByFidAndWid(factoryId, workshopId);

        if (workshop == null) {
            throw new Exception("The workshop does not exist");
        }
        workshopMapper.deleteWorkshopById(workshopId);
    }

    /**
     * delete batch while factory was deleted
     * @param factoryId
     * @throws Exception
     */
    public void deleteByFactory(int factoryId) throws Exception {

    }

    public List<WorkshopDto> selectByFactoryId(int factoryId) throws Exception {
        List<WorkshopDto> workshopDtos = new ArrayList<>();
        if (factoryMapper.selectFactoryById(factoryId) == null) {
            throw new Exception("The factory does not exist");
        }
        List<Workshop> workshops = workshopMapper.selectByFactoryId(factoryId);
        if (CollectionUtils.isEmpty(workshops)) {
            return new ArrayList<>();
        }
        for (Workshop workshop : workshops) {
            WorkshopDto workshopDto = new WorkshopDto(workshop);
            workshopDto.setCircuits(supplyCircuitService.listByWorkshop(workshop.getId()));
            workshopDtos.add(workshopDto);
        }
        return workshopDtos;
    }

    public Workshop selectById(int id) {
        return workshopMapper.selectByWid(id);
    }
}
