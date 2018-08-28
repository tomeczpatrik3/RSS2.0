package RoomReservationSystem.dto.reservation;

import RoomReservationSystem.dto.BuildingDTO;
import static RoomReservationSystem.dto.BuildingDTO.toBuildingDTO;
import RoomReservationSystem.dto.ClassroomDTO;
import static RoomReservationSystem.dto.ClassroomDTO.toClassroomDTO;
import RoomReservationSystem.dto.SemesterDTO;
import static RoomReservationSystem.dto.SemesterDTO.toSemesterDTO;
import RoomReservationSystem.dto.StatusDTO;
import static RoomReservationSystem.dto.StatusDTO.toStatusDTO;
import RoomReservationSystem.dto.SubjectDTO;
import static RoomReservationSystem.dto.SubjectDTO.toSubjectDTO;
import RoomReservationSystem.dto.UserDTO;
import static RoomReservationSystem.dto.UserDTO.toUserDTO;
import RoomReservationSystem.model.reservation.SemesterClassReservation;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * A szemeszterre vonatkozó foglalásokhoz tartozó DTO osztály
 * @author Tomecz Patrik
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class SemesterReservationDTO extends ReservationDTO{
    private SubjectDTO subject;         /*A tantárgy amire a foglalás vonatkozik*/
    private SemesterDTO semester;            /*A félév*/
    
    public SemesterReservationDTO(
            UserDTO userDTO,
            BuildingDTO buildingDTO,
            ClassroomDTO classroomDTO,
            SubjectDTO subjectDTO,
            SemesterDTO semesterDTO,
            StatusDTO statusDTO,
            String note
    ) {
        super(userDTO, buildingDTO, classroomDTO, statusDTO, note);
        this.subject = subjectDTO;
        this.semester = semesterDTO;
    }
    
    /**
     * Az SemesterReservation objektumból SemesterReservationDTO objektum létrehozásáért felelős metódus
     * @param   reservation     A SemesterReservation objektum
     * @return           A SemesterReservationDTO objektum
     */
    public static SemesterReservationDTO toSemesterReservationDTO(SemesterClassReservation reservation) {
        return new SemesterReservationDTO(
                toUserDTO(reservation.getUser()),
                toBuildingDTO(reservation.getClassroom().getBuilding()),
                toClassroomDTO(reservation.getClassroom()),
                toSubjectDTO(reservation.getSubject()),
                toSemesterDTO(reservation.getSemester()),
                toStatusDTO(reservation.getStatus()),
                reservation.getNote()
        );
    }
    
    /**
     * Több SemesterReservation objektumok SemesterReservationDTO objektumokká alakításáért felelős metódus
     * @param   reservations    A SemesterReservation objektumok egy listában
     * @return                  A SemesterReservationDTO objektumok egy listában
     */
    public static List<SemesterReservationDTO> toSemesterReservationDTOList(List<SemesterClassReservation> reservations) {
        List<SemesterReservationDTO> reservationDTOs = new ArrayList<>();
        reservations.forEach((reservation) -> {
            reservationDTOs.add(toSemesterReservationDTO(reservation));
        });
        return reservationDTOs;        
    }
}
