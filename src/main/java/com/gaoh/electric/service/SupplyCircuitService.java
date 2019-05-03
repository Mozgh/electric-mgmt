package com.gaoh.electric.service;

import com.gaoh.electric.mapper.SupplyCircuitMapper;
import com.gaoh.electric.mapper.WorkshopMapper;
import com.gaoh.electric.model.SupplyCircuit;
import com.gaoh.electric.model.Workshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SupplyCircuitService {

    @Autowired
    private SupplyCircuitMapper supplyCircuitMapper;

    @Autowired
    private WorkshopMapper workshopMapper;

    public int insertCircuit(int workshopId, String name) throws Exception {
        Workshop workshop = workshopMapper.selectByWid(workshopId);
        if (workshop == null) {
            throw new Exception("workshop does not exist");
        }
        SupplyCircuit circuit = new SupplyCircuit();
        circuit.setWorkshopId(workshopId);
        circuit.setName(name);

        supplyCircuitMapper.insertCircuit(circuit);
        return circuit.getId();
    }

    public void updateCircuitName(int cid, String name) throws Exception {
        SupplyCircuit circuit = supplyCircuitMapper.selectByCid(cid);
        if (circuit == null) {
            throw new Exception("circuit does not exist");
        }
        if (circuit.getName().equals(name)) {
            return;
        }

        List<SupplyCircuit> circuits = supplyCircuitMapper.selectByWorkshopIdAndName(circuit.getWorkshopId(), name);
        if (!CollectionUtils.isEmpty(circuits)) {
            throw new Exception("circuit name already exists");
        }
        supplyCircuitMapper.updateName(name, cid);
    }

    public List<SupplyCircuit> listByWorkshop(int wid) throws Exception {
        Workshop workshop = workshopMapper.selectByWid(wid);
        if (workshop == null) {
            throw new Exception("workshop does not exist");
        }

        return supplyCircuitMapper.selectByWorkshopId(wid);
    }

    public void deleteByCid(int cid) throws Exception {
        SupplyCircuit circuit = supplyCircuitMapper.selectByCid(cid);
        if (circuit == null) {
            throw new Exception("circuit does not exist");
        }

        supplyCircuitMapper.deleteByCid(cid);
    }

    public void deleteByWorkshop(int wid) throws Exception {
        Workshop workshop = workshopMapper.selectByWid(wid);

        if (workshop == null) {
            throw new Exception("workshop does not exist");
        }
        supplyCircuitMapper.deleteByWorkshopId(wid);
    }

}
