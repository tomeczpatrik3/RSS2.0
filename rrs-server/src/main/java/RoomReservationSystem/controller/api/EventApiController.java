package RoomReservationSystem.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import RoomReservationSystem.service.EventService;

/**
 * Az eseményekhez tartozó végpontokat tartalmazó osztály
 *
 * @author Tomecz Patrik
 */
@RestController
@RequestMapping(value = "/api/event")
public class EventApiController {

    @Autowired
    private EventService calendarService;

    /**
     * A függvény ami visszaadja egy listában az összes eseményt
     *
     * @return A megfelelő válasz entitás
     */
    @GetMapping("/getEvents")
    public ResponseEntity getEvents() {
        return ResponseEntity.ok(calendarService.getEvents());
    }

    /**
     * A függvény ami visszaadja az adott felhasználóhoz tartozó eseményeket
     *
     * @param userName A felhasználó (teljes) neve
     * @return A megfelelő válasz entitás
     */
    @GetMapping("/findByUserName")
    public ResponseEntity findByUserName(@RequestParam(value = "userName", required = true) String userName) {
        return ResponseEntity.ok(calendarService.findByUserName(userName));
    }

    /**
     * A függvény ami visszaadja az adott épülethez tartozó eseményeket
     *
     * @param buildingName Az épület neve
     * @return A megfelelő válasz entitás
     */
    @GetMapping("/findByBuildingName")
    public ResponseEntity findByBuildingName(@RequestParam(value = "buildingName", required = true) String buildingName) {
        return ResponseEntity.ok(calendarService.findByBuildingName(buildingName));
    }

    /**
     * A függvény ami visszaadja az adott tanteremhez tartozó eseményeket
     *
     * @param classroom A tanterem neve
     * @param building Az épület neve
     * @return A megfelelő válasz entitás
     */
    @GetMapping("/findByClassroomNameAndBuilding")
    public ResponseEntity findByClassroomNameAndBuilding(
            @RequestParam(value = "classroom", required = true) String classroom,
            @RequestParam(value = "building", required = true) String building
    ) {
        return ResponseEntity.ok(calendarService.findByClassroomNameAndBuilding(classroom, building));
    }

    /**
     * A függvény ami visszaadja az adott eseményhez tartozó eseményeket
     *
     * @param eventName Az esemény neve
     * @return A megfelelő válasz entitás
     */
    @GetMapping("/findByEventName")
    public ResponseEntity findByEventName(@RequestParam(value = "eventName", required = true) String eventName) {
        return ResponseEntity.ok(calendarService.findByEventName(eventName));
    }

    /**
     * A függvény ami visszaadja az adott tantárgyhoz tartozó eseményeket
     *
     * @param subjectName A tantárgy neve
     * @return A megfelelő válasz entitás
     */
    @GetMapping("/findBySubjectName")
    public ResponseEntity findBySubjectName(@RequestParam(value = "subjectName", required = true) String subjectName) {
        return ResponseEntity.ok(calendarService.findBySubjectName(subjectName));
    }

    /**
     * A függvény ami visszaadja az adott szemeszterhez tartozó eseményeket
     *
     * @param semesterName A szemeszter neve
     * @return A megfelelő válasz entitás
     */
    @GetMapping("/findBySemesterName")
    public ResponseEntity findBySemesterName(@RequestParam(value = "semesterName", required = true) String semesterName) {
        return ResponseEntity.ok(calendarService.findBySemesterName(semesterName));
    }
}
