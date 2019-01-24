package pl.dopierala.domain.UserRepository;

import pl.dopierala.domain.LibraryUser;

import java.util.Collection;

public interface UserRepository {

    int countUsers();

    void saveAllUsers(Collection<LibraryUser> users);
}
