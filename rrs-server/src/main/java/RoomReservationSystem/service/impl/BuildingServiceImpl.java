package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.BuildingDTO;
import RoomReservationSystem.exception.BuildingAlredyExistsException;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.model.Building;
import RoomReservationSystem.repository.BuildingRepository;
import RoomReservationSystem.service.BuildingService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Az épületekkel kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    /**
     * Az épület név alapján történő törlésére szolgáló függvény
     *
     * @param name A törlendő épület neve
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem létezik
     */
    @Override
    public void deleteByName(String name) throws BuildingNotExistsException {
        if (buildingRepository.findByName(name) == null) {
            throw new BuildingNotExistsException(name);
        } else {
            buildingRepository.deleteByName(name);
        }
    }

    /**
     * Az épület mentésére szolgáló függvény
     *
     * @param building Az épület, amit menteni szeretnénk
     * @return A mentett épület
     * @throws BuildingAlredyExistsException A lehetséges kivétel, ha az épület már létezik
     */
    @Override
    public Building save(Building building) throws BuildingAlredyExistsException {
        if (buildingRepository.findByName(building.getName()) != null) {
            throw new BuildingAlredyExistsException(building.getName());
        } else {
            return buildingRepository.save(building);
        }
    }

    /**
     * Az épületek azonosító alapján történő frissítését megvalósító függvény
     *
     * @param id Az azonosító
     * @param building Az épület
     * @return A frissített épület
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem létezik
     */
    @Override
    public Building update(int id, Building building) throws BuildingNotExistsException {
        if (buildingRepository.findById(id) == null) {
            throw new BuildingNotExistsException(id);
        } else {
            building.setId(id);
            return buildingRepository.save(building);
        }
    }

    /**
     * A név alapján történő keresést megvalósító függvény
     *
     * @param name Az épület neve
     * @return Az épület ha létezik, null egyébként
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem létezik
     */
    @Override
    public Building findByName(String name) throws BuildingNotExistsException {
        Building building = buildingRepository.findByName(name);
        if (building == null) {
            throw new BuildingNotExistsException(name);
        } else {
            return building;
        }
    }

    /**
     * Az id alapján történő keresést megvalósító függvény
     *
     * @param id Az épület id-ja
     * @return Az épület ha létezik, null egyébként
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem létezik
     */
    @Override
    public Building findById(int id) throws BuildingNotExistsException {
        Building building = buildingRepository.findById(id);
        if (building == null) {
            throw new BuildingNotExistsException(id);
        } else {
            return building;
        }
    }

    /**
     * Az összes épület lekérdezését megvalósító függvény
     *
     * @return Az épületek egy listában
     */
    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    /**
     * Az épületek neveinek lekérdezését megvalósító függvény
     *
     * @return Az épületek nevei egy listában
     */
    @Override
    public List<String> getNames() {
        List<Building> buildings = buildingRepository.findAll();
        List<String> buildingNames = new ArrayList<>();

        buildings.forEach((building) -> {
            buildingNames.add(building.getName());
        });

        return buildingNames;
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
        return buildingRepository.existsById(id);
    }

    /**
     * A függvény amivel lekérdezhető, hogy létezik-e az adott névhez tartozó
     * entitás
     *
     * @param name A név
     * @return Igen, ha létezik a névhez tartozó entitás, nem egyébként
     */
    @Override
    public boolean existsByName(String name) {
        return buildingRepository.existsByName(name);
    }
}
