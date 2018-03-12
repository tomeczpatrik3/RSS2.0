package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Building;
import RoomReservationSystem.model.Classroom;
import RoomReservationSystem.repository.ClassroomRepository;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.BuildingService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Az osztálytermekkel kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class ClassroomServiceImpl implements ClassroomService{
    @Autowired
    private ClassroomRepository classroomRepository;
    
    @Autowired
    private BuildingService buildingService;
    
    /**
     * Az osztályterem törlését megvalósító függvény
     * @param   classroom   A törlendő osztályterem
     */
    @Override
    public void delete(Classroom classroom){
        classroomRepository.delete(classroom);
    }
    
    /**
     * Az osztályterem név alapján történő törlését megvalósító függvény
     * @param   name   A törlendő osztályterem neve
     */
    @Override
    public void deleteByName(String name){
        classroomRepository.deleteByName(name);
    }
    
    /**
     * Az osztályterem mentését megvalósító függvény
     * @param   classroom       A menteni kívánt terem
     * @return                  A mentett terem
     */
    @Override
    public Classroom save(Classroom classroom){       
        return classroomRepository.save(classroom);
    }
    
    /**
     * Az összes osztályterem lekérdezését megvalósító függvény
     * @return  Az osztálytermek egy listában
     */
    @Override
    public List<Classroom> findAll(){
        return classroomRepository.findAll();
    }
    
    /**
     * A terem id alapján történő keresését megvalósító függvény
     * @param   id  A terem id-ja
     * @return      A terem ha létezik, null egyébként
     */
    @Override
    public Classroom findById(int id) {
        return classroomRepository.findById(id);
    }
    
    /**
     * A terem név alapján történő keresését megvalósító függvény
     * @param   name    A terem neve
     * @return          Ezzel a névvel rendelkező termek listája
     */    
    @Override
    public List<Classroom> findByName(String name){
        return classroomRepository.findByName(name);
    }
    
    /**
     * Egy adott épülethez tartozó tantermek neveinek listázását megvalósító függvény
     * @param   buildingName    Az épület neve
     * @return                  A tanteremek nevei egy listában
     */
    @Override
    public List<String> getNamesByBuilding(String buildingName){
        List<Classroom> rooms = classroomRepository.findByBuilding(buildingService.findByName(buildingName));
        List<String> roomNames = new ArrayList<>();
        
        for (Classroom room: rooms) {
            roomNames.add(room.getName());
        }
        
        return roomNames;
    }
    
    /**
     * A tantermek épület alapján történő keresését megvalósító függvény
     * @param   building    Az épület
     * @return              Az adott épülethez tartozó tantermek egy listában
     */
    @Override
    public List<Classroom> findByBuilding(Building building) {
        return classroomRepository.findByBuilding(building);
    }

    /**
     * A tantermek PC alapján történő keresését megvalósító függvény
     * @param   hasPC   Logikai érték: true - van / false - nincs
     * @return          A feltételnek eleget tevő tantermek egy listában
     */
    @Override
    public List<Classroom> findByHasPc(boolean hasPC) {
        return classroomRepository.findByHasPc(hasPC);
    }
    
    /**
     * A tantermek Projektor alapján történő keresését megvalósító függvény
     * @param   hasProjector    Logikai érték: true - van / false - nincs
     * @return                  A feltételnek eleget tevő tantermek egy listában
     */
    @Override
    public List<Classroom> findByHasProjector(boolean hasProjector){
        return classroomRepository.findByHasProjector(hasProjector);
    }

    /**
     * A tantermek székek alapján történő keresését megvalósító függvény
     * @param   num   A székek száma
     * @return        A kisebb számú székkel rendelkező tantermek egy listában
     */
    @Override
    public List<Classroom> findByChairsLessThan(int num){
        return classroomRepository.findByChairsLessThan(num);
    }

    /**
     * A tantermek székek alapján történő keresését megvalósító függvény
     * @param   num   A székek száma
     * @return        A nagyobb számú székkel rendelkező tantermek egy listában
     */
    @Override
    public List<Classroom> findByChairsGreaterThan(int num){
        return classroomRepository.findByChairsGreaterThan(num);
    }

    /**
     * A tantermek székek alapján történő keresését megvalósító függvény
     * @param   from    Minimális szék szám
     * @param   to      Maximális szék szám
     * @return          A feltételnek eleget tevő tantermek egy listában
     */
    @Override
    public List<Classroom> findByChairsBetween(int from, int to){
        return classroomRepository.findByChairsBetween(from, to);
    }
    
    /**
     * A tanterem név és épület alapján történő keresését megvalósító függvény
     * @param   name            A tanterem neve
     * @param   buildingName    Az épület neve
     * @return                  A tanterem ha van, null egyébként
     */
    @Override
    public Classroom findByNameAndBuildingName(String name, String buildingName) {
        Building building = buildingService.findByName(buildingName);
        return classroomRepository.findByNameAndBuilding(name, building);
    }
    
    /**
     * A tantermek neveinek lekérdezését megvalósító függvény
     * @return  A tantermek nevei egy listában
     */
    @Override
    public List<String> getNames() {
        List<Classroom> rooms = this.findAll();
        List<String> roomNames = new ArrayList<>();
        
        for (Classroom room: rooms) {
            roomNames.add(room.getName());
        }
        
        return roomNames;
    }  
}
