package RoomReservationSystem.service;

import RoomReservationSystem.dto.SubjectDTO;
import RoomReservationSystem.model.Subject;
import java.util.List;

public interface SubjectService {
    Subject save(SubjectDTO subject);
    void delete(Subject subject);
    void deleteByName(String name);
    List<Subject> findAll();
    List<Subject> findByName(String name);
    Subject findByCode(String code);
    Subject findById(int id);
    List<String> getSubjectNames();
}
