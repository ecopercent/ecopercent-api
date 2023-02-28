package sudols.ecopercent.repository;

import org.springframework.stereotype.Repository;
import sudols.ecopercent.domain.User;
import sudols.ecopercent.dto.UserPatchDto;
import sudols.ecopercent.dto.UserPostDto;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {

    private static final Map<Long, User> store = new HashMap<>();
    private static long sequeunce = 0L;


    @Override
    public Long save(User user) {
        user.setUserId(sequeunce++);
        store.put(user.getUserId(), user);
        return user.getUserId();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return store.values().stream().filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    @Override
    public void update(Long userId, User newUserData) {
        String nickname = newUserData.getNickname();
        String profileImage = newUserData.getProfileImage();
        String profileMessage = newUserData.getProfileMessage();
        User user = store.get(userId);
        user.setNickname(nickname);
        user.setProfileImage(profileImage);
        user.setProfileMessage(profileMessage);
        store.replace(userId, user);
    }

    @Override
    public void delete(Long userId) {
        store.remove(userId);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
