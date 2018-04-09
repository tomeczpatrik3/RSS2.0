package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Type;
import RoomReservationSystem.repository.TypeRepository;
import RoomReservationSystem.service.TypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A foglalás típusokkal kapcsolatos műveletekért felelős osztály
 * @author Tomecz Patrik
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRepository typeRepository;
    
    @Override
    public void delete(Type type) {
        typeRepository.delete(type);
    }

    @Override
    public void deleteByName(String name) {
       typeRepository.deleteByName(name);
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type findByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }
    
}
