package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.repository.ClassroomRepository;
import RoomReservationSystem.service.ClassroomService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl implements ClassroomService{
    @Autowired
    private ClassroomRepository classroomRepository;
    
    @Autowired
    private BuildingServiceImpl buildingService;
    
    @Override
    public void delete(Classroom classroom){
        classroomRepository.delete(classroom);
    }
    
    @Override
    public void deleteByName(String name){
        classroomRepository.deleteByName(name);
    }
    
    @Override
    public void deleteAll(){
        classroomRepository.deleteAll();
    }
    
    @Override
    public Classroom save(Classroom classroom){       
        return classroomRepository.save(classroom);
    }
    
    @Override
    public Iterable<Classroom> findAll(){
        return classroomRepository.findAll();
    }
    
    @Override
    public Classroom findById(int id) {
        return this.classroomRepository.findById(id);
    }
    
    @Override
    public Classroom findByName(String name){
        return classroomRepository.findByName(name);
    }
    
    @Override
    public List<String> getNamesByBuilding(String buildingName){
        List<Classroom> rooms = findByBuilding(buildingService.findByName(buildingName));
        List<String> roomNames = new ArrayList<>();
        
        for (Classroom room: rooms) {
            roomNames.add(room.getName());
        }
        
        return roomNames;
    }
    
    @Override
    public List<Classroom> findByBuilding(Building building) {
        return classroomRepository.findByBuilding(building);
    }

    @Override
    public List<Classroom> findByHasPc(boolean hasPC) {
        return classroomRepository.findByHasPc(hasPC);
    }

    @Override
    public List<Classroom> findByHasProjector(boolean hasProjector){
        return classroomRepository.findByHasProjector(hasProjector);
    }

    @Override
    public List<Classroom> findByChairsLessThan(int num){
        return classroomRepository.findByChairsLessThan(num);
    }

    @Override
    public List<Classroom> findByChairsGreaterThan(int num){
        return classroomRepository.findByChairsGreaterThan(num);
    }

    @Override
    public List<Classroom> findByChairsBetween(int from, int to){
        return classroomRepository.findByChairsBetween(from, to);
    }
    
    @Override
    public List<String> getNames() {
        Iterable<Classroom> rooms = this.findAll();
        List<String> roomNames = new ArrayList<>();
        
        for (Classroom room: rooms) {
            roomNames.add(room.getName());
        }
        
        return roomNames;
    }  
}
