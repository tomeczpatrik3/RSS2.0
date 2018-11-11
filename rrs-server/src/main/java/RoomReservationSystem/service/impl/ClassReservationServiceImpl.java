package RoomReservationSystem.service.impl;

import RoomReservationSystem.dto.ClassReservationDTO;
import RoomReservationSystem.exception.BuildingNotExistsException;
import RoomReservationSystem.exception.ClassReservationNotExistsException;
import RoomReservationSystem.exception.ClassroomNotExistsException;
import RoomReservationSystem.exception.SemesterNotExistsException;
import RoomReservationSystem.exception.SemesterNotOpenedException;
import RoomReservationSystem.exception.StatusNotExistsException;
import RoomReservationSystem.exception.SubjectNotExistsException;
import RoomReservationSystem.exception.UserNotExistsException;
import RoomReservationSystem.model.Semester;
import RoomReservationSystem.model.Status;
import RoomReservationSystem.model.User;
import RoomReservationSystem.model.ClassReservation;
import static RoomReservationSystem.model.ClassReservation.toClassReservation;
import RoomReservationSystem.model.ReservationDate;
import RoomReservationSystem.service.ClassroomService;
import RoomReservationSystem.service.StatusService;
import RoomReservationSystem.service.SubjectService;
import RoomReservationSystem.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import RoomReservationSystem.repository.ClassReservationRepository;
import RoomReservationSystem.service.SemesterService;
import RoomReservationSystem.service.ClassReservationService;
import RoomReservationSystem.service.ReservationDateService;
import static RoomReservationSystem.util.DateUtils.getReservationDates;
import static RoomReservationSystem.util.DateUtils.getTimestamp;
import java.util.Collections;
import java.util.Date;

/**
 * A tanórákra vonatkozó foglalásokkal kapcsolatos műveletekért felelős osztály
 *
 * @author Tomecz Patrik
 */
@Service
public class ClassReservationServiceImpl implements ClassReservationService {

    @Autowired
    private ClassReservationRepository repository;

    @Autowired
    private ReservationDateService reservationDateService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private SemesterService semesterService;

