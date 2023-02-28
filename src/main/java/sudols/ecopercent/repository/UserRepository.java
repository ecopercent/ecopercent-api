package sudols.ecopercent.repository;

import sudols.ecopercent.domain.User;
import sudols.ecopercent.dto.UserPatchDto;
import sudols.ecopercent.dto.UserPostDto;

import java.util.Optional;

public interface UserRepository {

    Long save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    void update(Long userId, User newUserData);

    void delete(Long userId);

    void clearStore();
}
