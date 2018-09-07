package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.ClassroomDTO;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassroomAlredyExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
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
 *
 * @author Tomecz Patrik
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private BuildingService buildingService;

    /**
     * Az osztályterem név alapján történő törlését megvalósító függvény
     *
     * @param name A törlendő osztályterem neve
     * @param buildingName Az épület neve
     * @throws RoomReservationSystem.exception.BuildingNotExistsException
     * @throws RoomReservationSystem.exception.ClassroomNotExistsException
     */
    @Override
    public void deleteByNameAndBuildingName(String name, String buildingName) throws BuildingNotExistsException, ClassroomNotExistsException {
        Building building = buildingService.findByName(buildingName);
        if (classroomRepository.findByNameAndBuilding(name, building) != null) {
            classroomRepository.deleteByNameAndBuilding(name, building);
        } else {
            throw new ClassroomNotExistsException(String.format("Ehhez az épülethez (%s) nem tartozik ilyen nevű (%s) tanterem!", buildingName, name));
        }
    }

    /**
     * Az osztályterem mentését megvalósító függvény
     *
     * @param classroom A menteni kívánt terem
     * @return A mentett terem
     * @throws RoomReservationSystem.exception.ClassroomAlredyExistsException
     */
    @Override
    public Classroom save(Classroom classroom) throws ClassroomAlredyExistsException {
        if (classroomRepository.findByNameAndBuilding(classroom.getName(), classroom.getBuilding()) == null) {
            return classroomRepository.save(classroom);
        } else {
            throw new ClassroomAlredyExistsException(String.format("Ehhez az épülethez (%s) már tartozik ilyen nevű (%s) tanterem!", classroom.getBuilding().getName(), classroom.getName()));
        }
    }

    /**
     * Az összes osztályterem lekérdezését megvalósító függvény
     *
     * @return Az osztálytermek egy listában
     */
    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    /**
     * A terem id alapján történő keresését megvalósító függvény
     *
     * @param id A terem id-ja
     * @return A terem ha létezik, null egyébként
     * @throws RoomReservationSystem.exception.ClassroomNotExistsException
     */
    @Override
    public Classroom findById(int id) throws ClassroomNotExistsException {
        Classroom found = classroomRepository.findById(id);
        if (found != null) {
            return found;
        } else {
            throw new ClassroomNotExistsException(String.format("Ilyen azonosítóval (%d) rendelkező tanterem nem létezik!", id));
        }
    }

    /**
     * A terem név alapján történő keresését megvalósító függvény
     *
     * @param name A terem neve
     * @return Ezzel a névvel rendelkező termek listája
     */
    @Override
    public List<Classroom> findByName(String name) {
        return classroomRepository.findByName(name);
    }

    /**
     * Egy adott épülethez tartozó tantermek listázását megvalósító függvény
     *
     * @param buildingName Az épület neve
     * @return A tanteremek egy listában
     * @throws RoomReservationSystem.exception.BuildingNotExistsException
     */
    @Override
    public List<Classroom> findByBuildingName(String buildingName) throws BuildingNotExistsException {
        return classroomRepository.findByBuilding(buildingService.findByName(buildingName));
    }

    /**
     * A tantermek épület alapján történő keresését megvalósító függvény
     *
     * @param building Az épület
     * @return Az adott épülethez tartozó tantermek egy listában
     */
    @Override
    public List<Classroom> findByBuilding(Building building) {
        return classroomRepository.findByBuilding(building);
    }

    /**
     * A tantermek PC alapján történő keresését megvalósító függvény
     *
     * @param hasPC Logikai érték: true - van / false - nincs
     * @return A feltételnek eleget tevő tantermek egy listában
     */
    @Override
    public List<Classroom> findByHasPc(boolean hasPC) {
        return classroomRepository.findByHasPc(hasPC);
    }

    /**
     * A tantermek Projektor alapján történő keresését megvalósító függvény
     *
     * @param hasProjector Logikai érték: true - van / false - nincs
     * @return A feltételnek eleget tevő tantermek egy listában
     */
    @Override
    public List<Classroom> findByHasProjector(boolean hasProjector) {
        return classroomRepository.findByHasProjector(hasProjector);
    }

    /**
     * A tantermek székek alapján történő keresését megvalósító függvény
     *
     * @param num A székek száma
     * @return A kisebb számú székkel rendelkező tantermek egy listában
     */
    @Override
    public List<Classroom> findByChairsLessThan(int num) {
        return classroomRepository.findByChairsLessThan(num);
    }

    /**
     * A tantermek székek alapján történő keresését megvalósító függvény
     *
     * @param num A székek száma
     * @return A nagyobb számú székkel rendelkező tantermek egy listában
     */
    @Override
    public List<Classroom> findByChairsGreaterThan(int num) {
        return classroomRepository.findByChairsGreaterThan(num);
    }

    /**
     * A tantermek székek alapján történő keresését megvalósító függvény
     *
     * @param from Minimális szék szám
     * @param to Maximális szék szám
     * @return A feltételnek eleget tevő tantermek egy listában
     */
    @Override
    public List<Classroom> findByChairsBetween(int from, int to) {
        return classroomRepository.findByChairsBetween(from, to);
    }

    /**
     * A tanterem név és épület alapján történő keresését megvalósító függvény
     *
     * @param name A tanterem neve
     * @param buildingName Az épület neve
     * @return A tanterem ha van, null egyébként
     * @throws RoomReservationSystem.exception.BuildingNotExistsException
     * @throws RoomReservationSystem.exception.ClassroomNotExistsException
     */
    @Override
    public Classroom findByNameAndBuildingName(String name, String buildingName) throws BuildingNotExistsException, ClassroomNotExistsException {
        Building building = buildingService.findByName(buildingName);
        Classroom found = classroomRepository.findByNameAndBuilding(name, building);
        if (found != null) {
            return found;
        } else {
            throw new ClassroomNotExistsException(String.format("Ehhez az épülethez (%s) nem tartozik ilyen nevű (%s) tanterem!", buildingName, name));
        }
    }

    /**
     * A DTO objektum alapján történő keresést megvalósító függvény (Annak
     * ismeretében hogy melyik attribútum egyedi)
     *
     * @param classroomDTO A DTO objektum
     * @return A Classroom objektum ha létezik
     * @throws RoomReservationSystem.exception.BuildingNotExistsException
     */
    @Override
    public Classroom findByDTO(ClassroomDTO classroomDTO) throws BuildingNotExistsException {
        return classroomRepository.findByNameAndBuilding(
                classroomDTO.getName(),
                buildingService.findByName(classroomDTO.getBuilding())
        );
    }
}
