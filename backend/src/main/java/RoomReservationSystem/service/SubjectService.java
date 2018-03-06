package RoomReservationSystem.service;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.model.Subject;
import java.util.List;

public interface SubjectService {
    Subject save(SubjectDTO subject);
    void delete(Subject subject);
    void deleteByName(String name);
    Iterable<Subject> findAll();
    Subject findByName(String name);
    Subject findById(int id);
    List<String> getSubjectNames();
}
