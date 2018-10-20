package RoomReservationSystem.api;

import RoomReservationSystem.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/calendar")
public class CalendarApiController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/getEvents")
    public ResponseEntity getEvents() {
        return ResponseEntity.ok(calendarService.getEvents());
    }

    @GetMapping("/findByUserName")
    public ResponseEntity findByUserName(@RequestParam(value = "userName", required = true) String userName) {
        return ResponseEntity.ok(calendarService.findByUserName(userName));
    }

    @GetMapping("/findByBuildingName")
    public ResponseEntity findByBuildingName(@RequestParam(value = "buildingName", required = true) String buildingName) {
        return ResponseEntity.ok(calendarService.findByBuildingName(buildingName));
    }

    @GetMapping("/findByClassroomName")
    public ResponseEntity findByClassroomName(@RequestParam(value = "classroomName", required = true) String classroomName) {
        return ResponseEntity.ok(calendarService.findByClassroomName(classroomName));
    }

    @GetMapping("/findByEventName")
    public ResponseEntity findByEventName(@RequestParam(value = "eventName", required = true) String eventName) {
        return ResponseEntity.ok(calendarService.findByEventName(eventName));
    }

    @GetMapping("/findBySubjectName")
    public ResponseEntity findBySubjectName(@RequestParam(value = "subjectName", required = true) String subjectName) {
        return ResponseEntity.ok(calendarService.findBySubjectName(subjectName));
    }

    @GetMapping("/findBySemesterName")
    public ResponseEntity findBySemesterName(@RequestParam(value = "semesterName", required = true) String semesterName) {
        return ResponseEntity.ok(calendarService.findBySemesterName(semesterName));
    }
}
