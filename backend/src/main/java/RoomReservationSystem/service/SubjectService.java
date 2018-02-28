package RoomReservationSystem.service;

import RoomReservationSystem.model.Subject;
import java.util.List;

public interface SubjectService {
    Subject save(Subject subject);
    void delete(Subject subject);
    void deleteByName(String name);
    Iterable<Subject> findAll();
    Subject findByName(String name);
    List<String> getSubjectNames();
}
