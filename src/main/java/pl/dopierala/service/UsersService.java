package pl.dopierala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dopierala.domain.LibraryUser;
import pl.dopierala.domain.UserRepository.UserRepository;

import java.util.Collection;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepo;

    public int countUsers(){
        return userRepo.countUsers();
    }

    public void saveAllUsers(Collection<LibraryUser> users){
        userRepo.saveAllUsers(users);
    };
}
