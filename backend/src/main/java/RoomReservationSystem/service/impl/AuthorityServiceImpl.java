package RoomReservationSystem.service.impl;

import RoomReservationSystem.model.Authority;
import RoomReservationSystem.repository.AuthorityRepository;
import RoomReservationSystem.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService{
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Override
    public Authority save(Authority authority) {
        return this.authorityRepository.save(authority);
    }
    
    @Override
    public void delete(Authority authority) {
        this.authorityRepository.delete(authority);
    }
    
    @Override
    public Authority findByName(String name) {
        return this.authorityRepository.findByName(name);
    }
    
    @Override
    public Authority findById(int id) {
        return this.authorityRepository.findById(id);
    }
}