    /**
     * A foglalás mentését megvalósító függvény
     *
     * @param classReservationDTO A foglalás
     * @return A mentett foglalás
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws SemesterNotOpenedException A lehetséges kivétel, ha a szemeszter
     * nem nyitott
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Override
    public ClassReservation save(ClassReservationDTO classReservationDTO)
            throws UserNotExistsException, SubjectNotExistsException, ClassroomNotExistsException, StatusNotExistsException, SemesterNotExistsException, SemesterNotOpenedException, BuildingNotExistsException {

        Semester semester;
        if (classReservationDTO.getStartDates().length == 1 && classReservationDTO.getEndDates().length == 1) {
            /*Ha egyszerű foglalásról van szó*/
            semester = semesterService.findOpenByDate(new Date(
                    getTimestamp(classReservationDTO.getStartDates()[0]).getTime()
            ));
        } else {
            /*Ha az adott szemeszterre vonatkozó foglalásról van szó*/
            semester = semesterService.findOpenedByName(classReservationDTO.getSemester());
        }

        ClassReservation reservation = repository.save(
                toClassReservation(
                        userService.getAuthenticatedUser(), /*A foglaláshoz tartozó felhasználó*/
                        subjectService.findByCode(classReservationDTO.getSubjectCode()), /*A foglaláshoz tartozó tantárgy*/
                        classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                                classReservationDTO.getClassroom(),
                                classReservationDTO.getBuilding()),
                        statusService.findByName("PENDING"), /*A foglalás státusza*/
                        semester,
                        Collections.emptyList(),
                        classReservationDTO.getNote() /*A foglaláshoz tartozó megjegyzés*/
                )
        );

        List<ReservationDate> reservationDates = getReservationDates(reservation, classReservationDTO.getStartDates(), classReservationDTO.getEndDates());

        reservationDates.forEach((date) -> {
            reservationDateService.save(date);
        });

        reservation.setDateList(reservationDates);

        return reservation;
    }

    /**
     * A foglalás azonosító alapján történő frissítését megvalósító függvény
     *
     * @param id Az azonosító
     * @param classReservationDTO A foglalás
     * @return A frissített foglalás
     * @throws ClassReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws SemesterNotOpenedException A lehetséges kivétel, ha a szemeszter
     * nem nyitott
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Override
    public ClassReservation update(int id, ClassReservationDTO classReservationDTO) throws ClassReservationNotExistsException,
            UserNotExistsException, SubjectNotExistsException, ClassroomNotExistsException, StatusNotExistsException, SemesterNotExistsException, BuildingNotExistsException, SemesterNotOpenedException {
        if (repository.findById(id) == null) {
            throw new ClassReservationNotExistsException(id);
        } else {
            Semester semester;
            if (classReservationDTO.getStartDates().length == 1 && classReservationDTO.getEndDates().length == 1) {
                /*Ha egyszerű foglalásról van szó*/
                semester = semesterService.findOpenByDate(new Date(
                        getTimestamp(classReservationDTO.getStartDates()[0]).getTime()
                ));
            } else {
                /*Ha az adott szemeszterre vonatkozó foglalásról van szó*/
                semester = semesterService.findOpenedByName(classReservationDTO.getSemester());
            }

            ClassReservation converted = toClassReservation(
                    userService.findByUsername(classReservationDTO.getUsername()), /*A foglaláshoz tartozó felhasználó*/
                    subjectService.findByCode(classReservationDTO.getSubjectCode()), /*A foglaláshoz tartozó tantárgy*/
                    classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                            classReservationDTO.getClassroom(),
                            classReservationDTO.getBuilding()),
                    statusService.findByName(classReservationDTO.getStatus()), /*A foglalás státusza*/
                    semester,
                    Collections.emptyList(),
                    classReservationDTO.getNote() /*A foglaláshoz tartozó megjegyzés*/
            );
            converted.setId(id);

            //Az entitás mentése:
            ClassReservation saved = repository.save(converted);
            //A régi dátum(ok) törlése:
            reservationDateService.deleteByReservation(saved);
            //Az új dátumok előállítása:
            List<ReservationDate> reservationDates = getReservationDates(saved, classReservationDTO.getStartDates(), classReservationDTO.getEndDates());
            //Az új dátumok mentése:
            reservationDates.forEach((date) -> {
                reservationDateService.save(date);
            });
            //Az új dátumok beállítása a foglalás számára:
            saved.setDateList(reservationDates);

            return saved;
        }
    }

    /**
     * A saját foglalás azonosító alapján történő frissítését megvalósító
     * függvény
     *
     * @param id Az azonosító
     * @param classReservationDTO A foglalás
     * @return A frissített foglalás
     * @throws ClassReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     * @throws SemesterNotOpenedException A lehetséges kivétel, ha a szemeszter
     * nem nyitott
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Override
    public ClassReservation updateOwnById(int id, ClassReservationDTO classReservationDTO) throws ClassReservationNotExistsException,
            SubjectNotExistsException, ClassroomNotExistsException, StatusNotExistsException, SemesterNotExistsException, BuildingNotExistsException, SemesterNotOpenedException {
        if (repository.findById(id) == null || !repository.findById(id).getUser().getUsername().equals(userService.getAuthenticatedUser().getUsername())) {
            throw new ClassReservationNotExistsException(id);
        } else {
            Semester semester;
            if (classReservationDTO.getStartDates().length == 1 && classReservationDTO.getEndDates().length == 1) {
                /*Ha egyszerű foglalásról van szó*/
                semester = semesterService.findOpenByDate(new Date(
                        getTimestamp(classReservationDTO.getStartDates()[0]).getTime()
                ));
            } else {
                /*Ha az adott szemeszterre vonatkozó foglalásról van szó*/
                semester = semesterService.findOpenedByName(classReservationDTO.getSemester());
            }

            ClassReservation converted = toClassReservation(
                    userService.getAuthenticatedUser(), /*A foglaláshoz tartozó felhasználó*/
                    subjectService.findByCode(classReservationDTO.getSubjectCode()), /*A foglaláshoz tartozó tantárgy*/
                    classroomService.findByNameAndBuildingName( /*A foglaláshoz tartozó tanterem*/
                            classReservationDTO.getClassroom(),
                            classReservationDTO.getBuilding()),
                    statusService.findByName("PENDING"), /*A foglalás státusza*/
                    semester,
                    Collections.emptyList(),
                    classReservationDTO.getNote() /*A foglaláshoz tartozó megjegyzés*/
            );
            converted.setId(id);

            //Az entitás mentése:
            ClassReservation saved = repository.save(converted);
            //A régi dátum(ok) törlése:
            reservationDateService.deleteByReservation(saved);
            //Az új dátumok előállítása:
            List<ReservationDate> reservationDates = getReservationDates(saved, classReservationDTO.getStartDates(), classReservationDTO.getEndDates());
            //Az új dátumok mentése:
            reservationDates.forEach((date) -> {
                reservationDateService.save(date);
            });
            //Az új dátumok beállítása a foglalás számára:
            saved.setDateList(reservationDates);

            return saved;
        }
    }

    /**
     * A foglalás id alapján történő keresését megvalósító függvény
     *
     * @param id A foglalás id-je
     * @return A foglalás ha létezik, null egyébként
     * @throws ClassReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     */
    @Override
    public ClassReservation findById(int id) throws ClassReservationNotExistsException {
        ClassReservation res = repository.findById(id);
        if (res != null) {
            return res;
        } else {
            throw new ClassReservationNotExistsException(id);
        }
    }

    /**
     * A foglalások lekérdezését megvalósító függvény
     *
     * @return A foglalások egy listában
     */
    @Override
    public List<ClassReservation> getAll() {
        return repository.findAll();
    }

    /**
     * A foglalások felhasználónév alapján történő keresését megvalósító
     * függvény
     *
     * @param username A felhasználónév
     * @return Az adott felhasználó által történt foglalások egy listában
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Override
    public List<ClassReservation> findByUsername(String username) throws UserNotExistsException {
        User user = userService.findByUsername(username);
        if (user != null) {
            return repository.findByUser(user);
        } else {
            throw new UserNotExistsException(username);
        }
    }

    /**
     * A foglalások státusz alapján történő keresését megvalósító függvény
     *
     * @param statusName A keresendő státusz
     * @return Az adott státusszal rendelkező foglalások
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     */
    @Override
    public List<ClassReservation> findByStatus(String statusName) throws StatusNotExistsException {
        Status status = statusService.findByName(statusName);
        if (status != null) {
            return repository.findByStatus(status);
        } else {
            throw new StatusNotExistsException(statusName);
        }
    }

    /**
     * Egy foglalás státuszának megváltoztatását megvalósító függvény
     *
     * @param id A foglalás id-ja
     * @param statusName Az új státusz
     * @return A megváltoztatott státusszal rendelkező foglalás
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     * @throws ClassReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     */
    @Override
    public ClassReservation setStatus(int id, String statusName) throws StatusNotExistsException, ClassReservationNotExistsException {
        ClassReservation res = repository.findById(id);
        Status status = statusService.findByName(statusName);
        if (res != null) {
            if (status != null) {
                res.setStatus(status);
                return repository.save(res);
            } else {
                throw new StatusNotExistsException(statusName);
            }
        } else {
            throw new ClassReservationNotExistsException(id);
        }
    }

    /**
     * A foglalások tanterem alapján történő kiválasztását megvalósító függvény
     *
     * @param building Az épület
     * @param classroom A tanterem
     * @return A foglalások egy listában
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Override
    public List<ClassReservation> findByBuildingAndClassroom(String building, String classroom) throws ClassroomNotExistsException, BuildingNotExistsException {
        return repository.findByClassroom(classroomService.findByNameAndBuildingName(classroom, building));
    }

    /**
     * A foglalások tantárgy alapján történő kiválasztását megvalósító függvény
     *
     * @param subjectCode A tárgykód
     * @return A foglalások egy listában
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Override
    public List<ClassReservation> findBySubjectCode(String subjectCode) throws SubjectNotExistsException {
        return repository.findBySubject(subjectService.findByCode(subjectCode));
    }

    /**
     * A foglalások szemeszter alapján történő kiválasztását megvalósító
     * függvény
     *
     * @param semester A szemeszter
     * @return A foglalások egy listában
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Override
    public List<ClassReservation> findBySemester(String semester) throws SemesterNotExistsException {
        return repository.findBySemester(semesterService.findByName(semester));
    }

    /**
     * Egy adott azonosítóhoz tartozó foglalás törlése
     *
     * @param id Az azonosító
     * @throws ClassReservationNotExistsException A lehetséges kivétel, ha a
     * foglalás nem létezik
     */
    @Override
    public void deleteById(int id) throws ClassReservationNotExistsException {
        if (repository.findById(id) != null) {
            repository.deleteById(id);
        } else {
            throw new ClassReservationNotExistsException(id);
        }
    }

    /**
     * Egy adott felhasználóhoz tartozó foglalások törlése
     *
     * @param username A felhasználónév
     * @throws UserNotExistsException A lehetséges kivétel, ha a felhasználó nem
     * létezik
     */
    @Override
    public void deleteByUsername(String username) throws UserNotExistsException {
        repository.deleteByUser(userService.findByUsername(username));
    }

    /**
     * Egy adott épület adott tanterméhez tartozó foglalások törlése
     *
     * @param building Az épület
     * @param classroom A tanterem
     * @throws ClassroomNotExistsException A lehetséges kivétel, ha a tanterem
     * nem létezik
     * @throws BuildingNotExistsException A lehetséges kivétel, ha az épület nem
     * létezik
     */
    @Override
    public void deleteByBuildingAndClassroom(String building, String classroom) throws ClassroomNotExistsException, BuildingNotExistsException {
        repository.deleteByClassroom(classroomService.findByNameAndBuildingName(classroom, building));
    }

    /**
     * Egy adott tantárgyhoz tartozó foglalások törlése
     *
     * @param subjectCode A tárgykód
     * @throws SubjectNotExistsException A lehetséges kivétel, ha a tantárgy nem
     * létezik
     */
    @Override
    public void deleteBySubjectCode(String subjectCode) throws SubjectNotExistsException {
        repository.deleteBySubject(subjectService.findByCode(subjectCode));
    }

    /**
     * Egy adott szemeszterhez tartozó foglalások törlése
     *
     * @param semester A szemeszter
     * @throws SemesterNotExistsException A lehetséges kivétel, ha a szemeszter
     * nem létezik
     */
    @Override
    public void deleteBySemester(String semester) throws SemesterNotExistsException {
        repository.deleteBySemester(semesterService.findByName(semester));
    }

    /**
     * Egy adott státuszhoz tartozó foglalások törlése
     *
     * @param status A státusz
     * @throws StatusNotExistsException A lehetséges kivétel, ha a státusz nem
     * létezik
     */
    @Override
    public void deleteByStatus(String status) throws StatusNotExistsException {
        repository.deleteByStatus(statusService.findByName(status));
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
        return repository.existsById(id);
    }
}
