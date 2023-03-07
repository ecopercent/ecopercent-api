package sudols.ecopercent.repository.jpa;

import jakarta.persistence.EntityManager;
import sudols.ecopercent.domain.User;
import sudols.ecopercent.repository.UserRepository;

import java.util.List;
import java.util.Optional;

//@Repository
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        System.out.println(result);
        return result.stream().findAny();
    }

    @Override
    public void update(Long userId, User newUserData) {
        User user = em.find(User.class, userId);
        user.setNickname(newUserData.getNickname());
        user.setProfileImage(newUserData.getProfileImage());
        user.setProfileMessage(newUserData.getProfileMessage());
    }

    @Override
    public void deleteById(Long userId) {
        User user = findById(userId).get();
        em.remove(user);
    }

    @Override
    public void clearStore() {
        em.clear();
    }
}