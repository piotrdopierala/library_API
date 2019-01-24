package pl.dopierala.domain.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.dopierala.domain.LibraryUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Collection;

public class DBUserRepository implements UserRepository {
    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public int countUsers(){
        String query = "SELECT count(1) FROM LibraryUser";
        TypedQuery<Long> countQuery = em.createQuery(query, Long.class);

        return countQuery.getSingleResult().intValue();
    }

    @Override
    @Transactional
    public void saveAllUsers(Collection<LibraryUser> users){
        users.forEach(usr->em.persist(usr));
    }
}
