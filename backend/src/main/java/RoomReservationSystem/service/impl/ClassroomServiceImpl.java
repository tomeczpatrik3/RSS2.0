package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.ClassroomDTO;
import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTO;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.repository.ClassroomRepository;
import RoomReservationSystem.service.ClassroomService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static RoomReservationSystem.model.Classroom.toClassroom;
import RoomReservationSystem.service.BuildingService;

@Service
public class ClassroomServiceImpl implements ClassroomService{
    @Autowired
    private ClassroomRepository classroomRepository;
    
    @Autowired
    private BuildingService buildingService;
    
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
    public Classroom save(ClassroomDTO classroomDTO){       
        return classroomRepository.save(toClassroom(
              classroomDTO,
              buildingService.findByName(classroomDTO.getBuilding())
        ));
    }
    
    @Override
    public List<ClassroomDTO> findAll(){
        List<Classroom> classrooms = classroomRepository.findAll();
        List<ClassroomDTO> crDtos = new ArrayList<>();
        
        for (Classroom classroom: classrooms) {
            crDtos.add( toClassroomDTO(classroom) );
        }
        
        return crDtos;
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
        List<ClassroomDTO> rooms = this.findAll();
        List<String> roomNames = new ArrayList<>();
        
        for (ClassroomDTO room: rooms) {
            roomNames.add(room.getName());
        }
        
        return roomNames;
    }  
}
