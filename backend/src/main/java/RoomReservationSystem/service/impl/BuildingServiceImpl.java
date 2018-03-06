package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.repository.BuildingRepository;
import RoomReservationSystem.service.BuildingService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    
    @Override
    public void delete(Building building) {
        buildingRepository.delete(building);
    }

    @Override
    public void deleteByName(String name) {
        buildingRepository.deleteByName(name);
    }

    @Override
    public Building save(BuildingDTO building) {
        return buildingRepository.save(new Building(
                building.getName(),
                Collections.emptyList()
        ));
    }

    @Override
    public Building findByName(String name) {
        return buildingRepository.findByName(name);
    }
    
    @Override
    public Building findById(int id) {
        return buildingRepository.findById(id);
    }

    
    @Override
    public Iterable<Building> findAll() {
        return buildingRepository.findAll();
    }
    
    @Override
    public List<String> getNames() {
        Iterable<Building> buildings = this.findAll();
        List<String> buildingNames = new ArrayList<>();
        
        for (Building building: buildings) {
            buildingNames.add(building.getName());
        }
        
        return buildingNames;
    }  
    
}
