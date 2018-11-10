package RoomReservationSystem.service.impl;

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
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem létezik
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem nem létezik
     */
    @Override
    public void deleteByNameAndBuildingName(String name, String buildingName) throws BuildingNotExistsException, ClassroomNotExistsException {
        Building building = buildingService.findByName(buildingName);
        if (classroomRepository.findByNameAndBuilding(name, building) != null) {
            classroomRepository.deleteByNameAndBuilding(name, building);
        } else {
            throw new ClassroomNotExistsException(buildingName, name);
        }
    }

    /**
     * Az osztályterem mentését megvalósító függvény
     *
     * @param classroom A menteni kívánt terem
     * @return A mentett terem
     * @throws ClassroomAlredyExistsException A lehetséges kivétel, ha a tanterem már létezik
     */
    @Override
    public Classroom save(Classroom classroom) throws ClassroomAlredyExistsException {
        if (classroomRepository.findByNameAndBuilding(classroom.getName(), classroom.getBuilding()) == null) {
            return classroomRepository.save(classroom);
        } else {
            throw new ClassroomAlredyExistsException(classroom.getBuilding().getName(), classroom.getName());
        }
    }

    /**
     * A tantermek azonosító alapján történő frissítését megvalósító függvény
     *
     * @param id Az azonosító
     * @param classroom A tanterem
     * @return A frissített tanterem
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem nem létezik
     */
    @Override
    public Classroom update(int id, Classroom classroom) throws ClassroomNotExistsException {
        if (classroomRepository.findById(id) == null) {
            throw new ClassroomNotExistsException(id);
        } else {
            classroom.setId(id);
            return classroomRepository.save(classroom);
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
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem nem létezik
     */
    @Override
    public Classroom findById(int id) throws ClassroomNotExistsException {
        Classroom found = classroomRepository.findById(id);
        if (found != null) {
            return found;
        } else {
            throw new ClassroomNotExistsException(id);
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
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem létezik
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
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem létezik
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem nem létezik
     */
    @Override
    public Classroom findByNameAndBuildingName(String name, String buildingName) throws BuildingNotExistsException, ClassroomNotExistsException {
        Building building = buildingService.findByName(buildingName);
        Classroom found = classroomRepository.findByNameAndBuilding(name, building);
        if (found != null) {
            return found;
        } else {
            throw new ClassroomNotExistsException(buildingName, name);
        }
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott azonosítóhoz
     * tartozó entitás
     *
     * @param id Az azonosító
     * @return Igen, ha létezik az azonosítóhoz tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsById(int id) {
        return classroomRepository.existsById(id);
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott névhez és
     * épülethez tartozó entitás
     *
     * @param name A tanterem neve
     * @param building Az épület
     * @return Igen, ha létezik a névhez és épülethez tartozó entitás, nem
     * egyébként
     */
    @Override
    public boolean existsByNameAndBuilding(String name, Building building) {
        return classroomRepository.existsByNameAndBuilding(name, building);
    }

    /**
     * A függvény ami visszaadja a megadott épülethez tartozó tantermek neveit
     * egy listában
     *
     * @param building Az épület neve
     * @return A nevek egy listában
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem létezik
     */
    @Override
    public List<String> getNamesByBuilding(String building) throws BuildingNotExistsException {
        List<Classroom> classrooms = findByBuilding(buildingService.findByName(building));
        List<String> names = new ArrayList<>();

        classrooms.forEach(classroom -> {
            names.add(classroom.getName());
        });

        return names;
    }
}
